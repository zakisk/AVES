package com.aves.data.network_util

import com.aves.domain.state.DataState
import kotlinx.coroutines.flow.FlowCollector
import org.json.JSONObject
import retrofit2.HttpException
import retrofit2.Response


/**
 * `Created by Zaki Shaikh : December 12th, 2022`
 *
 * Returns the error message when [HttpException] occurs decodes it from [HttpException.message] field.
 */
internal fun HttpException.serverMessage(): String? {
    return try {
        val response = this.response()!!.errorBody()!!.charStream().readText()
        if (response.contains("{") && response.contains("}")) {
            val jsonObject = JSONObject(
                response.substring(response.indexOf('{'), response.lastIndexOf('}'))
            )
            if (jsonObject.has("message")) {
                jsonObject.getString("message")
            } else null
        } else null
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}


/**
 * An extension to [FlowCollector] to emit response from API [Response] and error message
 * if an exception occurs.
 */
internal suspend fun <T> FlowCollector<DataState<T>>.emitResponse(
    /** all API call methods should return [retrofit2.Response] in order to get status of api call
     * instead of handling errors manually. */
    response: Response<T>,

    /** Validates whether the received [retrofit2.Response] call is succes or not by
     * calling [validate] on this response and evaluating based on it's fields.*/
    validate: T?.() -> Boolean,

    /** Returns the error message if [retrofit2.Response] fails.*/
    message: T?.() -> String? = { null }
) {
    try {
        /** This is the actual response we get from server [retrofit2.Response.body].*/
        val body = response.body()

        /** Checking whether this API call is finished successfully or not. */
        if (response.isSuccessful && body != null && body.validate()) {
            emit(DataState.Success(body))
        } else {
            /** if [Response.code] is 4XX i.e bad request then throws [HttpException].*/
            if (response.code() >= 400) throw HttpException(response)
            emit(DataState.Error("${body.message()} HTTP ${response.code()}"))
        }
    } catch (e: HttpException) {
        /** Collects the error message by [HttpException.serverMessage] if thrown.*/
        emit(DataState.Error(e.serverMessage() ?: e.localizedMessage))
    } catch (e: Exception) {
        emit(DataState.Error())
    }
}