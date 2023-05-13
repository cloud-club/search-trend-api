package com.cloudclub.searchtrend.common.async

import org.slf4j.MDC
import org.springframework.core.task.TaskDecorator

class AsyncTaskDecorator : TaskDecorator {
    override fun decorate(runnable: Runnable): Runnable {
        return Runnable {
            MDC.getCopyOfContextMap()?.let { MDC.setContextMap(it) }
            runnable.run()
        }
    }
}
