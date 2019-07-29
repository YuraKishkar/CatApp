package com.example.catapp.view.adapter

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.catapp.R
import com.example.catapp.utils.extensions.expand
import com.example.catapp.utils.extensions.loadImageFromUrl
import com.example.catapp.view.adapter.base.BaseRecyclerAdapter
import com.example.catapp.view.fragment.listFragment.CatsItemData

class CatsItemsAdapter(
    onItemClickListener: (item: CatsItemData, view: View, position: Int) -> Unit,
    onItemDownloadClick: (item: CatsItemData, view: View, position: Int) -> Unit
) :
    BaseRecyclerAdapter<CatsItemData, CatsItemsAdapter.ViewHolder>(onItemClickListener, onItemDownloadClick) {

    class ViewHolder(
        itemView: View,
        val onItemClickListener: (item: CatsItemData, view: View, position: Int) -> Unit,
        val onItemDownloadClick: (item: CatsItemData, view: View, position: Int) -> Unit
    ) :
        RecyclerView.ViewHolder(itemView) {
        private lateinit var item: CatsItemData
        private val mImageView = itemView.findViewById<ImageView>(R.id.iv_cat)
        private val mLlArrow = itemView.findViewById<LinearLayout>(R.id.ll_arrow_title)
        private val mLlExpandable = itemView.findViewById<LinearLayout>(R.id.ll_expandable)
        private val mBtnFavourite = itemView.findViewById<Button>(R.id.btn_favourite)
        private val mIvArrow = itemView.findViewById<ImageView>(R.id.iv_arrow)
        private val mBtnDownload = itemView.findViewById<Button>(R.id.btn_download)


        fun bind(item: CatsItemData, position: Int) {
            this.item = item
            mImageView.loadImageFromUrl(itemView, item.url)
            mLlArrow.setOnClickListener {
                mLlArrow.expand(mLlExpandable, mIvArrow)
            }
            mBtnFavourite.setOnClickListener {
                onItemClickListener(item, it, position)
                it.isEnabled = false
            }

            mBtnDownload.setOnClickListener {
                onItemDownloadClick(item, it, position)
            }
        }
    }

    override fun getLayoutResId(): Int = R.layout.item_cat


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        getItems(position)?.let {
            holder.bind(it, position)
        }
    }


    override fun viewHolder(view: View, viewType: Int): ViewHolder =
        ViewHolder(view, mOnItemClickListener, mOnItemDownloadClick)
}