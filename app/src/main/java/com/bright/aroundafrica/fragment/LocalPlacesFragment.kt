package com.bright.aroundafrica.fragment

import android.content.Context
import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bright.aroundafrica.PlaceDetail
import com.bright.aroundafrica.R
import com.bright.aroundafrica.adapter.FeedAdapter
import com.bright.aroundafrica.model.LocalPlace
import com.bright.aroundafrica.util.AdapterCallback
import com.bright.aroundafrica.viewModel.LocalPlaceViewModel
import kotlinx.android.synthetic.main.fragment_local_places.*


class LocalPlacesFragment : Fragment() {

    private val feedItems = ArrayList<LocalPlace>()
    private var baseAdapter: FeedAdapter? = null
    private var viewModel: LocalPlaceViewModel? = null

    private var onItemClicked = object : AdapterCallback {
        override fun onActionPerformed(data: Any, position: Int) {
            val intent = Intent(requireContext(), PlaceDetail::class.java)
            intent.putExtra(PlaceDetail.locationId, (data as LocalPlace).id)
            requireContext().startActivity(intent)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        baseAdapter = FeedAdapter(feedItems, onItemClicked)
        recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = baseAdapter
        circularProgressBar.visibility = View.VISIBLE

        viewModel?.getLocalPlaces()
        viewModel?.localPlaceList?.observe(requireActivity(), {
            circularProgressBar.visibility = View.INVISIBLE
            feedItems.addAll(it)
            baseAdapter?.notifyDataSetChanged()
        })
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_local_places, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            LocalPlacesFragment().apply {

            }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProvider(this).get(LocalPlaceViewModel::class.java)
    }
}