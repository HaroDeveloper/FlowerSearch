package element.list.flowersmvp.networking

import element.list.flowersmvp.model.FlowersListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FlowerService {
    @GET("api/v1/flowers")
    fun getFlowers(@Query("page") page: Int?): Call<FlowersListResponse>

    @GET("api/v1/flowers/search")
    fun searchFlowers(@Query("query") searchText: String, @Query("page") page: Int?): Call<FlowersListResponse>
}