package com.khaki.cookingidea.model

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

/**
 * @see Ingredient
 */
class IngredientTest {

    @Test
    fun test_correctIngredientName() {
        val ingredient = Ingredient.of("トマト")
        Assertions.assertEquals(
            "トマト",
            ingredient.name,
            "指定した名前の食材がインスタンス化されていることを確認する"
        )
    }

    @Test
    fun test_createDifferentID() {
        val ingredient1 = Ingredient.of("トマト")
        val ingredient2 = Ingredient.of("トマト")

        Assertions.assertNotEquals(
            ingredient1.id,
            ingredient2.id,
            "複数の食材のIDが同一にならないことを確認"
        )
    }
}
