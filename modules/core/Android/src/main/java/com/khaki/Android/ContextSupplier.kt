package com.khaki.Android

import android.content.Context

/**
 * Contextが動的に必要な場合に利用する
 */
interface ContextSupplier {
    fun getContext(): Context
}
