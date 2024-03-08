package data.source.remote

import data.model.VideosList
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.http.HttpHeaders
import util.pathUrl

class MovieApiService(private val httpClient: HttpClient) {
    suspend fun getAllMovies(): VideosList = httpClient.get {
        this.pathUrl("/popular")
    }.body()
}