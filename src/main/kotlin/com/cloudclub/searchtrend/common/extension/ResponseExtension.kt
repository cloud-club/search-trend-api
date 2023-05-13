package com.cloudclub.searchtrend.common.extension

import com.cloudclub.searchtrend.common.dto.ResponseDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

/** Wrap Response Ok */
fun <T> T.wrapOk() = ResponseEntity.ok(ResponseDto(this))

/** Wrap Response Created */
fun <T> T.wrapCreated() = ResponseEntity.status(HttpStatus.CREATED).body(ResponseDto(this))

/** Wrap Response Void */
fun Unit.wrapVoid() = ResponseEntity.noContent()
