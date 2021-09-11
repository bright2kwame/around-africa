package com.bright.aroundafrica.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.bright.aroundafrica.R
import com.bright.aroundafrica.adapter.FeedAdapter
import com.bright.aroundafrica.model.LocalPlace
import com.bright.aroundafrica.util.AdapterCallback
import kotlinx.android.synthetic.main.fragment_local_places.*


class LocalPlacesFragment : Fragment() {

    private val feedItems = ArrayList<LocalPlace>()
    private var baseAdapter: FeedAdapter? = null

    private var onItemClicked = object : AdapterCallback {
        override fun onActionPerformed(data: Any, position: Int) {

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        baseAdapter = FeedAdapter(feedItems, onItemClicked)
        recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = baseAdapter


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_local_places, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            LocalPlacesFragment().apply {

            }
    }
}