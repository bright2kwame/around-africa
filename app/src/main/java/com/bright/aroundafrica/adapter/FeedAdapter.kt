package com.bright.aroundafrica.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bright.aroundafrica.R
import com.bright.aroundafrica.model.LocalPlace
import com.bright.aroundafrica.util.AdapterCallback
import com.squareup.picasso.Picasso


/**
 * Created by Monarchy on 09/10/2017.
 */

class FeedAdapter(private var items: List<Any>, private val adapterCallback: AdapterCallback) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val constant = 200


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var viewHolder: RecyclerView.ViewHolder? = null
        val inflater = LayoutInflater.from(viewGroup.context)
        when (viewType) {
            constant -> {
                val viewHolderItem = inflater.inflate(R.layout.local_place_item, viewGroup, false)
                viewHolder = ViewHolderLocalPlace(viewHolderItem)
            }
        }
        return viewHolder!!
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            constant -> {
                val viewHolderLocalPlace = holder as ViewHolderLocalPlace
                configureQuickSaleViewHolder(viewHolderLocalPlace, items[position] as LocalPlace)
            }
        }
    }

    override fun getItemCount(): Int {
        return this.items.size
    }

    override fun getItemViewType(position: Int): Int {
        return when {
            items[position] is LocalPlace -> constant
            else -> -1
        }
    }

    private fun configureQuickSaleViewHolder(viewHolder: ViewHolderLocalPlace, data: LocalPlace) {
        val parent = viewHolder.itemView

        viewHolder.textViewTitle?.text = data.id
        viewHolder.textViewContent?.text = data.title
        if (data.thumbnailUrl.isNotEmpty()) {
            Picasso.get().load(data.thumbnailUrl).resize(200, 200).centerCrop()
                .into(viewHolder.imageViewItem)
        }
        parent.setOnClickListener {
            adapterCallback.onActionPerformed(data, viewHolder.adapterPosition)
        }
    }
}
