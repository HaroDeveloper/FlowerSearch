package element.list.flowersmvp.ui.home

import element.list.flowersmvp.model.Flower

interface HomeContract {
    interface View {
        fun setAdapterData(flowerList: MutableList<Flower>)
        fun addAdapterData(flowerList: MutableList<Flower>)
        fun searchFlowersFailed()
        fun getFlowersFailed()
    }

    interface Presenter{
        fun searchFlowers(searchText: String, pageNum: Int)
        fun getFlowers(pageNum: Int)
        fun getFlowersAfterSearch(pageNum: Int)
        fun getPaginationItems(searchText: String)
    }
}