package com.example.catapp.view.fragment.listFragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.catapp.App
import com.example.catapp.R
import com.example.catapp.presenter.MainPresenter
import com.example.catapp.presenter.base.BasePresenter
import com.example.catapp.presenter.base.interfaces.IBasePresenter
import com.example.catapp.utils.ExpandCollapseViewUtil
import com.example.catapp.view.adapter.CatsItemsAdapter
import com.example.catapp.view.base.fragment.BaseFragment
import com.example.catapp.view.interfaces.IMainFragmet
import javax.inject.Inject


class MainFragment : BaseFragment(), IMainFragmet {


    @Inject
    lateinit var mPresener: MainPresenter

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mCatsItemsAdapter: CatsItemsAdapter

    override fun getPresenter(): BasePresenter = mPresener

    override fun getLayoutResId() = R.layout.fragment_main

    init {
        App.instance.getComponent().inject(this)
    }

    override fun onCreateView(view: View) {
        super.onCreateView(view)
        mRecyclerView = view.findViewById(R.id.rv_cats)
        initRecyclerView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPresener.onCreate(this)
    }

    override fun showResults(listCats: List<CatsItemData>) {
        mCatsItemsAdapter.insertAll(listCats)
    }


    private fun initRecyclerView() {
        mRecyclerView.layoutManager = LinearLayoutManager(context)
        mRecyclerView.setHasFixedSize(true)
        mCatsItemsAdapter =
            CatsItemsAdapter { item: CatsItemData, view: View, expandableView: View, position: Int ->
                expandCollapse(
                    expandableView,
                    position
                )
            }
        mRecyclerView.adapter = mCatsItemsAdapter
    }


    private fun expandCollapse(expandable: View, position: Int) {
        when (expandable.visibility) {
            View.VISIBLE -> {
                collapseView(expandable)
                mCatsItemsAdapter.notifyItemChanged(position)
            }
            else -> {
                expandView(expandable)
                mCatsItemsAdapter.notifyItemChanged(position)
            }
        }

    }

    private fun collapseView(view: View) {
        val rotate = RotateAnimation(180f, 0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        rotate.duration = ExpandCollapseViewUtil.ANIMATION_DURATION
        ExpandCollapseViewUtil.collapseInLinearLayout(view)
    }

    private fun expandView(view: View) {
        val rotate = RotateAnimation(180f, 0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        rotate.duration = ExpandCollapseViewUtil.ANIMATION_DURATION
        ExpandCollapseViewUtil.expandInLinearLayout(view)
    }

}
