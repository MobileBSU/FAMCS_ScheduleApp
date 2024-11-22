package org.mobile.scheduleapp.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import org.mobile.scheduleapp.presentation.screens.authScreens.AuthViewModel
import org.mobile.scheduleapp.presentation.screens.authScreens.login.LoginViewModel
import org.mobile.scheduleapp.presentation.screens.authScreens.signup.SignUpViewModel

val appModule = module{
    viewModel{ AuthViewModel() }
    viewModel { SignUpViewModel(get()) }
    viewModel { LoginViewModel(get())}

}