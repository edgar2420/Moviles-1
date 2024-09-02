package com.moviles.practicanavegacioncasera.repository

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.moviles.practicanavegacioncasera.ui.fragments.FormHamburguesaFragment
import java.io.File

object ImageController {
    fun selectPhotoFromGallery(activity: FormHamburguesaFragment, code: Int) {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        activity.startActivityForResult(intent, code)
    }

    fun saveImage(context: Context, id:Int, uri:Uri) {
        val image = File(context.filesDir, id.toString())
        val bytes = context.contentResolver.openInputStream(uri)?.readBytes()
        image.writeBytes(bytes!!)
    }

    fun getImage(context: Context, id:Int): Uri {
        val image = File(context.filesDir, id.toString())
        return Uri.fromFile(image)
    }
}