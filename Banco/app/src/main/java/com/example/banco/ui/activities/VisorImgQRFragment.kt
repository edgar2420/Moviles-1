package com.example.appmovilof.ui.activities

import android.content.ContentResolver
import android.content.ContentValues
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.appmovilof.R
import com.example.appmovilof.databinding.FragmentVisorImgQRBinding
import android.content.Intent


import androidx.core.content.FileProvider

import android.graphics.Bitmap

import android.os.AsyncTask
import android.os.Environment
import android.provider.MediaStore
import java.io.*
import android.graphics.drawable.BitmapDrawable
import androidx.core.graphics.drawable.toBitmap


class VisorImgQRFragment : Fragment() {

    private lateinit var binding: FragmentVisorImgQRBinding
    private var imgData: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentVisorImgQRBinding.inflate(inflater, container, false)

        return binding.root
    }

    companion object {

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(img: String) =
            VisorImgQRFragment().apply {
                arguments = Bundle().apply {
                    imgData = img.substringAfter(",")

                    println(imgData)
                }
            }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageBytes = Base64.decode(imgData, Base64.DEFAULT)
        val decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
        binding.imgQR.setImageBitmap(decodedImage)
        binding.button.setOnClickListener {
            val compartirtc = Intent(Intent.ACTION_SEND)
            compartirtc.type = "image/png"
            val valorestc = ContentValues()
            valorestc.put(MediaStore.Images.Media.TITLE, "IMG QR")
            valorestc.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
            val uritc = requireActivity().contentResolver.insert(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                valorestc
            )
            val outstreamtc: OutputStream?
            try {
                outstreamtc = requireActivity().contentResolver.openOutputStream(uritc!!)
                decodedImage.compress(Bitmap.CompressFormat.JPEG, 100, outstreamtc)
                outstreamtc!!.close()
            } catch (e: Exception) {
                System.err.println(e.toString())
            }
            compartirtc.putExtra(Intent.EXTRA_STREAM, uritc)
            startActivity(
                Intent.createChooser(
                    compartirtc,
                    getString(R.string.app_name)
                )
            )
        }

    }



}