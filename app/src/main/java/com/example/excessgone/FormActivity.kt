package com.example.excessgone
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.excessgone.Repository.FirebaseStorageManager
import com.example.excessgone.Form_Models.Forms
import com.example.excessgone.databinding.ActivityFormBinding
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class FormActivity : AppCompatActivity() {

    companion object{
        const val REQUEST_FROM_CAMERA = 1001
    }

    private var storage: StorageReference? = null
    private lateinit var database : DatabaseReference
    private lateinit var binding : ActivityFormBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)

        binding = ActivityFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

      storage = FirebaseStorage.getInstance().reference
        database = FirebaseDatabase.getInstance().getReference("Forms")
        binding = ActivityFormBinding.inflate(layoutInflater)
        setContentView(binding.root)
       binding = DataBindingUtil.setContentView(this, R.layout.activity_form)

        initUI()
        binding.submitBtn.setOnClickListener {
            if (TextUtils.isEmpty(binding.textShelterNameField.text.toString())) {
                binding.textShelterNameInputLayout.error = "Enter Name"
            } else if (TextUtils.isEmpty(binding.textPhoneField.text.toString())) {
                binding.textShelterNameInputLayout.isErrorEnabled = false
                binding.textPhoneInputLayout.error = "Enter Phone"
            } else if (TextUtils.isEmpty(binding.textAddressField.text.toString())) {
                binding.textPhoneInputLayout.isErrorEnabled = false
                binding.textAddressInputLayout.error = "Enter Address"
            } else if (TextUtils.isEmpty(binding.textFoodField.text.toString())) {
                binding.textAddressInputLayout.isErrorEnabled = false
                binding.textFoodInputLayout.error = "Enter Food Type"
                Toast.makeText(this, "Add picture of the food.", Toast.LENGTH_SHORT).show()
            } else {
                binding.textFoodInputLayout.isErrorEnabled = false
                val shelterName = binding.textShelterNameField.text.toString()
                val phoneNum = binding.textPhoneField.text.toString()
                val address = binding.textAddressField.text.toString()
                val foodType = binding.textFoodField.text.toString()
                val form = Forms(shelterName, phoneNum, address, foodType)

                    database.child(shelterName).setValue(form).addOnSuccessListener {

                        binding.textShelterNameField.text?.clear()
                        binding.textPhoneField.text?.clear()
                        binding.textAddressField.text?.clear()
                        binding.textFoodField.text?.clear()

                        Toast.makeText(this, "Successfully Saved", Toast.LENGTH_SHORT).show()
                        val i = Intent(this@FormActivity, MainActivity::class.java)
                        startActivity(i)
                    }.addOnFailureListener {
                        Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == Activity.RESULT_OK){
            when(requestCode){
                REQUEST_FROM_CAMERA ->{
                    binding.imageFood.setImageURI(data!!.data)
                    FirebaseStorageManager().uploadImage(this, data.data!!)
                }
            }
        }
    }

    private fun initUI() {
        binding.pictureBtn.setOnClickListener{
            captureImageUsingCamera()
        }
    }

    private fun captureImageUsingCamera(){
        ImagePicker.with(this).cameraOnly().crop().start(REQUEST_FROM_CAMERA)
    }
}


