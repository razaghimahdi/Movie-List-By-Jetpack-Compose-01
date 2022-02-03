package com.example.core.domain_core

/**
 * to handle progressbar loading.
 * */
sealed class ProgressBarState{

    object Loading: ProgressBarState()

    object Idle: ProgressBarState()

}