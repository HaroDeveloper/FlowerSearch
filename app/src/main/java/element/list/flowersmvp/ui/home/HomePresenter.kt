package element.list.flowersmvp.ui.home

import element.list.flowersmvp.ui.home.listeners.FlowersAfterSearchListener
import element.list.flowersmvp.ui.home.listeners.GetFlowersListener
import element.list.flowersmvp.ui.home.listeners.SearchFlowersListener
import element.list.flowersmvp.model.Flower

class HomePresenter(private var interactor: HomeInteractor) :
    HomeContract.Presenter {
    var isLastPage: Boolean = false
    var isLoading: Boolean = false
    var view: HomeContract.View? = null

    fun bindView(view: HomeFragment) {
        this.view = view
    }

    fun unbindView() {
        view = null
    }

    override fun searchFlowers(searchText: String, pageNum: Int) {
        interactor.searchFlowers(searchFlowersListener, searchText, pageNum)
    }

    override fun getFlowers(pageNum: Int) {
        interactor.getFlowers(getFlowersListener, pageNum)
    }

    override fun getFlowersAfterSearch(pageNum: Int) {
        interactor.getFlowersAfterSearch(flowerAfterSearchListener, pageNum)
    }

    override fun getPaginationItems(searchText: String) {
        if ((interactor.flowerItemsPagination?.pagination?.nextPage ?: 0) >
            (interactor.flowerItemsPagination?.pagination?.currentPage ?: 0)
        ) {
            if (searchText.isEmpty())
                getFlowers(interactor.flowerItemsPagination?.pagination?.nextPage ?: 0)
            else
                searchFlowers(searchText, interactor.flowerItemsPagination?.pagination?.nextPage ?: 0)
        }
    }

    private var getFlowersListener: GetFlowersListener = object : GetFlowersListener {
        override fun onGetFlowersSuccess(flowersList: MutableList<Flower>?) {
            flowersList?.let { view?.addAdapterData(it) }
            isLoading = false
        }

        override fun onGetFlowersFail() {
            view?.getFlowersFailed()
        }
    }

    private var searchFlowersListener: SearchFlowersListener = object : SearchFlowersListener {
        override fun onSearchFlowersSuccess(flowersList: MutableList<Flower>?, pageNum: Int?) {
            if (pageNum == 1)
                flowersList?.let { view?.setAdapterData(it) }
            else
                flowersList?.let { view?.addAdapterData(it) }

            isLoading = false
        }

        override fun onSearchFlowersFail() {
            view?.searchFlowersFailed()
        }
    }

    private var flowerAfterSearchListener: FlowersAfterSearchListener =
        object : FlowersAfterSearchListener {
            override fun flowersAfterSearchSuccess(flowersList: MutableList<Flower>) {
                view?.setAdapterData(flowersList)
                isLoading = false
            }

            override fun flowersAfterSearchFail() {
                view?.getFlowersFailed()
            }
        }
}