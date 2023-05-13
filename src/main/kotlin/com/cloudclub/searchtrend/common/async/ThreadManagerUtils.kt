package com.cloudclub.searchtrend.common.async

import org.springframework.scheduling.concurrent.CustomizableThreadFactory
import java.time.ZonedDateTime
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * ThreadManagerUtils
 * - Thread 관리
 */
class ThreadManagerUtils {
    companion object {
        /**
         * getAllThreadsInfo
         * - key : Thread Name, value : Thread
         */
        fun getAllThreadsInfo(threadNamePrefix: String? = null): Map<String, Thread> {
            if (threadNamePrefix.isNullOrEmpty()) {
                return Thread.getAllStackTraces()
                    .map { it.key }
                    .associateBy { it.name }
            }

            return Thread.getAllStackTraces()
                .map { it.key }
                .filter { it.name.contains(threadNamePrefix) }
                .associateBy { it.name }
        }

        /** generate Executor Service */
        fun generateExecutor(threadNamePrefix: String, poolSize: Int): ExecutorService {
            /** ThreadNamePrefix 설정 */
            val threadFactory = CustomizableThreadFactory(
                "${threadNamePrefix}-${ZonedDateTime.now().toEpochSecond()}-"
            )

            return Executors.newFixedThreadPool(poolSize, threadFactory)
        }
    }
}
