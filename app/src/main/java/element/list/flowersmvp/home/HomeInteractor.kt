package element.list.flowersmvp.home

import androidx.lifecycle.MutableLiveData
import element.list.flowersmvp.FlowerService
import element.list.flowersmvp.home.listeners.FlowersAfterSearchListener
import element.list.flowersmvp.home.listeners.GetFlowersListener
import element.list.flowersmvp.home.listeners.SearchFlowersListener
import element.list.flowersmvp.model.Flower
import element.list.flowersmvp.model.PaginationInfo
import element.list.flowersmvp.utils.onEnqueue

class HomeInteractor(private var flowerService: FlowerService) {
    private var flowerLiveItems: MutableLiveData<List<Flower>> = MutableLiveData()
    var flowers = mutableListOf<Flower>()
    lateinit var flowerItemsPagination: PaginationInfo

    fun searchFlowers(
        searchFlowersListener: SearchFlowersListener,
        searchText: String,
        pageNum: Int
    ) {
        flowerService.searchFlowers(searchText, pageNum).onEnqueue(
            success = {
                searchFlowersListener.onSearchFlowersSuccess(it.body()!!.flowers, pageNum)
                flowerItemsPagination = it.body()!!.meta
            },
            failure = {
                searchFlowersListener.onSearchFlowersFail()
            }
        )
    }

    fun getFlowers(getFlowersListener: GetFlowersListener, pageNum: Int) {
        flowerService.getFlowers(pageNum).onEnqueue(
            success = {
                flowerItemsPagination = it.body()!!.meta
                getFlowersListener.onGetFlowersSuccess(it.body()!!.flowers)
            },
            failure = {
                getFlowersListener.onGetFlowersFail()
            }
        )
    }

    fun getFlowersAfterSearch(afterSearchListener: FlowersAfterSearchListener, pageNum: Int) {
        flowerService.getFlowers(pageNum).onEnqueue(
            success = {
                flowers = it.body()?.flowers!!
                flowerLiveItems.postValue(flowers)
                flowerItemsPagination = it.body()?.meta!!
                afterSearchListener.flowersAfterSearchSuccess(it.body()!!.flowers)
            },
            failure = {}
        )
    }

}