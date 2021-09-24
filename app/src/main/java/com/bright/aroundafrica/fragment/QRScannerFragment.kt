package com.bright.aroundafrica.fragment

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.fragment.app.Fragment
import com.bright.aroundafrica.PlaceDetail
import com.bright.aroundafrica.R
import com.bright.aroundafrica.model.LocalPlace
import com.budiyev.android.codescanner.*
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.fragment_qr_scan.*


class QRScannerFragment : Fragment() {
    private lateinit var codeScanner: CodeScanner
    private val CAMERA_REQUEST_CODE = 100

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activity = requireActivity()
        codeScanner = CodeScanner(activity, scannerView)


        codeScanner.camera = CodeScanner.CAMERA_BACK
        codeScanner.formats = CodeScanner.ALL_FORMATS
        codeScanner.autoFocusMode = AutoFocusMode.SAFE
        codeScanner.scanMode = ScanMode.SINGLE
        codeScanner.isAutoFocusEnabled = true
        codeScanner.isFlashEnabled = false

        // Callbacks
        codeScanner.decodeCallback = DecodeCallback {
            activity.runOnUiThread {
                val resultIn = it.text
                proceedToCheckLocation(resultIn)
            }
        }
        codeScanner.errorCallback = ErrorCallback {
            activity.runOnUiThread {
                Toast.makeText(
                    requireContext(), "Camera initialization error: ${it.message}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }


        if (checkSelfPermission(
                activity,
                android.Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(android.Manifest.permission.CAMERA),
                CAMERA_REQUEST_CODE
            )
        } else {
            codeScanner.startPreview()
        }

    }

    private fun proceedToCheckLocation(text: String) {
        MaterialAlertDialogBuilder(
            requireContext(),
            R.style.ThemeOverlay_AppCompat
        )
            .setMessage(resources.getString(R.string.dialog_message, text))
            .setNegativeButton(resources.getString(R.string.decline)) { _, _ ->

            }
            .setPositiveButton(resources.getString(R.string.accept)) { _, _ ->
                val intent = Intent(requireContext(), PlaceDetail::class.java)
                intent.putExtra(PlaceDetail.locationId, text)
                requireContext().startActivity(intent)
            }
            .show()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CAMERA_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                codeScanner.startPreview()
            } else {
                Toast.makeText(requireContext(), "camera permission denied", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        codeScanner.startPreview()
    }

    override fun onPause() {
        codeScanner.releaseResources()
        super.onPause()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_qr_scan, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            QRScannerFragment().apply {

            }
    }
}