package com.bright.aroundafrica.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bright.aroundafrica.R
import kotlinx.android.synthetic.main.fragment_about.*
import kotlinx.android.synthetic.main.layout_footer.*


class AboutFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonCall?.setOnClickListener {
            Toast.makeText(activity, "Placing call ...", Toast.LENGTH_SHORT).show()
        }

        buttonSendMessage?.setOnClickListener {
            Toast.makeText(activity, "Sending message ...", Toast.LENGTH_SHORT).show()
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            AboutFragment().apply {

            }
    }
}