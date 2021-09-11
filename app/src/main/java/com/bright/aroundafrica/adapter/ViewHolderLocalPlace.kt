package com.bright.aroundafrica.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bright.aroundafrica.R


/**
 * Created by Monarchy on 09/10/2017.
 */

class ViewHolderLocalPlace(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var imageViewItem: ImageView? = null
    var textViewTitle: TextView? = null
    var textViewContent: TextView? = null


    init {
        imageViewItem = itemView.findViewById(R.id.imageViewItem)
        textViewTitle = itemView.findViewById(R.id.textViewTitle)
        textViewContent = itemView.findViewById(R.id.textViewContent)

    }
}
