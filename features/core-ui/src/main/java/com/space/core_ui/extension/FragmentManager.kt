package com.space.core_ui.extension


import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import androidx.fragment.app.commitNow

inline fun <reified T : Fragment> FragmentManager.transformFragment(
    layoutRes: Int,
    vararg triple: Pair<String, Any?>
) {
    commit {
        replace(layoutRes, T::class.java, bundleOf(*triple))
        setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        setReorderingAllowed(true)
        addToBackStack(T::class.java.javaClass.name)
    }
}

inline fun <reified T : Fragment> FragmentManager.startFragment(
    layoutRes: Int,
    vararg triple: Pair<String, Any?>
) {
    commitNow {
        replace(layoutRes, T::class.java, bundleOf(*triple))
        setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        setReorderingAllowed(true)
    }
}

fun FragmentManager.clearBackStack() {
    popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
}

