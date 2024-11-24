package org.mobile.scheduleapp.presentation

import androidx.datastore.core.DataStore
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.map
import org.mobile.scheduleapp.common.datastore.UserSettings
import org.mobile.scheduleapp.common.datastore.toAuthResultData

class MainActivityViewModel(
    private val dataStore: DataStore<UserSettings>
): ViewModel() {
    val authState = dataStore.data.map { it.toAuthResultData().token }
}