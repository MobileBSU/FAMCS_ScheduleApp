package org.mobile.scheduleapp.presentation.screens.authScreens.signup

interface SignUpController {
    fun signUp()
    fun updateUsername(input: String)
    fun updateEmail(input: String)
    fun updatePassword(input: String)
    fun updateIsTermsAccepted(input: Boolean)
    fun updateConfirmedPassword(input: String)
}