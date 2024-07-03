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
            if (apiKey == null) throw IllegalArgumentException("apiKey is required")
            return GenerativeModel(
                modelName,
                apiKey!!,
                generationConfig = generationConfig {
                    temperature = 1f
                    topK = 64
                    topP = 0.95f
                    maxOutputTokens = 8192
                    responseMimeType = responseType.responseMimeType
                }
            )
        }
    }
}