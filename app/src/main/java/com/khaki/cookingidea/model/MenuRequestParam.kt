package com.khaki.cookingidea.model

import androidx.annotation.ReturnThis

class MenuRequestParam private constructor(
    val requestIngredients: List<Ingredient>,
    val isUseFlyer: Boolean = false,
    val requestIngredientsInFlyer: List<Ingredient>,
    val requestMenuTheme: String?,
) {

    class Build {

        private val requestIngredients: MutableList<Ingredient> = mutableListOf()
        private var isUseFlyer: Boolean = false
        private val requestIngredientsInFlyer: MutableList<Ingredient> = mutableListOf()
        private var requestMenuTheme: String? = null

        @ReturnThis
        fun setIngredient(newIngredient: Ingredient): Build {
            this.requestIngredients.add(newIngredient)
            return this
        }

        @ReturnThis
        fun setUseFlyer(isUseFlyer: Boolean): Build {
            this.isUseFlyer = isUseFlyer
            return this
        }

        @ReturnThis
        fun setRequestMenuTheme(theme: String): Build {
            this.requestMenuTheme = theme
            return this
        }

        @ReturnThis
        fun setIngredientInFlyer(newIngredient: Ingredient): Build {
            this.requestIngredientsInFlyer.add(newIngredient)
            return this
        }

        fun build(): MenuRequestParam {
            return MenuRequestParam(
                requestIngredients,
                isUseFlyer,
                requestIngredientsInFlyer,
                requestMenuTheme
            )
        }
    }
}
