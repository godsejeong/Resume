package com.dev.devkotlin.root.dashboard.my.recent

import com.dev.devkotlin.adapter.ItemListAdapter
import com.dev.devkotlin.data.ListDataDiffInterface

import com.uber.rib.core.ViewRouter
import kotlinx.android.synthetic.main.recent_rib.view.*

/**
 * Adds and removes children of {@link RecentBuilder.RecentScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class RecentRouter(
    view: RecentView,
    interactor: RecentInteractor,
    component: RecentBuilder.Component) : ViewRouter<RecentView, RecentInteractor, RecentBuilder.Component>(view, interactor, component) {

    fun recyclerViewSet(item : ArrayList<ListDataDiffInterface>){
        ItemListAdapter().apply {
            view.recent_rv.adapter = this
            this.submitList(item)
        }
    }
}
