package com.example.catapp.view.adapter

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.catapp.R
import com.example.catapp.view.fragment.listFragment.CatsItemData

class CatsItemsAdapter(onItemClickListener: OnItemClickListener<CatsItemData>) :
    BaseRecyclerAdapter<CatsItemData, CatsItemsAdapter.ViewHolder>(onItemClickListener) {

    class ViewHolder(itemView: View, onItemClickListener: OnItemClickListener<CatsItemData>) :
        RecyclerView.ViewHolder(itemView) {
        private lateinit var item: CatsItemData
        private val mImageView = itemView.findViewById<ImageView>(R.id.iv_cat)

        init {
            mImageView.setOnClickListener {
                onItemClickListener.onClick(item, it)
            }
        }

        fun bind(item: CatsItemData) {
            this.item = item
            Glide.with(itemView)
                .load(item.url)
                .apply {
                    centerCrop()
                    into(mImageView)
                }

        }
    }

    override fun getLayoutResId(): Int = R.layout.item_cat


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItems(position)?.let {
            holder.bind(it)
        }
    }

    override fun viewHolder(view: View, viewType: Int): ViewHolder = ViewHolder(view, mOnItemClickListener)
}