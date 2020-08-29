package com.dev.devkotlin.root.dashboard.my.bookmark

import android.view.View
import com.dev.devkotlin.adapter.ItemListAdapter
import com.dev.devkotlin.data.ListDataDiffInterface

import com.uber.rib.core.ViewRouter
import kotlinx.android.synthetic.main.bookmark_rib.view.*

/**
 * Adds and removes children of {@link BookmarkBuilder.BookmarkScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class BookmarkRouter(
    view: BookmarkView,
    interactor: BookmarkInteractor,
    component: BookmarkBuilder.Component) : ViewRouter<BookmarkView, BookmarkInteractor, BookmarkBuilder.Component>(view, interactor, component){

    fun recyclerViewSet(item : ArrayList<ListDataDiffInterface>){
        ItemListAdapter().apply {
            view.bookmark_rv.adapter = this
            this.submitList(item)
        }
    }

}
