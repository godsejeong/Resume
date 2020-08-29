package com.dev.devkotlin.root.dashboard.my.recent

import android.view.LayoutInflater
import android.view.ViewGroup
import com.dev.devkotlin.R
import com.dev.devkotlin.root.dashboard.news.NewsView
import com.uber.rib.core.InteractorBaseComponent
import com.uber.rib.core.ViewBuilder
import dagger.Binds
import dagger.BindsInstance
import dagger.Provides
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy.CLASS
import javax.inject.Qualifier
import javax.inject.Scope

/**
 * Builder for the {@link RecentScope}.
 *
 * TODO describe this scope's responsibility as a whole.
 */
class RecentBuilder(dependency: ParentComponent) : ViewBuilder<RecentView, RecentRouter, RecentBuilder.ParentComponent>(dependency) {

  /**
   * Builds a new [RecentRouter].
   *
   * @param parentViewGroup parent view group that this router's view will be added to.
   * @return a new [RecentRouter].
   */
  fun build(parentViewGroup: ViewGroup): RecentRouter {
    val view = createView(parentViewGroup)
    val interactor = RecentInteractor()
    val component = DaggerRecentBuilder_Component.builder()
        .parentComponent(dependency)
        .view(view)
        .interactor(interactor)
        .build()
    return component.recentRouter()
  }

  override fun inflateView(inflater: LayoutInflater, parentViewGroup: ViewGroup): RecentView? {
    return inflater.inflate(R.layout.recent_rib, parentViewGroup, false) as RecentView
  }

  interface ParentComponent {
    // TODO: Define dependencies required from your parent interactor here.
  }

  @dagger.Module
  abstract class Module {

    @RecentScope
    @Binds
    internal abstract fun presenter(view: RecentView): RecentInteractor.RecentPresenter

    @dagger.Module
    companion object {

      @RecentScope
      @Provides
      @JvmStatic
      internal fun router(
          component: Component,
          view: RecentView,
          interactor: RecentInteractor): RecentRouter {
        return RecentRouter(view, interactor, component)
      }
    }

    // TODO: Create provider methods for dependencies created by this Rib. These should be static.
  }

  @RecentScope
  @dagger.Component(modules = arrayOf(Module::class), dependencies = arrayOf(ParentComponent::class))
  interface Component : InteractorBaseComponent<RecentInteractor>
    , BuilderComponent {

    @dagger.Component.Builder
    interface Builder {
      @BindsInstance
      fun interactor(interactor: RecentInteractor): Builder

      @BindsInstance
      fun view(view: RecentView): Builder

      fun parentComponent(component: ParentComponent): Builder
      fun build(): Component
    }
  }

  interface BuilderComponent {
    fun recentRouter(): RecentRouter
  }

  @Scope
  @Retention(CLASS)
  internal annotation class RecentScope

  @Qualifier
  @Retention(CLASS)
  internal annotation class RecentInternal
}
