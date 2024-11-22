package org.mobile.scheduleapp.di

import org.koin.dsl.module
import org.mobile.scheduleapp.auth.data.AuthRepositoryImpl
import org.mobile.scheduleapp.auth.data.AuthService
import org.mobile.scheduleapp.auth.domain.repository.AuthRepository
import org.mobile.scheduleapp.auth.domain.usecase.SignInUseCase
import org.mobile.scheduleapp.auth.domain.usecase.SignUpUseCase
import org.mobile.scheduleapp.common.util.provideDispatcher

private val authModule = module {
    single<AuthRepository> { AuthRepositoryImpl(get(), get()) }
    factory { AuthService() }
    factory { SignUpUseCase() }
    factory { SignInUseCase() }
}

private val utilityModule = module {
    factory { provideDispatcher() }
}

fun getSharedModules() = listOf(authModule, utilityModule)