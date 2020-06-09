package element.list.flowersmvp.model

import com.google.gson.annotations.SerializedName

data class Flower(
    var id: Int,
    var name: String,
    @SerializedName("latin_name")
    var latinName: String,
    var sightings: Int,
    @SerializedName("profile_picture")
    var profilePicture: String,
    var favorite: Boolean
)