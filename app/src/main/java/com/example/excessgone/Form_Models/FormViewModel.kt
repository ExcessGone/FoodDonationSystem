// Attached: FormViewModel.kt
// =============================================================================
//
// Programmer: Nour Siwar
// Class: Android Development
// Instructor: CodePath
//
// =============================================================================
// Program: FormViewModel class
// =============================================================================
// Description:
// This class communicates with the repository FormRepository that
// will use history fragment to communicate with our view model to aid
// in loading information from firebase.
// =============================================================================

package com.example.excessgone.Form_Models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.excessgone.Repository.FormRepository

class FormViewModel : ViewModel() {

    // declaring and assigning variables
    private val repository : FormRepository = FormRepository().getInstance()
    private val all_forms = MutableLiveData<List<Forms>>()


    // live data object that will be used inside HistoryFragment
    val allForms : LiveData<List<Forms>> = all_forms


    init{
        // get instance of repository
        // call loadForms from FormsRepository
        repository.loadForms(all_forms)
    }
}