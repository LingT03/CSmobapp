package edu.msudenver.cs3013.project03

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit

/** Class for storing persisting data
 * */
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "favoritesStore")
val KEY_TEXT2 = stringPreferencesKey("key_text")
class FavoritesStore(private val context: Context) {
    val text: Flow<String> = context.dataStore.data.map { preferences -> preferences[KEY_TEXT2] ?: "" }

    //method to save text for data persistence
    suspend fun saveText(text: String) {
        context.dataStore.edit { preferences -> preferences[KEY_TEXT2] = text }
    }
}