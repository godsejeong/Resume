package com.dev.devkotlin.root.dashboard.my.bookmark

import android.view.LayoutInflater
import android.view.ViewGroup
import com.dev.devkotlin.R
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
 * Builder for the {@link BookmarkScope}.
 *
 * TODO describe this scope's responsibility as a whole.
 */
class BookmarkBuilder(dependency: ParentComponent) : ViewBuilder<BookmarkView, BookmarkRouter, BookmarkBuilder.ParentComponent>(dependency) {

  /**
   * Builds a new [BookmarkRouter].
   *
   * @param parentViewGroup parent view group that this router's view will be added to.
   * @return a new [BookmarkRouter].
   */
  fun build(parentViewGroup: ViewGroup): BookmarkRouter {
    val view = createView(parentViewGroup)
    val interactor = BookmarkInteractor()
    val component = DaggerBookmarkBuilder_Component.builder()
        .parentComponent(dependency)
        .view(view)
        .interactor(interactor)
        .build()
    return component.bookmarkRouter()
  }

  override fun inflateView(inflater: LayoutInflater, parentViewGroup: ViewGroup): BookmarkView? {
    return inflater.inflate(R.layout.bookmark_rib, parentViewGroup, false) as BookmarkView

  }

  interface ParentComponent {
    // TODO: Define dependencies required from your parent interactor here.
  }

  @dagger.Module
  abstract class Module {

    @BookmarkScope
    @Binds
    internal abstract fun presenter(view: BookmarkView): BookmarkInteractor.BookmarkPresenter

    @dagger.Module
    companion object {

      @BookmarkScope
      @Provides
      @JvmStatic
      internal fun router(
          component: Component,
          view: BookmarkView,
          interactor: BookmarkInteractor): BookmarkRouter {
        return BookmarkRouter(view, interactor, component)
      }
    }

    // TODO: Create provider methods for dependencies created by this Rib. These should be static.
  }

  @BookmarkScope
  @dagger.Component(modules = arrayOf(Module::class), dependencies = arrayOf(ParentComponent::class))
  interface Component : InteractorBaseComponent<BookmarkInteractor>, BuilderComponent {

    @dagger.Component.Builder
    interface Builder {
      @BindsInstance
      fun interactor(interactor: BookmarkInteractor): Builder

      @BindsInstance
      fun view(view: BookmarkView): Builder

      fun parentComponent(component: ParentComponent): Builder
      fun build(): Component
    }
  }

  interface BuilderComponent {
    fun bookmarkRouter(): BookmarkRouter
  }

  @Scope
  @Retention(CLASS)
  internal annotation class BookmarkScope

  @Qualifier
  @Retention(CLASS)
  internal annotation class BookmarkInternal
}
