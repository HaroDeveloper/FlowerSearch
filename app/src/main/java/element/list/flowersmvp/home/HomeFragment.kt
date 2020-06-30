package element.list.flowersmvp.home

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import element.list.flowersmvp.*
import element.list.flowersmvp.constants.Constants
import element.list.flowersmvp.model.Flower
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

class HomeFragment : Fragment(), HomeContract.View {
    @Inject
    lateinit var presenter: HomePresenter
    private lateinit var flowerAdapter: FlowerAdapter
    private var startingPage = 1
    private var recyclerStartPos = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        presenter.bindView(this)
        presenter.getFlowers(startingPage)
        setListeners()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        FlowerApp.appComponent.plus(HomeModule()).inject(this)
        super.onCreate(savedInstanceState)
    }

    private fun setAdapter() {
        flowerAdapter = FlowerAdapter(context!!)
        flowerRecyclerView.adapter = flowerAdapter
        val flowerLayoutManager =
            GridLayoutManager(
                context,
                Constants.FLOWER_RECYCLER_GRID_COLUMNS,
                RecyclerView.VERTICAL,
                false
            )
        flowerRecyclerView.layoutManager = flowerLayoutManager

        flowerRecyclerView.addOnScrollListener(object :
            PaginationScrollListener(flowerLayoutManager) {
            override fun isLastPage(): Boolean {
                return presenter.isLastPage
            }

            override fun isLoading(): Boolean {
                return presenter.isLoading
            }

            override fun loadMoreItems() {
                presenter.isLoading = true
                presenter.getPaginationItems(searchView.text.toString())
            }
        })
    }

    private fun setListeners() {
        searchView.addTextChangedListener(object : CustomTextWatcher() {
            override fun afterTextChanged(text: Editable?) {
                if (!TextUtils.isEmpty(searchView.text)) {
                    presenter.searchFlowers(text.toString(), startingPage)
                } else {
                    presenter.getFlowersAfterSearch(startingPage)
                    flowerRecyclerView.scrollToPosition(recyclerStartPos)
                }
            }

        })
    }

    override fun setAdapterData(flowerList: MutableList<Flower>) {
        flowerAdapter.setData(flowerList)
    }

    override fun addAdapterData(flowerList: MutableList<Flower>) {
        flowerAdapter.addData(flowerList)
    }

    override fun searchFlowersFailed() {
        Toast.makeText(context, R.string.search_flowers_failed, Toast.LENGTH_SHORT).show()
    }

    override fun getFlowersFailed() {
        Toast.makeText(context, R.string.get_flowers_failed, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unbindView()
    }

}