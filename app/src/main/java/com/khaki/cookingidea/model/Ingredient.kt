package com.khaki.cookingidea.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
class Ingredient private constructor(
    val id: ID,
    val name: String,
) : Parcelable {

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
    @Parcelize
    value class ID private constructor(
        private val value: String,
    ) : Parcelable {
        companion object {
            fun of(): ID {
                return ID(UUID.randomUUID().toString())
            }
        }
    }
}
