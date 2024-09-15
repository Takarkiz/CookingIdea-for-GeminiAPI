package com.khaki.cookingidea.model

import java.util.UUID

class Ingredient private constructor(
    val id: ID,
    val name: String,
) {

    companion object {

        fun of(
            name: String,
        ): Ingredient {
            return Ingredient(
                id = ID.of(),
                name = name,
            )
        }
    }

    @JvmInline
    value class ID private constructor(
        private val value: String
    ) {
        companion object {
            fun of(): ID {
                return ID(UUID.randomUUID().toString())
            }
        }
    }
}
