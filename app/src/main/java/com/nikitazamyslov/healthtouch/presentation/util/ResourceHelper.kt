package com.nikitazamyslov.healthtouch.presentation.util

import android.content.Context
import androidx.annotation.StringRes
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ResourceHelper @Inject constructor(
    @ApplicationContext val context: Context
) {
    fun getStringResource(@StringRes id: Int): String {
        return context.resources.getString(id)
    }
}