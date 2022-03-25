package ca.qc.cstj.consortium.data.repositories

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import ca.qc.cstj.consortium.core.Constants
import ca.qc.cstj.consortium.domain.models.Trader
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore("consortium.trader")

class TraderRepository(private val context: Context) {
    object PreferencesKey {
        val NAME = stringPreferencesKey("name")
        val ZUSCUM = floatPreferencesKey("zuscum")
        val WUSNYX = floatPreferencesKey("wusnyx")
        val JASMALT = floatPreferencesKey("jasmalt")
        val IASPYX = floatPreferencesKey("iaspyx")
        val BLIERIUM = floatPreferencesKey("blierium")
    }

    val trader: Flow<Trader> = context.dataStore.data.map { preferences ->
        val name = preferences[PreferencesKey.NAME] ?: ""
        val zuscum = preferences[PreferencesKey.ZUSCUM] ?: Constants.defaultElementAmount
        val wusnyx = preferences[PreferencesKey.WUSNYX] ?: Constants.defaultElementAmount
        val jasmalt = preferences[PreferencesKey.JASMALT] ?: Constants.defaultElementAmount
        val iaspyx = preferences[PreferencesKey.IASPYX] ?: Constants.defaultElementAmount
        val blierium = preferences[PreferencesKey.BLIERIUM] ?: Constants.defaultElementAmount

        Trader(name, zuscum, wusnyx, jasmalt, iaspyx, blierium)
    }

    suspend fun save(name:String,
                     zuscum:Float,
                     wusnyx:Float,
                     jasmalt:Float,
                     iaspyx:Float,
                     blierium:Float) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKey.NAME] = name
            preferences[PreferencesKey.ZUSCUM] = zuscum
            preferences[PreferencesKey.WUSNYX] = wusnyx
            preferences[PreferencesKey.JASMALT] = jasmalt
            preferences[PreferencesKey.IASPYX] = iaspyx
            preferences[PreferencesKey.BLIERIUM] = blierium
        }
    }
}