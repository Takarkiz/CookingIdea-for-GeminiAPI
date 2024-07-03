package com.example.cookingidea.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

/**
 * @see GeminiModelHelper
 */
class GeminiModelHelperTest {

    @Test
    fun `testBuild throws exception when API key is not set`() {
        assertThrows<IllegalStateException> {
            GeminiModelHelper.Build().build()
        }
    }

    @Test
    fun `testBuild creates GenerativeModel with default values`() {
        val model = GeminiModelHelper.Build()
            .setApiKey("YOUR_API_KEY")
            .build()

        assertEquals(GeminiModelHelper.ModelType.FLASH.modelName, model.modelName)
        assertEquals("YOUR_API_KEY", model.apiKey)
        assertEquals(1f, model.generationConfig?.temperature)
        assertEquals(64, model.generationConfig?.topK)
        assertEquals(0.95f, model.generationConfig?.topP)
        assertEquals(8192, model.generationConfig?.maxOutputTokens)
        assertEquals(
            GeminiModelHelper.ResponseType.TEXT.responseMimeType,
            model.generationConfig?.responseMimeType
        )
    }

    @Test
    fun `testBuild creates GenerativeModel with custom values`() {
        val model = GeminiModelHelper.Build()
            .setModel(GeminiModelHelper.ModelType.PRO)
            .setApiKey("YOUR_API_KEY")
            .setResponseType(GeminiModelHelper.ResponseType.JSON)
            .build()

        assertEquals(GeminiModelHelper.ModelType.PRO.modelName, model.modelName)
        assertEquals("YOUR_API_KEY", model.apiKey)
        assertEquals(
            GeminiModelHelper.ResponseType.JSON.responseMimeType,
            model.generationConfig?.responseMimeType
        )
    }
}
