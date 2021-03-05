package com.beeline.demo.cocktailapp.ui.base

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.beeline.demo.cocktailapp.R
import com.beeline.demo.cocktailapp.ui.history.HistoryCocktailFragment
import com.beeline.demo.cocktailapp.ui.random.RandomCocktailFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

class BaseActivity : FragmentActivity() {

    private val fragmentManager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        val pagerAdapter = ViewPagerAdapter(this)
        view_pager.adapter = pagerAdapter
        view_pager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {

        })
        TabLayoutMediator(
            tab_layout,
            view_pager
        ) { tab: TabLayout.Tab, i: Int ->
            tab.customView = pagerAdapter.getTabView(i)
        }.attach()

        view_pager?.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                for (i in 0..pagerAdapter.itemCount) {
                    tab_layout.getTabAt(i)?.customView?.findViewById<ImageView>(R.id.iv_tab)?.background =
                        pagerAdapter.changeINactiveTabIcon(i)
                }
                tab_layout?.getTabAt(position)?.customView?.findViewById<ImageView>(R.id.iv_tab)?.background =
                    pagerAdapter.changeActiveTabIcon(position)
            }
        })

    }

    inner class ViewPagerAdapter(fragmentActivity: FragmentActivity) :
        FragmentStateAdapter(fragmentActivity) {

        private val title_icons: List<Pair<String, Int>> = listOf(
                "Cocktail" to R.drawable.ic_cocktail,
                "History" to R.drawable.ic_history
            )

        override fun getItemCount(): Int {
            return title_icons.size
        }

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> RandomCocktailFragment.create()
                1 -> HistoryCocktailFragment.create()
                else -> HistoryCocktailFragment.create()
            }
        }

        fun getTabView(position: Int): View {
            val v: View = LayoutInflater.from(applicationContext).inflate(R.layout.tab_view, null)
            val tv = v.findViewById<View>(R.id.tv_tab) as TextView
            val iv = v.findViewById<View>(R.id.iv_tab)
            tv.text = title_icons[position].first
            iv.background =
                ResourcesCompat.getDrawable(resources, title_icons[position].second, theme)
            return v
        }

        fun changeActiveTabIcon(position: Int): Drawable? =
            ResourcesCompat.getDrawable(resources, title_icons[position].second, theme)

        fun changeINactiveTabIcon(position: Int): Drawable? =
            ResourcesCompat.getDrawable(resources, title_icons[position].second, theme)
    }
}