package element.list.flowersmvp.ui.home.listeners

import element.list.flowersmvp.model.Flower

interface FlowersAfterSearchListener {
    fun flowersAfterSearchSuccess(flowersList: MutableList<Flower>)
    fun flowersAfterSearchFail()
}