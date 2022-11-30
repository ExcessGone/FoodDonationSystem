// Attached: DetailActivity.kt
// =============================================================================
//
// Programmer: Nour Siwar
// Class: Android Development
// Instructor: CodePath
//
// =============================================================================
// Program: DetailActivity class
// =============================================================================
// Description:
// This class is the one responsible for communicating with activity_detail.xml.
// When the user presses on any form loaded in recyclerview, this class is in action.
// =============================================================================

package com.example.excessgone

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // call function
        setValuesToViews()
    }

    // This is to communicate with the Detail Activity's layout,
    // so that all information about the shelter transfers to the Detailed part of each form.
    private fun setValuesToViews(){
        homelessShelterName.text=intent.getStringExtra("name")
        foodType.text=intent.getStringExtra("food")
        shelterNumber.text=intent.getStringExtra("phone")
        shelterLocation.text=intent.getStringExtra("address")
    }
}