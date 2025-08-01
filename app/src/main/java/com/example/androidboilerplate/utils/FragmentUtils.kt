package com.example.androidboilerplate.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager

object FragmentUtils {

    fun addFragment(
        activity: FragmentActivity,
        fragment: Fragment,
        containerId: Int,
        tag: String? = null,
        addToBackStack: Boolean = true
    ) {
        val transaction = activity.supportFragmentManager.beginTransaction()
        transaction.add(containerId, fragment, tag)
        if (addToBackStack) {
            transaction.addToBackStack(tag)
        }
        transaction.commit()
    }

    fun replaceFragment(
        activity: FragmentActivity,
        fragment: Fragment,
        containerId: Int,
        tag: String? = null,
        addToBackStack: Boolean = true
    ) {
        val transaction = activity.supportFragmentManager.beginTransaction()
        transaction.replace(containerId, fragment, tag)
        if (addToBackStack) {
            transaction.addToBackStack(tag)
        }
        transaction.commit()
    }

    fun popBackStack(activity: FragmentActivity) {
        if (activity.supportFragmentManager.backStackEntryCount > 0) {
            activity.supportFragmentManager.popBackStack()
        }
    }

    fun clearBackStack(activity: FragmentActivity) {
        activity.supportFragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

    fun getCurrentFragment(activity: FragmentActivity, containerId: Int): Fragment? {
        return activity.supportFragmentManager.findFragmentById(containerId)
    }
} 