// Attached: FirebaseStorageManager.kt
// =============================================================================
//
// Programmer: Nour Siwar
// Class: Android Development
// Instructor: CodePath
//
// =============================================================================
// Program: FirebaseStorageManager class
// =============================================================================
// Description:
// Note: This class needs to be worked on as the Camera picture is overriden
//
// This class takes care of the Camera API, and is responsible for uploading
// the image, making sure it gets to Firebase DB
// =============================================================================
package com.example.excessgone.Repository

import android.app.ProgressDialog
import android.content.Context
import android.net.Uri
import android.util.Log
import com.google.firebase.storage.FirebaseStorage


class FirebaseStorageManager {
    private val mStorageRef = FirebaseStorage.getInstance().reference
    private lateinit var mProfessDialog : ProgressDialog
    private val TAG = "FirebaseStorageManager"

    fun uploadImage(mContext: Context, imageURI: Uri){

        mProfessDialog = ProgressDialog(mContext)
        mProfessDialog.setMessage("Please wait, image is uploading....")
        val uploadTask = mStorageRef.child("forms/imageFood.png").putFile(imageURI)
        uploadTask.addOnSuccessListener {
            //success test case
            Log.e(TAG, "IMAGE UPLOADED")
        }.addOnFailureListener{
            // failed test case
            Log.e(TAG, "IMAGE FAILED")
        }
    }
}