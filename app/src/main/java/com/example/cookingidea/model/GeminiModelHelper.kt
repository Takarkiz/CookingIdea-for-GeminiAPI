package com.example.cookingidea.model

import androidx.annotation.ReturnThis
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.generationConfig

object GeminiModelHelper {

    enum class ModelType(val modelName: String) {
        FLASH("models/gemini-1.5-flash"),
        PRO("models/gemini-1.5-pro"),
    }

    enum class ResponseType(val responseMimeType: String) {
        TEXT("text/plain"),
        JSON("application/json"),
    }

    class Build {
        private var modelName: String = ModelType.FLASH.modelName
        private var apiKey: String? = null
        private var responseType: ResponseType = ResponseType.TEXT

        @ReturnThis
        fun setModel(modelType: ModelType): Build {
            modelName = modelType.modelName
            return this
        }

        @ReturnThis
        fun setApiKey(apiKey: String): Build {
            this.apiKey = apiKey
            return this
        }

        @ReturnThis
        fun setResponseType(responseType: ResponseType): Build {
            this.responseType = responseType
            return this
        }

        fun build(): GenerativeModel {
            val safetyApiKey = checkNotNull(apiKey)
            return GenerativeModel(
                modelName,
                safetyApiKey,
                generationConfig = generationConfig {
                    temperature = TEMPERATURE_VALUE
                    topK = TOP_K_VALUE
                    topP = TOP_P_VALUE
                    maxOutputTokens = MAX_OUTPUT_TOKENS
                    responseMimeType = responseType.responseMimeType
                }
            )
        }
    }

    // 生成パラメータ
    private const val TEMPERATURE_VALUE = 1f
    private const val TOP_K_VALUE = 64
    private const val TOP_P_VALUE = 0.95f
    private const val MAX_OUTPUT_TOKENS = 8192

}