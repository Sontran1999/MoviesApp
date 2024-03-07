package util

import data.constant.NetworkConstant
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.headers
import io.ktor.client.request.parameter
import io.ktor.http.takeFrom

fun HttpRequestBuilder.pathUrl(path: String) {
    url {
        takeFrom("${NetworkConstant.BASE_URL}$path")
        headers{
            append("Authorization", NetworkConstant.AUTH)
        }
    }

}