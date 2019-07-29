package com.example.catapp.utils.helpers

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Environment
import androidx.core.content.ContextCompat
import com.example.catapp.App
import io.reactivex.Observable
import java.io.BufferedInputStream
import java.io.File
import java.io.FileOutputStream
import java.net.HttpURLConnection
import java.net.URL
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ImageSaveHelper @Inject constructor() {

    @Inject
    lateinit var mContext: Context

    init {
        App.instance.getComponent().inject(this)
    }

    companion object {
        val FOLDER_NAME = "Downloads"
        val FILE_NAME_POSTFIX = ".jpg"

    }

    @SuppressLint("CheckResult")
    fun downloadImage(id: String, url: String): Observable<String> {
        return Observable.just(id)
            .map {
                synchronized(this) {
                    if (!isExternalStorageWritable()) {
                        return@map null
                    }
                    val urlConnection = URL(url)
                    val http = urlConnection.openConnection() as HttpURLConnection
                    http.connect()
                    val inputStream = http.inputStream
                    val bufferedInputStream = BufferedInputStream(inputStream)

                    val bitmap = BitmapFactory.decodeStream(bufferedInputStream)
                    val file = createFile(it)
                    val outputStream = FileOutputStream(file)
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
                    outputStream.flush()
                    outputStream.close()

                    return@map file.path

                }
            }
    }


    private fun createFile(id: String): File {
        val fileStorage =
            File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString())
        if (!fileStorage.exists()) {
            fileStorage.createNewFile()
        }
        val fileName = id + "_" + System.currentTimeMillis() + FILE_NAME_POSTFIX
        return File(fileStorage, fileName)
    }


    fun hasPermission(): Boolean = (ContextCompat.checkSelfPermission(
        mContext,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    ) == PackageManager.PERMISSION_GRANTED)

    private fun isExternalStorageWritable(): Boolean {
        val extStorageState = Environment.getExternalStorageState()
        return Environment.MEDIA_MOUNTED == extStorageState
    }


}


