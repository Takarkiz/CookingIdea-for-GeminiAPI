package com.khaki.cookingidea.ui.screen.main

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import coil.ImageLoader
import coil.request.ImageRequest
import com.khaki.cookingidea.core.android.ContextSupplier
import com.khaki.cookingidea.ui.navigation.AppNavHost
import com.khaki.cookingidea.ui.screen.selecttheme.SelectThemeViewModel
import com.khaki.cookingidea.ui.theme.CookingIdeaTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels {
        MainViewModel.Factory(contextSupplier = object : ContextSupplier {
            override fun getContext(): Context {
                return this@MainActivity
            }
        })
    }

    private val selectThemeViewModel: SelectThemeViewModel by viewModels {
        SelectThemeViewModel.Factory()
    }

    private val photoPickerLauncher =
        registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            if (uri == null) return@registerForActivityResult
            mainViewModel.updateSelectedImageUri(uri)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            CookingIdeaTheme {
                AppNavHost(
                    navController = navController,
                    mainViewModel = mainViewModel,
                    selectThemeViewModel = selectThemeViewModel,
                    photoPickerLauncher = photoPickerLauncher,
                    onLoadImage = { uri ->
                        loadImage(uri)
                    }
                )
            }
        }
    }

    private fun loadImage(uri: Uri) {
        val imageLoader = ImageLoader.Builder(this)
            .crossfade(true)
            .build()
        val request = ImageRequest.Builder(this)
            .data(uri)
            .target(
                onStart = { _ ->
                    // NOP
                },
                onSuccess = { result ->
                    lifecycleScope.launch {
                        mainViewModel.execute((result as BitmapDrawable).bitmap)
                    }
                },
                onError = { _ ->
                    // NOP
                }
            )
            .build()
        imageLoader.enqueue(request)
    }
}
