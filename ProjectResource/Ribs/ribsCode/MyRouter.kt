package com.dev.devkotlin.root.dashboard.my

import android.view.View
import com.dev.devkotlin.root.dashboard.my.bookmark.BookmarkBuilder
import com.dev.devkotlin.root.dashboard.my.bookmark.BookmarkRouter
import com.dev.devkotlin.root.dashboard.my.bookmark.BookmarkView
import com.dev.devkotlin.root.dashboard.my.purchase.PurchaseBuilder
import com.dev.devkotlin.root.dashboard.my.purchase.PurchaseRouter
import com.dev.devkotlin.root.dashboard.my.purchase.PurchaseView
import com.dev.devkotlin.root.dashboard.my.recent.RecentBuilder
import com.dev.devkotlin.root.dashboard.my.recent.RecentRouter
import com.dev.devkotlin.root.dashboard.my.recent.RecentView
import com.google.android.material.tabs.TabLayout
import com.uber.rib.core.ViewRouter
import kotlinx.android.synthetic.main.my_rib.view.*

class MyRouter(
    view: MyView,
    interactor: MyInteractor,
    component: MyBuilder.Component,
    private var recentBuilder: RecentBuilder,
    private var bookmarkBuilder: BookmarkBuilder,
    private var purchaseBuilder: PurchaseBuilder
) : ViewRouter<MyView, MyInteractor, MyBuilder.Component>(
    view, interactor, component
) {

    var recentRouter: RecentRouter? = null
    var bookmarkRouter: BookmarkRouter? = null
    var purchaseRouter: PurchaseRouter? = null

    var bookmarkView: BookmarkView? = null
    var recentView: RecentView? = null
    var purchaseView: PurchaseView? = null

    var views = ArrayList<View>()

    fun settingPager() {
        recentRouter = recentBuilder.build(view)
        bookmarkRouter = bookmarkBuilder.build(view)
        purchaseRouter = purchaseBuilder.build(view)

        recentRouter?.let {
            recentView = it.view
        }
        bookmarkRouter?.let {
            bookmarkView = it.view
        }
        purchaseRouter?.let {
            purchaseView = it.view
        }

        views.apply {
            add(recentView!!)
            add(bookmarkView!!)
            add(purchaseView!!)
        }

        view.my_pager.adapter = ViewPagerAdapter(view.my_tab.tabCount,views)

        view.my_pager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(view.my_tab))

        view.my_tab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                view.my_pager.currentItem = tab!!.position
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }
        })

        attachChild(recentRouter)
        attachChild(bookmarkRouter)
        attachChild(purchaseRouter)
    }

    fun detachRouter() {
        recentRouter?.let {
            detachChild(it)
        }.also {
            recentRouter = null
        }

        bookmarkRouter?.let {
            detachChild(it)
        }.also {
            bookmarkRouter = null
        }
        purchaseRouter?.let {
            detachChild(it)
        }.also {
            purchaseRouter = null
        }
    }
}
