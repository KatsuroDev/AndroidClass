package ca.qc.cstj.s05localdatasource.data.repositories

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import ca.qc.cstj.s05localdatasource.domain.models.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore("s05.user")

class UserRepository(private val context:Context) {
    object PreferencesKey {
        val FIRST_NAME = stringPreferencesKey("first_name")
        val LAST_NAME = stringPreferencesKey("last_name")
        val IS_ONLINE = booleanPreferencesKey("is_online")
    }

    val user: Flow<User> = context.dataStore.data.map { preferences ->
        val firstName = preferences[PreferencesKey.FIRST_NAME] ?: ""
        val lastName = preferences[PreferencesKey.LAST_NAME] ?: ""
        val isOnline = preferences[PreferencesKey.IS_ONLINE] ?: false

        User(firstName, lastName, isOnline)
    }

    suspend fun save(firstName:String, lastName:String, isOnline:Boolean) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKey.FIRST_NAME] = firstName
            preferences[PreferencesKey.LAST_NAME] = lastName
            preferences[PreferencesKey.IS_ONLINE] = isOnline
        }
    }
}