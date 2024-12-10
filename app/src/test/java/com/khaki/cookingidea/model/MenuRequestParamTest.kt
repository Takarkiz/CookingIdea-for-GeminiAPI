package com.khaki.cookingidea.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

/**
 * @see MenuRequestParam
 */
class MenuRequestParamTest {

    @Test
    fun testBuilder_empty() {
        val param = MenuRequestParam.Builder().build()

        assertEquals(
            emptyList<Ingredient>(),
            param.requestIngredients,
            "空の食材が取得できることを確認する"
        )

        assertEquals(
            emptyList<Ingredient>(),
            param.requestIngredientsInFlyer,
            "空の食材が取得できることを確認する"
        )

        assertEquals(
            null,
            param.flyerUri,
            "初期値ではチラシを使わない設定になっていることを確認"
        )

        assertNull(
            param.requestMenuTheme,
            "初回はテーマが設定されていないことを確認"
        )
    }

    @Test
    fun testBuilder() {
        val ingredient1 = Ingredient.of("トマト")
        val ingredient2 = Ingredient.of("レタス")
        val ingredient3 = Ingredient.of("チーズ")

        val param = MenuRequestParam.Builder()
            .setIngredient(ingredient1)
            .setIngredient(ingredient2)
            .setUseFlyer("content:hoge/fuga")
            .setIngredientInFlyer(ingredient3)
            .setRequestMenuTheme("和風")
            .build()

        assertEquals(
            listOf(ingredient1, ingredient2),
            param.requestIngredients,
            "食材が追加したものと一致していることを確認"
        )
        assertEquals("content:hoge/fuga", param.flyerUri, "チラシを利用することの指定が確認")
        assertEquals(
            listOf(ingredient3),
            param.requestIngredientsInFlyer,
            "チラシで使用する食材が一致していることを確認"
        )
        assertEquals("和風", param.requestMenuTheme, "テーマが一致することの確認")
    }
}
