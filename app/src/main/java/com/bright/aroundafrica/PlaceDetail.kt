package com.bright.aroundafrica

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.bright.aroundafrica.model.LocalPlace
import com.bright.aroundafrica.util.AppConstants
import com.bright.aroundafrica.viewModel.LocalPlaceViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_place_detail.*


class PlaceDetail : AppCompatActivity() {

    private var viewModel: LocalPlaceViewModel? = null


    companion object {
        const val locationId = "ID"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_place_detail)

        setSupportActionBar(topAppBar as Toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "PLACE"

        circularProgressBar?.visibility = View.VISIBLE
        viewModel = ViewModelProvider(this).get(LocalPlaceViewModel::class.java)
        viewModel?.getLocalPlace(intent.getStringExtra(locationId)!!)
        viewModel?.localPlace?.observe(this, {
            circularProgressBar?.visibility = View.GONE
            if (it != null) {
                this.updateUI(it)
            } else {
                Toast.makeText(this, "Location not found", Toast.LENGTH_SHORT).show()
            }

        })

    }

    private fun updateUI(locationPlace: LocalPlace) {
        textViewTitle.text = locationPlace.id
        textViewContent.text = locationPlace.title
        if (locationPlace.url.isNotEmpty()) {
            Picasso.get().load(locationPlace.url).resize(600, 600).centerCrop()
                .into(imageViewItem)
        }

        val qrUrl = AppConstants.baseQRCodeApi.plus(AppConstants.createQRCode).plus(locationPlace.id)
        Log.e("URL",qrUrl)
        Picasso.get()
            .load(qrUrl)
            .resize(200, 200).centerCrop()
            .into(imageViewItemQR)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}