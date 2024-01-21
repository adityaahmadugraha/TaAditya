package com.aditya.appsjeruk.data.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.aditya.appsjeruk.utils.Constant
import com.aditya.appsjeruk.utils.Constant.PREF_NAME
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.userStore by preferencesDataStore(PREF_NAME)

class UserPreference(context: Context) {
    private val userDataStore = context.userStore
    fun getUser(): Flow<UserLocal> {
        return userDataStore.data.map { preferences ->
            val userLocal = UserLocal(
                preferences[Constant.ID] ?: "",
                preferences[Constant.NAME] ?: "",
                preferences[Constant.USERNAME] ?: "",
                preferences[Constant.ROLES] ?:"",

            )
            userLocal

        }
    }

    suspend fun saveUser(user: UserLocal) {
        userDataStore.edit { preferences ->
            preferences[Constant.ID] = user.id
            preferences[Constant.NAME] = user.name
            preferences[Constant.USERNAME] = user.username
            preferences[Constant.ROLES] = user.roles

        }
    }


    suspend fun deleteUser() {
        userDataStore.edit { preferences ->
            preferences.clear()
        }

    }
}