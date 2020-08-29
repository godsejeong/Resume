package com.dev.devkotlin.root.dashboard.my.purchase

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.dev.devkotlin.utils.RecyclerViewDecoration
import kotlinx.android.synthetic.main.bookmark_rib.view.*
import kotlinx.android.synthetic.main.purchase_rib.view.*

/**
 * Top level view for {@link PurchaseBuilder.PurchaseScope}.
 */
class PurchaseView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : ConstraintLayout(context, attrs, defStyle), PurchaseInteractor.PurchasePresenter{

    override fun recyclerSet(){
        purchase_rv.addItemDecoration(RecyclerViewDecoration(50,0.1))
    }

}
