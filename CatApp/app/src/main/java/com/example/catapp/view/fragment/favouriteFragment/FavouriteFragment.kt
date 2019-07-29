package com.example.catapp.view.fragment.favouriteFragment


import android.Manifest
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.catapp.App
import com.example.catapp.R
import com.example.catapp.presenter.FavouritePresenter
import com.example.catapp.presenter.base.interfaces.IBasePresenter
import com.example.catapp.view.adapter.CatsFavouriteAdapter
import com.example.catapp.view.base.fragment.BaseFragment
import com.example.catapp.view.interfaces.IFavouriteFragment
import javax.inject.Inject


class FavouriteFragment : BaseFragment(), IFavouriteFragment {


    @Inject
    lateinit var mPresenter: FavouritePresenter

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mCatsItemsFavouriteAdapter: CatsFavouriteAdapter


    override fun getLayoutResId(): Int = R.layout.fragment_favourite

    override fun getPresenter(): IBasePresenter = mPresenter


    init {
        App.instance.getComponent().inject(this)
    }

    override fun onCreateView(view: View) {
        super.onCreateView(view)
        mRecyclerView = view.findViewById(R.id.rv_cats_favourite)
        initRecyclerView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPresenter.onCreate(this)
    }


    override fun showResults(list: List<FavouriteEntity>) {
        mCatsItemsFavouriteAdapter.insertAll(list)
    }

    private fun initRecyclerView() {
        mRecyclerView.layoutManager = LinearLayoutManager(context)
        mRecyclerView.setHasFixedSize(true)
        mCatsItemsFavouriteAdapter =
            CatsFavouriteAdapter(onItemClickListener = { item: FavouriteEntity, view: View, position: Int ->
                mPresenter.deleteFavouriteCat(item, position)
            },
                onItemDownloadClick = { item: FavouriteEntity, view: View, position: Int ->
                    mPresenter.downloadImage(item.id, item.url)
                }
            )
        mRecyclerView.adapter = mCatsItemsFavouriteAdapter

    }


    override fun updateUI(position: Int) {
        mCatsItemsFavouriteAdapter.removeItem(position)
    }

}
