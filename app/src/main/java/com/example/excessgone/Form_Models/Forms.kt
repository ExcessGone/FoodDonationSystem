// Attached: Forms.kt
// =============================================================================
//
// Programmer: Nour Siwar
// Class: Android Development
// Instructor: CodePath
//
// =============================================================================
// Program: Forms data class
// =============================================================================
// Description:
//  This class holds all String information the user submits when they fill the form.
// =============================================================================

package com.example.excessgone.Form_Models


data class Forms(val shelterName : String? = null,
                 val phoneNumber : String? = null,
                 val address: String?=null,
                 val foodType : String?=null)
