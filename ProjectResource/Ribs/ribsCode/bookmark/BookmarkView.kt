package com.dev.devkotlin.root.dashboard.my.bookmark

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.dev.devkotlin.utils.RecyclerViewDecoration
import kotlinx.android.synthetic.main.bookmark_rib.view.*

/**
 * Top level view for {@link BookmarkBuilder.BookmarkScope}.
 */
class BookmarkView
@JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) :
    ConstraintLayout(context, attrs, defStyle), BookmarkInteractor.BookmarkPresenter{

    override fun recyclerSet(){
        bookmark_rv.addItemDecoration(RecyclerViewDecoration(50,0.1))
    }
}
