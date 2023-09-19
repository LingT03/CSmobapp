package edu.msudenver.cs3013.project03

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

const val KEY_TEXT = "keyText"
/** Wrapper for LiveData. Has two methods for ssaving data and for getting data
 * */
class PreferenceWrapper(private val sharedPreferences: SharedPreferences) {
    private val textLiveData = MutableLiveData<String>()
    init {
        sharedPreferences.registerOnSharedPreferenceChangeListener { _, key ->
            when (key) {
                KEY_TEXT -> {
                    textLiveData.postValue(sharedPreferences.getString(KEY_TEXT, ""))
                }
            }
        }
    }
    //method for saving text
    fun saveText(text: String) {
        sharedPreferences.edit()
            .putString(KEY_TEXT, text)
            .apply()
    }
    //method for getting the text
    fun getText(): LiveData<String> {
        textLiveData.postValue(sharedPreferences.getString(KEY_TEXT, ""))
        return textLiveData
    }
}