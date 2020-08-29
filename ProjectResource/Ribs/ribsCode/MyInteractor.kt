package com.dev.devkotlin.root.dashboard.my

import android.util.Log
import android.view.View
import androidx.viewpager.widget.PagerAdapter
import com.dev.devkotlin.MyApplication
import com.dev.devkotlin.data.ApolloDataSource
import com.dev.devkotlin.utils.RxSchedulers
import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import fragment.LiveItemFragment
import fragment.ProgramItemFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@RibInteractor
class MyInteractor : Interactor<MyInteractor.MyPresenter, MyRouter>() {

    @Inject
    lateinit var presenter: MyPresenter

    private val apolloDataSource: ApolloDataSource by lazy {
        MyApplication.getAppInstance().getDataSource()
    }


    override fun didBecomeActive(savedInstanceState: Bundle?) {
        super.didBecomeActive(savedInstanceState)

        presenter.toolbarSet()
        router.settingPager()
        fetchProgramList()
    }

    override fun dispatchDetach(): MyPresenter {
        return super.dispatchDetach()
        router.detachRouter()
    }

    override fun willResignActive() {
        super.willResignActive()

    }

    interface MyPresenter {
        fun toolbarSet()
    }

    private fun fetchProgramList() {
        apolloDataSource.fetchProgramList()
    }
}


