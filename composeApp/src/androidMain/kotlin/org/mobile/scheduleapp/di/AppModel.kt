package org.mobile.scheduleapp.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import org.mobile.scheduleapp.screens.authScreens.AuthViewModel

val appModule = module{
    viewModel{ AuthViewModel() }
}