package com.example.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.core.constants.Queue
import com.example.core.domain_core.ProgressBarState
import com.example.core.domain_core.UIComponent

@Composable
fun BaseScreen(
    queue: Queue<UIComponent> = Queue(mutableListOf()), //Dialogs
    onRemoveHeadFromQueue: () -> Unit,
    progressBarState: ProgressBarState = ProgressBarState.Idle,
    content: @Composable () -> Unit,// The content of the UI.
){
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState
    ){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
        ){
            content()
            // process the queue
            if(!queue.isEmpty()){
                queue.peek()?.let { uiComponent ->
                    if(uiComponent is UIComponent.Dialog){
                        GenericDialog(
                            modifier = Modifier
                                .fillMaxWidth(0.9f)
                            ,
                            title = uiComponent.title,
                            description = uiComponent.description,
                            onRemoveHeadFromQueue = onRemoveHeadFromQueue
                        )
                    }
                }
            }

          /*  if(progressBarState is ProgressBarState.Loading){
                CircularIndeterminateProgressBar()
            }*/
        }
    }
}











