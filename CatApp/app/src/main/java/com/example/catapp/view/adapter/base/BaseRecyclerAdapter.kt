package com.example.catapp.view.adapter.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.catapp.R
import com.example.catapp.utils.extensions.expand

abstract class BaseRecyclerAdapter<T, VH : RecyclerView.ViewHolder>(
    onItemClick: (T, View, Int) -> Unit,
    onItemDownloadClick: (T, View, Int) -> Unit
) :
    RecyclerView.Adapter<VH>() {

    private val items = arrayListOf<T>()
    protected abstract fun getLayoutResId(): Int
    private lateinit var mLayoutInflater: LayoutInflater

    protected var mOnItemClickListener: (T, View, Int) -> Unit = onItemClick
    protected var mOnItemDownloadClick: (T, View, Int) -> Unit = onItemDownloadClick

    init {
        setHasStableIds(true)
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        mLayoutInflater = LayoutInflater.from(recyclerView.context)
    }

    fun insertAll(items: List<T>) {
        this.items.clear()
        items.let {
            this.items.addAll(items)
        }
        notifyDataSetChanged()
    }

    fun getItems(position: Int): T? =
        if (position >= 0 && position < items.size) items[position] else null


    override fun onBindViewHolder(holder: VH, position: Int) {
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH =
        viewHolder(mLayoutInflater.inflate(getLayoutResId(), parent, false), viewType)

    abstract fun viewHolder(view: View, viewType: Int): VH

    override fun getItemCount(): Int = items.size

    fun removeItem(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
//        val itemCount = itemCount - position
        notifyItemRangeChanged(position, itemCount)
    }

}


