package org.mobile.scheduleapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.compose.koinViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.startKoin
import org.mobile.scheduleapp.di.appModule
import org.mobile.scheduleapp.di.getSharedModules
import org.mobile.scheduleapp.presentation.view.navigation.AppNavigation
import org.mobile.scheduleapp.presentation.view.theming.ScheduleAppTheme

class MainActivity(
) : ComponentActivity(
) {
    private val viewModel: MainActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startKoin {
            androidContext(this@MainActivity)
            modules(appModule + getSharedModules())
        }

        setContent {
            ScheduleAppTheme {
                val token = viewModel.authState.collectAsStateWithLifecycle(initialValue = null)
                AppNavigation(token.value)
            }
        }
    }
}