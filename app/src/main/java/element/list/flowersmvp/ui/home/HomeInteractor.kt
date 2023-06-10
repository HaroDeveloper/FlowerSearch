package element.list.flowersmvp.ui.home

import androidx.lifecycle.MutableLiveData
import element.list.flowersmvp.networking.FlowerService
import element.list.flowersmvp.ui.home.listeners.FlowersAfterSearchListener
import element.list.flowersmvp.ui.home.listeners.GetFlowersListener
import element.list.flowersmvp.ui.home.listeners.SearchFlowersListener
import element.list.flowersmvp.model.Flower
import element.list.flowersmvp.model.PaginationInfo
import element.list.flowersmvp.utils.onEnqueue

class HomeInteractor(private var flowerService: FlowerService) {
    private var flowerLiveItems: MutableLiveData<List<Flower>> = MutableLiveData()
    var flowers: MutableList<Flower>? = mutableListOf()
    var flowerItemsPagination: PaginationInfo? = null

    fun searchFlowers(
        searchFlowersListener: SearchFlowersListener,
        searchText: String,
        pageNum: Int
    ) {
        flowerService.searchFlowers(searchText, pageNum).onEnqueue(
            success = {
                searchFlowersListener.onSearchFlowersSuccess(it.body()?.flowers, pageNum)
                flowerItemsPagination = it.body()?.meta
            },
            failure = {
                searchFlowersListener.onSearchFlowersFail()
            }
        )
    }

    fun getFlowers(getFlowersListener: GetFlowersListener, pageNum: Int) {
        flowerService.getFlowers(pageNum).onEnqueue(
            success = {
                flowerItemsPagination = it.body()?.meta
                getFlowersListener.onGetFlowersSuccess(it.body()?.flowers)
            },
            failure = {
                getFlowersListener.onGetFlowersFail()
            }
        )
    }

    fun getFlowersAfterSearch(afterSearchListener: FlowersAfterSearchListener, pageNum: Int) {
        flowerService.getFlowers(pageNum).onEnqueue(
            success = {
                flowers = it.body()?.flowers
                flowerLiveItems.postValue(flowers)
                flowerItemsPagination = it.body()?.meta
                it.body()?.flowers?.let { afterSearchListener.flowersAfterSearchSuccess(it) }
            },
            failure = {}
        )
    }

}