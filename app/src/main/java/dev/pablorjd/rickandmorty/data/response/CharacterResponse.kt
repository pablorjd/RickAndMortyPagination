package dev.pablorjd.rickandmorty.data.response

import com.google.gson.annotations.SerializedName
import dev.pablorjd.rickandmorty.presentation.model.CharacterModel


data class CharacterResponse(
    @SerializedName("id") val id:Int,
    @SerializedName("name") val name: String,
    @SerializedName("status") val status: String,
    @SerializedName("species") val species: String,
    @SerializedName("type") val type: String,
    @SerializedName("gender") val gender: String,
    @SerializedName("image") val image: String,
    @SerializedName("url") val url: String
) {
    fun toDomain():CharacterModel {
        return CharacterModel(
            id=id,
            name= name,
            isAlive = status == "Alive",
            image = image
        )
    }
}
