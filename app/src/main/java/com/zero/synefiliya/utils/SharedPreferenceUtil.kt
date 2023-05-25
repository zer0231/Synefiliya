package com.zero.synefiliya.utils

import android.content.Context
import com.zero.synefiliya.utils.Constants.Companion.USER_DATA
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPreferenceUtil @Inject constructor(@ApplicationContext private val context: Context) {

    private val userSharedPreference =
        context.getSharedPreferences(USER_DATA, Context.MODE_PRIVATE)


//    fun setUserName(username: String) {
//        val editor = userSharedPreference.edit()
//        editor.putString(USERNAME_SP, username)
//        editor.apply()
//    }
//
//    fun getUserName(): String? {
//        return userSharedPreference.getString(USERNAME_SP, "*")
//    }

    fun storeBookmarked(movieId: Int) {
        val editor = userSharedPreference.edit()
        val movieSet: Set<String> = setOf("test", "test", "test")
        editor.putStringSet(movieId.toString(), movieSet)
        editor.apply()
    }

}