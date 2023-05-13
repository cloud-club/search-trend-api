package com.cloudclub.searchtrend.common.extension

import com.cloudclub.searchtrend.config.environment.EnvironmentType.PROFILE_PROD
import com.cloudclub.searchtrend.config.environment.EnvironmentType.PROFILE_STAGING
import com.cloudclub.searchtrend.config.environment.EnvironmentType.PROFILE_TEST
import org.springframework.core.env.Environment

fun Environment.isProd(): Boolean {
    return this.activeProfiles.any { it.equals(PROFILE_PROD) }
}

fun Environment.isStaging(): Boolean {
    return this.activeProfiles.any { it.equals(PROFILE_STAGING) }
}

fun Environment.isTest(): Boolean {
    return this.activeProfiles.any { it.equals(PROFILE_TEST) }
}
