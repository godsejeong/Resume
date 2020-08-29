package com.dev.devkotlin.root.dashboard.my.recent

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.dev.devkotlin.utils.RecyclerViewDecoration
import kotlinx.android.synthetic.main.bookmark_rib.view.*
import kotlinx.android.synthetic.main.recent_rib.view.*

/**
 * Top level view for {@link RecentBuilder.RecentScope}.
 */
class RecentView
@JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) :
    LinearLayout(context, attrs, defStyle), RecentInteractor.RecentPresenter {


//    override fun showProgressBar() {
//        recent_progress.visibility = View.VISIBLE
//    }
//
//    override fun hideProgressBar() {
//        recent_progress.visibility = View.GONE
//    }

    override fun recyclerSet(){
        recent_rv.addItemDecoration(RecyclerViewDecoration(50,0.1))
    }

}
