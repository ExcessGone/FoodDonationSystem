package com.example.excessgone

import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.text.TextUtils
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.excessgone.databinding.ActivityFormBinding


class FormActivity : AppCompatActivity() {

    // variables
    private lateinit var binding : ActivityFormBinding
    lateinit var imageView: ImageView
    lateinit var button: ImageButton
    val REQUEST_IMAGE_CAPTURE = 100


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_form)
        initViews()

        imageView = findViewById(R.id.imageFood)
        button = findViewById(R.id.pictureBtn)

        button.setOnClickListener {
            val takePicI = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

            try {
                startActivityForResult(takePicI, REQUEST_IMAGE_CAPTURE)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(this, "Error: " + e.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }

    // this funtion validates the form, not allowing the user to submit an empty form.
    private fun initViews()
    {
        binding.submitBtn.setOnClickListener{ view ->
            if(TextUtils.isEmpty(binding.textNameField.text.toString())) {
                binding.textNameInputLayout.error = "Enter Name"
            }
            else if(TextUtils.isEmpty(binding.textPhoneField.text.toString()))
            {
                binding.textNameInputLayout.isErrorEnabled = false
                binding.textPhoneInputLayout.error = "Enter Phone"
            }
            else if(TextUtils.isEmpty(binding.textAddressField.text.toString()))
            {
                binding.textPhoneInputLayout.isErrorEnabled = false
                binding.textAddressInputLayout.error = "Enter Address"
            }
            else if(TextUtils.isEmpty(binding.textFoodField.text.toString()))
            {
                binding.textAddressInputLayout.isErrorEnabled = false
                binding.textFoodInputLayout.error = "Enter Food Type"
                Toast.makeText(this, "Add picture of the food.", Toast.LENGTH_SHORT).show()
            }
            else
            {
                binding.textFoodInputLayout.isErrorEnabled = false
                val i = Intent(this@FormActivity, MainActivity::class.java)
                startActivity(i)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            val imageBitmap = data?.extras?.get("data") as Bitmap
            imageView.setImageBitmap(imageBitmap)
        }
        else
        {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}