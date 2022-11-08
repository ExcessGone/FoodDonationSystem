/*package com.example.excessgone

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import android.webkit.MimeTypeMap
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class Cameras : AppCompatActivity() {
    var selectedImage: ImageView? = null
    lateinit var subBtn: Button
    var currentPhotoPath: String? = null
    var storageReference: StorageReference? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)
        selectedImage = findViewById(R.id.imageFood)
        subBtn = findViewById(R.id.submitBtn)
        storageReference = FirebaseStorage.getInstance().reference
        subBtn.setOnClickListener({ askCameraPermissions() })
    }

    private fun askCameraPermissions() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) ActivityCompat.requestPermissions(
            this, arrayOf(
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ), CAMERA_PERM_CODE
        ) else {
            dispatchTakePictureIntent()
        }
    }

    private fun dispatchTakePictureIntent() {}
    fun onRequestPermissionResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantsResults: IntArray
    ) {
        if (requestCode === CAMERA_PERM_CODE) {
            if (grantsResults.size > 0 && grantsResults[0] == PackageManager.PERMISSION_GRANTED) {
                dispatchTakePictureIntent()
            } else {
                Toast.makeText(
                    this,
                    "Camera Permission is Required to Use Camera",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CAMERA_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                val f = File(currentPhotoPath)
                selectedImage!!.setImageURI(Uri.fromFile(f))
                Log.d("tag", "ABsolute URl of Image is " + Uri.fromFile(f))
                val mediaScanIntent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
                val contentUri = Uri.fromFile(f)
                mediaScanIntent.data = contentUri
                this.sendBroadcast(mediaScanIntent)
                uploadImageToFirebase(f.name, contentUri)
            }
        }
    }

    private fun uploadImageToFirebase(name: String, contentUri: Uri) {
        val image = storageReference!!.child("pictures/$name")
        image.putFile(contentUri).addOnSuccessListener {
            image.downloadUrl.addOnSuccessListener { uri ->
                Log.d(
                    "XXXX",
                    "onSuccess: Uploaded Image URI is $uri"
                )
            }
            Toast.makeText(this@Cameras, "image is uploaded", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(this@Cameras, "upload failed", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getFileExt(contentUri: Uri): String? {
        val c = contentResolver
        val mime = MimeTypeMap.getSingleton()
        return mime.getExtensionFromMimeType(c.getType(contentUri))
    }

    @Throws(IOException::class)
    fun createImageFile(): File {

        // create an image file name
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val imageFileName = "JPEG_" + timeStamp + "_"
        val storageDir =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
        val image: File = File.createTempFile(
            imageFileName,  // prefix
            ".jpg",  // suffix
            storageDir // directory
        )

        //save file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.absolutePath
        return image
    }

    companion object {
        const val CAMERA_PERM_CODE = 101
        const val CAMERA_REQUEST_CODE = 102
        const val GALLERY_REQUEST_CODE = 105
    }
}*/