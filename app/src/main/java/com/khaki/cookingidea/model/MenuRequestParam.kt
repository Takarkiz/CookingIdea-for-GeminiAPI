package com.khaki.cookingidea.model

import android.os.Parcelable
import androidx.annotation.ReturnThis
import kotlinx.parcelize.Parcelize

@Parcelize
class MenuRequestParam private constructor(
    val requestIngredients: List<Ingredient>,
    val flyerUri: String?,
    val requestIngredientsInFlyer: List<Ingredient>,
    val requestMenuTheme: String?,
) : Parcelable {

    fun newBuilder(): Builder {
        val builder = Builder()

        requestIngredients.forEach {
            builder.setIngredient(it)
        }

        if (flyerUri != null) {
            builder.setUseFlyer(flyerUri)
        }

        requestIngredientsInFlyer.forEach {
            builder.setIngredientInFlyer(it)
        }

        if (requestMenuTheme != null) {
            builder.setRequestMenuTheme(requestMenuTheme)
        }

        return builder
    }

    class Builder {

        private val requestIngredients: MutableList<Ingredient> = mutableListOf()
        private var flyerUri: String? = null
        private val requestIngredientsInFlyer: MutableList<Ingredient> = mutableListOf()
        private var requestMenuTheme: String? = null

        @ReturnThis
        fun setIngredient(newIngredient: Ingredient): Builder {
            this.requestIngredients.add(newIngredient)
            return this
        }

        @ReturnThis
        fun setUseFlyer(flyerUri: String): Builder {
            this.flyerUri = flyerUri
            return this
        }

        @ReturnThis
        fun setRequestMenuTheme(theme: String): Builder {
            this.requestMenuTheme = theme
            return this
        }

        @ReturnThis
        fun setIngredientInFlyer(newIngredient: Ingredient): Builder {
            this.requestIngredientsInFlyer.add(newIngredient)
            return this
        }

        fun build(): MenuRequestParam {
            return MenuRequestParam(
                requestIngredients,
                flyerUri,
                requestIngredientsInFlyer,
                requestMenuTheme
            )
        }
    }
}
