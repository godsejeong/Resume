package com.dev.devkotlin.root.dashboard.my.purchase

import android.view.LayoutInflater
import android.view.ViewGroup
import com.dev.devkotlin.R
import com.dev.devkotlin.root.dashboard.my.bookmark.BookmarkView
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
 * Builder for the {@link PurchaseScope}.
 *
 * TODO describe this scope's responsibility as a whole.
 */
class PurchaseBuilder(dependency: ParentComponent) : ViewBuilder<PurchaseView, PurchaseRouter, PurchaseBuilder.ParentComponent>(dependency) {

  /**
   * Builds a new [PurchaseRouter].
   *
   * @param parentViewGroup parent view group that this router's view will be added to.
   * @return a new [PurchaseRouter].
   */
  fun build(parentViewGroup: ViewGroup): PurchaseRouter {
    val view = createView(parentViewGroup)
    val interactor = PurchaseInteractor()
    val component = DaggerPurchaseBuilder_Component.builder()
        .parentComponent(dependency)
        .view(view)
        .interactor(interactor)
        .build()
    return component.purchaseRouter()
  }

  override fun inflateView(inflater: LayoutInflater, parentViewGroup: ViewGroup): PurchaseView? {
      return inflater.inflate(R.layout.purchase_rib, parentViewGroup, false) as PurchaseView
  }

  interface ParentComponent {
    // TODO: Define dependencies required from your parent interactor here.
  }

  @dagger.Module
  abstract class Module {

    @PurchaseScope
    @Binds
    internal abstract fun presenter(view: PurchaseView): PurchaseInteractor.PurchasePresenter

    @dagger.Module
    companion object {

      @PurchaseScope
      @Provides
      @JvmStatic
      internal fun router(
          component: Component,
          view: PurchaseView,
          interactor: PurchaseInteractor): PurchaseRouter {
        return PurchaseRouter(view, interactor, component)
      }
    }

    // TODO: Create provider methods for dependencies created by this Rib. These should be static.
  }

  @PurchaseScope
  @dagger.Component(modules = arrayOf(Module::class), dependencies = arrayOf(ParentComponent::class))
  interface Component : InteractorBaseComponent<PurchaseInteractor>, BuilderComponent {

    @dagger.Component.Builder
    interface Builder {
      @BindsInstance
      fun interactor(interactor: PurchaseInteractor): Builder

      @BindsInstance
      fun view(view: PurchaseView): Builder

      fun parentComponent(component: ParentComponent): Builder
      fun build(): Component
    }
  }

  interface BuilderComponent {
    fun purchaseRouter(): PurchaseRouter
  }

  @Scope
  @Retention(CLASS)
  internal annotation class PurchaseScope

  @Qualifier
  @Retention(CLASS)
  internal annotation class PurchaseInternal
}
