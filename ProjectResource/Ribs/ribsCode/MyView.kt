package com.dev.devkotlin.root.dashboard.my

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.dev.devkotlin.MyApplication
import com.google.android.material.tabs.TabLayout
import fragment.ProgramItemFragment
import kotlinx.android.synthetic.main.my_rib.view.*

class MyView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) :
    ConstraintLayout(context, attrs, defStyle), MyInteractor.MyPresenter  {

    override fun toolbarSet(){
        my_tab.addTab(my_tab.newTab().setText("최근 시청 목록"))
        my_tab.addTab(my_tab.newTab().setText("찜한 프로그램"))
        my_tab.addTab(my_tab.newTab().setText("구매한 프로그램"))
        my_tab.tabGravity = TabLayout.GRAVITY_FILL
    }
}
