package com.example.excessgone

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.excessgone.databinding.ActivityFormBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class FormActivity : AppCompatActivity() {

    private lateinit var binding : ActivityFormBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_form)
        initViews()
    }

    private fun initViews()
    {
        binding.submitBtn.setOnClickListener{ view ->
            if(TextUtils.isEmpty(binding.textNameField.toString())){
                binding.textNameInputLayout.error="Enter Name"
                }
            else if(TextUtils.isEmpty(binding.textEmailField.text.toString()))
            {
                binding.textNameInputLayout.isErrorEnabled = false
                binding.textEmailInputLayout.error = "Enter Email"
            }
            else if(TextUtils.isEmpty(binding.textPhoneField.text.toString()))
            {
                binding.textEmailInputLayout.isErrorEnabled = false
                binding.textPhoneInputLayout.error = "Enter Phone"
            }
            else if(TextUtils.isEmpty(binding.textAddressField.text.toString()))
            {
                binding.textPhoneInputLayout.isErrorEnabled = false
                binding.textAddressInputLayout.error = "Enter Address"
            }
            else
            {
                binding.textAddressInputLayout.isErrorEnabled = false
            }
        }
    }
}