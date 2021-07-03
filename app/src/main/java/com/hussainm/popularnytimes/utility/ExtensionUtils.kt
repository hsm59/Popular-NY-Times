package com.hussainm.popularnytimes.utility

import com.google.gson.JsonParser
import okhttp3.ResponseBody


/**
 * Error body extension function, that returns the error message from the Json data
 */
fun ResponseBody?.getErrorMessage(): String {
    return try {

        val errorJsonObject = JsonParser().parse(this?.string()).asJsonObject

        if (errorJsonObject?.has(ERROR_KEY) == true && errorJsonObject.has(MESSAGE_KEY))
            errorJsonObject.get(MESSAGE_KEY).asString
        else
            ERROR_MESSAGE

    } catch (e: Exception) {
        e.printStackTrace()
        e.message.orEmpty()
    }
}