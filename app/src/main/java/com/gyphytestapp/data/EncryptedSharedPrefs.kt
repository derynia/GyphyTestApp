package com.gyphytestapp.data

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

// Repository saving and getting encrypted data
class EncryptedSharedPrefs @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

    private val sharedPreferences = EncryptedSharedPreferences.create(
        PREFS_NAME,
        masterKeyAlias,
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    var accessKey: String?
        get() = getByKey(ACCESS_KEY)
        set(key) = putPrefByKey(ACCESS_KEY, key!!)

    private fun putPrefByKey(key: String, value: String) {
        with(sharedPreferences.edit()) {
            putString(key, value)
            this.apply()
        }
    }

    private fun getByKey(key: String) : String? = sharedPreferences.getString(key,null)

    companion object {
        private const val PREFS_NAME = "Enc"
        private const val ACCESS_KEY = "AccesKey"
    }
}