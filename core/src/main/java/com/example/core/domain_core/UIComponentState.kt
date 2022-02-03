package com.example.core.domain_core

/**
 * A generic class for hiding/showing some ui component, such as dialogs.
 */
sealed class UIComponentState {

    object Show: UIComponentState()

    object Hide: UIComponentState()
}
