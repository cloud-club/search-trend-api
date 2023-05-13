package com.cloudclub.searchtrend.common.extension

import java.net.URLDecoder
import java.net.URLEncoder
import java.util.*

fun String.decodeBase64() = String(Base64.getDecoder().decode(this))

fun String.encodeURL(type: String = "UTF-8"): String = URLEncoder.encode(this, type)
fun String.decodeURL(type: String = "UTF-8"): String = URLDecoder.decode(this, type)

