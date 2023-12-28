package com.example.jetnewsapp.utils

import java.net.URLDecoder
import java.net.URLEncoder
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

fun decode(url: String?) = URLDecoder.decode(url ?: "", "UTF-8")
fun encode(url: String?) = URLEncoder.encode(url ?: "", "UTF-8")

fun dateFormatter(currentDate: LocalDate): String {
    val formatter = SimpleDateFormat("EEEE, MMMM d, yyyy", Locale.getDefault())
    return formatter.format(Date.from(currentDate.atStartOfDay(ZoneId.systemDefault()).toInstant()))
}

fun formatPubAt(inputDateTime: String): String {
    val parsedDateTime = LocalDateTime.parse(inputDateTime, DateTimeFormatter.ISO_DATE_TIME)
    val outputFormatter = DateTimeFormatter.ofPattern("MMMM d, yyyy")
    return parsedDateTime.format(outputFormatter)
}