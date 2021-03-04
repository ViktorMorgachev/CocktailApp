package com.beeline.demo.cocktailapp.navigation

import androidx.fragment.app.Fragment

interface FragmentNavigation{
    fun addFragment(fragment: Fragment)
    fun replaceFragment(fragment: Fragment)
}