package dev.pablorjd.rickandmorty.data.response

import com.google.gson.annotations.SerializedName

data class ResponseWrapper(
    @SerializedName("info") val info: InfoResponse,
    @SerializedName("results") val results: List<CharacterResponse>,

)