package element.list.flowersmvp.home.listeners

import element.list.flowersmvp.model.Flower


interface SearchFlowersListener {
    fun onSearchFlowersSuccess(flowersList: MutableList<Flower>, pageNum: Int)
    fun onSearchFlowersFail()
}