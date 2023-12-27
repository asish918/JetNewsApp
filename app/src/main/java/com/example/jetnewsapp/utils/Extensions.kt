package com.example.jetnewsapp.utils

import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.os.Parcelable
import java.net.URLDecoder
import java.net.URLEncoder

fun decode(url: String?) = URLDecoder.decode(url ?: "", "UTF-8")
fun encode(url: String?) = URLEncoder.encode(url ?: "", "UTF-8")
