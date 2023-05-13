package com.cloudclub.searchtrend.common.extension

import javax.servlet.http.HttpServletRequest

/** client Ip를 확인하기 위한, Header Keys */
val header = mutableListOf(
    "X-Forwarded-For",
    "Proxy-Client-IP",
    "WL-Proxy-Client-IP",
    "HTTP_CLIENT_IP",
    "HTTP_X_FORWARDED_FOR"
)

/** HttpServletRequest의 Header를 Map<String, Any>로 Wrapping하여 반환한다. */
fun HttpServletRequest.getHeadersInfo(): Map<String, Any> {
    return this.headerNames
        .asSequence()
        .associateWith { this.getHeader(it) }
}

fun HttpServletRequest.getClientIpAddress(): String? {
    val headerKey = header.firstOrNull {
        !this.getHeader(it).isNullOrEmpty() && this.getHeader(it) != "/"
    } ?: return this.remoteAddr

    return this.getHeader(headerKey)
}
