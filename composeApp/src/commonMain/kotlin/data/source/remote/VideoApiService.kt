package data.source.remote

import data.source.remote.model.VideosList
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import util.pathUrl

class VideoApiService(private val httpClient: HttpClient) {
    suspend fun getAllVideos(): VideosList = httpClient.get {
        this.pathUrl("/popular")
    }.body()
}