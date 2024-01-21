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
//                preferences[Constant.PASSWORD] ?: "",
                preferences[Constant.ROLES] ?:"",
//                preferences[Constant.KEY_ROLES] ?: "",
//                preferences[Constant.KEY_ALAMAT] ?: "",
//                preferences[Constant.KEY_TOKEN] ?: ""


            )
            userLocal

        }
    }

    suspend fun saveUser(user: UserLocal) {
        userDataStore.edit { preferences ->
            preferences[Constant.ID] = user.id
            preferences[Constant.NAME] = user.name
            preferences[Constant.USERNAME] = user.username
//            preferences[Constant.PASSWORD] = user.password
            preferences[Constant.ROLES] = user.roles
//            preferences[Constant.KEY_ROLES] = user.roles
//            preferences[Constant.KEY_ALAMAT] = user.alamat
//            preferences[Constant.KEY_TOKEN] = user.token
//
//            preferences[Constant.ID] ?: "",
//            preferences[Constant.NAME] ?: "",
//            preferences[Constant.USERNAME] ?: "",
//            preferences[Constant.PASSWORD] ?: "",
//            preferences[Constant.ROLES] ?:"",
        }
    }


    suspend fun deleteUser() {
        userDataStore.edit { preferences ->
            preferences.clear()
        }

    }
}