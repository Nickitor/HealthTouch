package com.nikitazamyslov.healthtouch.presentation.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.job
import kotlin.coroutines.coroutineContext
import kotlin.time.Duration
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

val DEFAULT_PERIOD = 1.seconds
val DEFAULT_DURATION = 1.minutes

@Suppress("LongParameterList")
fun getTimer(
    period: Duration = DEFAULT_PERIOD,
    initialDelay: Duration = Duration.ZERO,
    duration: Duration = DEFAULT_DURATION,
    tickAction: suspend () -> Unit = { Unit },
    completeAction: suspend () -> Unit = { Unit },
    scope: CoroutineScope,
) = tickerFlow(
    period = period,
    initialDelay = initialDelay,
    duration = duration
)
    .onEach { tickAction() }
    .onCompletion {
        if (!coroutineContext.job.isCancelled) {
            completeAction()
        }
    }
    .launchIn(scope)

fun tickerFlow(
    period: Duration,
    initialDelay: Duration,
    duration: Duration
) =
    flow {
        delay(initialDelay)
        var seconds = duration.inWholeSeconds
        while (seconds > 0L) {
            emit(Unit)
            delay(period)
            --seconds
        }
    }
