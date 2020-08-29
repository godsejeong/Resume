package com.dev.devkotlin.root.dashboard.my

import android.view.LayoutInflater
import android.view.ViewGroup
import com.dev.devkotlin.R
import com.dev.devkotlin.root.dashboard.my.bookmark.BookmarkBuilder
import com.dev.devkotlin.root.dashboard.my.purchase.PurchaseBuilder
import com.dev.devkotlin.root.dashboard.my.recent.RecentBuilder
import com.dev.devkotlin.utils.RxSchedulers
import com.uber.rib.core.InteractorBaseComponent
import com.uber.rib.core.ViewBuilder
import dagger.Binds
import dagger.BindsInstance
import dagger.Provides
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy.CLASS
import javax.inject.Qualifier
import javax.inject.Scope

class MyBuilder(dependency: ParentComponent) :
    ViewBuilder<MyView, MyRouter, MyBuilder.ParentComponent>(dependency) {

    fun build(parentViewGroup: ViewGroup): MyRouter {
        val view = createView(parentViewGroup)
        val interactor = MyInteractor()
        val component = DaggerMyBuilder_Component.builder()
            .parentComponent(dependency)
            .view(view)
            .interactor(interactor)
            .build()
        return component.myRouter()
    }

    override fun inflateView(inflater: LayoutInflater, parentViewGroup: ViewGroup): MyView? {
        return inflater.inflate(R.layout.my_rib, parentViewGroup, false) as MyView
    }

    interface ParentComponent {
        fun rxSchedulers(): RxSchedulers
    }

    @dagger.Module
    abstract class Module {

        @MyScope
        @Binds
        internal abstract fun presenter(view: MyView): MyInteractor.MyPresenter

        @dagger.Module
        companion object {

            @MyScope
            @Provides
            @JvmStatic
            internal fun router(
                component: Component,
                view: MyView,
                interactor: MyInteractor
            ): MyRouter {
                return MyRouter(view, interactor, component,RecentBuilder(component),BookmarkBuilder(component),PurchaseBuilder(component))
            }
        }
    }

    @MyScope
    @dagger.Component(
        modules = arrayOf(Module::class),
        dependencies = arrayOf(ParentComponent::class)
    )
    interface Component : InteractorBaseComponent<MyInteractor>,
        RecentBuilder.ParentComponent,
        BookmarkBuilder.ParentComponent,
        PurchaseBuilder.ParentComponent,BuilderComponent {

        @dagger.Component.Builder
        interface Builder {
            @BindsInstance
            fun interactor(interactor: MyInteractor): Builder

            @BindsInstance
            fun view(view: MyView): Builder

            fun parentComponent(component: ParentComponent): Builder
            fun build(): Component
        }
    }

    interface BuilderComponent {
        fun myRouter(): MyRouter
    }

    @Scope
    @Retention(CLASS)
    internal annotation class MyScope

    @Qualifier
    @Retention(CLASS)
    internal annotation class MyInternal
}
