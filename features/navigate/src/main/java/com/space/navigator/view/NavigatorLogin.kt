package com.space.navigator.view

import android.content.Context
import com.space.navigator.BaseNavigator
import com.space.shared.AuthType

interface NavigatorLogin : BaseNavigator{
    fun openView(
        context: Context,
        authType: AuthType
    )
}
