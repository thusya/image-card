package com.thusee.imagecard.ui.util

import com.thusee.imagecard.BuildConfig

fun getImageUrl(url: String): String {
    return BuildConfig.API_BASE_URL.trimEnd('/') + "/" + url.trimStart('/')
}