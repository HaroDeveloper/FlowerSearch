package element.list.flowersmvp.model

import com.google.gson.annotations.SerializedName

data class Pagination(
    @SerializedName("current_page")
    var currentPage: Int,
    @SerializedName("prev_page")
    var previousPage: Int,
    @SerializedName("next_page")
    var nextPage: Int,
    @SerializedName("total_pages")
    var totalPages: Int
)