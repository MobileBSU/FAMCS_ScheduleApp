package org.mobile.scheduleapp.di

import androidx.datastore.core.DataStoreFactory
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import org.mobile.scheduleapp.common.datastore.UserSettingsSerializer
import org.mobile.scheduleapp.presentation.MainActivityViewModel
import org.mobile.scheduleapp.presentation.screens.authScreens.AuthViewModel
import org.mobile.scheduleapp.presentation.screens.authScreens.login.LoginViewModel
import org.mobile.scheduleapp.presentation.screens.authScreens.signup.SignUpViewModel
import org.mobile.scheduleapp.presentation.screens.searchScreens.groupSearchScreen.GroupSearchViewModel
import org.mobile.scheduleapp.presentation.screens.searchScreens.lecturersSearchScreen.LecturerSearchViewModel
import java.io.File

val appModule = module{
    viewModel{ AuthViewModel() }
    viewModel { SignUpViewModel(get(), get()) }
    viewModel { LoginViewModel(get(), get())}
    viewModel { MainActivityViewModel(get()) }
    viewModel { GroupSearchViewModel(get(), get()) }
    viewModel { LecturerSearchViewModel(get(), get())}


    single {
        DataStoreFactory.create(
            serializer = UserSettingsSerializer,
            produceFile = { File(androidContext().filesDir, "app_user_settings") }
        )
    }

}