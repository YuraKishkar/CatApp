package com.example.catapp.view.fragment.listFragment


import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.catapp.App
import com.example.catapp.R
import com.example.catapp.presenter.MainPresenter
import com.example.catapp.presenter.base.BasePresenter
import com.example.catapp.utils.ExpandCollapseViewUtil
import com.example.catapp.view.adapter.CatsItemsAdapter
import com.example.catapp.view.base.fragment.BaseFragment
import com.example.catapp.view.interfaces.IMainFragmet
import com.google.android.material.floatingactionbutton.FloatingActionButton
import javax.inject.Inject


class MainFragment : BaseFragment(), IMainFragmet {


    @Inject
    lateinit var mPresener: MainPresenter

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mCatsItemsAdapter: CatsItemsAdapter
    private lateinit var mFloatingActionButton: FloatingActionButton

    override fun getPresenter(): BasePresenter = mPresener

    override fun getLayoutResId() = R.layout.fragment_main

    init {
        App.instance.getComponent().inject(this)
    }

    override fun onCreateView(view: View) {
        super.onCreateView(view)
        mRecyclerView = view.findViewById(R.id.rv_cats)
        mFloatingActionButton = view.findViewById(R.id.fab_favourite)
        mFloatingActionButton.setOnClickListener {
        findNavController().navigate(R.id.action_mainFragment_to_favouriteFragment)
        }
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
            CatsItemsAdapter { item: CatsItemData, view: View, position: Int ->
                mPresener.addToFavouriteCat(item)
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
