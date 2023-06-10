package element.list.flowersmvp.ui.home.listeners

import element.list.flowersmvp.model.Flower

interface GetFlowersListener {
    fun onGetFlowersSuccess(flowersList: MutableList<Flower>?)
    fun onGetFlowersFail()
}