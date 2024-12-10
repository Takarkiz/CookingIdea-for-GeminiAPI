package com.khaki.cookingidea.model

import android.content.Context
import com.khaki.cookingidea.R

fun MenuRequestParam.toPrompt(context: Context): String {

    var prompt = context.getString(R.string.main_prompt)

    // チラシを利用している場合
    if (flyerUri != null) {
        prompt = prompt.plus(context.getString(R.string.prompt_condition_use_flyer))

        if (requestIngredientsInFlyer.isNotEmpty()) {
            prompt =
                prompt.plus(context.getString(R.string.prompt_condition_use_ingredient_in_flyer))

            requestIngredientsInFlyer.forEach { ingredient ->
                prompt = prompt.plus(
                    context.getString(
                        R.string.prompt_condition_bullet_point,
                        ingredient.name
                    )
                )
            }
        }
    }

    // 使用する食材の指定
    if (requestIngredients.isNotEmpty()) {
        prompt = prompt.plus(context.getString(R.string.prompt_condition_use_ingredient))

        requestIngredients.forEach { ingredient ->
            prompt = prompt.plus(
                context.getString(
                    R.string.prompt_condition_bullet_point,
                    ingredient.name
                )
            )
        }
    }

    // テーマを設定している場合
    if (requestMenuTheme != null) {
        prompt = prompt.plus(
            context.getString(
                R.string.prompt_condition_use_menu_theme,
                requestMenuTheme
            )
        )
    }

    return prompt
}
