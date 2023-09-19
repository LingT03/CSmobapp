package msudenver.edu.sharepreferences

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

// Key for shared preferences
const val KEY_TEXT = "keyText"


class PreferenceWrapper(private val sharedPreferences: SharedPreferences) {
    private val textLiveData = MutableLiveData<String>()

    init {
        sharedPreferences.registerOnSharedPreferenceChangeListener { _, key ->
            // When key is KEY_TEXT, post the new value to the textLiveData
            when (key) {
                KEY_TEXT -> {
                    textLiveData.postValue(sharedPreferences.getString(KEY_TEXT, ""))
                }
            }
        }
    }

    // Function to save text to shared preferences
    fun saveText(text: String) {
        sharedPreferences.edit()
            .putString(KEY_TEXT, text)
            .apply()
    }

    // Function to get text from shared preferences
    fun getText(): LiveData<String> {
        textLiveData.postValue(sharedPreferences.getString(KEY_TEXT, ""))
        return textLiveData
    }
}
