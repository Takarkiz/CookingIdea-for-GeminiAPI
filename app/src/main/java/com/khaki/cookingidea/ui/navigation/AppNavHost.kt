package com.khaki.cookingidea.ui.navigation

import android.net.Uri
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.khaki.cookingidea.ui.navigation.Routes.Companion.routeValue
import com.khaki.cookingidea.ui.screen.main.MainScreen
import com.khaki.cookingidea.ui.screen.main.MainViewModel
import com.khaki.cookingidea.ui.screen.selecttheme.SelectThemeScreen
import com.khaki.cookingidea.ui.screen.selecttheme.SelectThemeViewModel
import com.khaki.cookingidea.ui.screen.start.StartScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    mainViewModel: MainViewModel,
    selectThemeViewModel: SelectThemeViewModel,
    photoPickerLauncher: ActivityResultLauncher<PickVisualMediaRequest>,
    onLoadImage: (Uri) -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = Routes.START.routeValue()
    ) {
        composable(Routes.START.routeValue()) {
            StartScreen(
                onClickStart = {
                    navController.navigate(Routes.SELECT_THEME.routeValue())
                }
            )
        }

        composable(Routes.SELECT_THEME.routeValue()) {
            val selectThemeUiState by selectThemeViewModel.uiState.collectAsStateWithLifecycle()

            SelectThemeScreen(
                uiState = selectThemeUiState,
                onUpdateSelectedTheme = {
                    selectThemeViewModel.updateInputTheme(it)
                },
                onBack = {
                    navController.popBackStack()
                },
                onNext = {
                    navController.navigate(Routes.MAIN.routeValue())
                }
            )
        }

        composable(Routes.MAIN.routeValue()) {
            val mainUiState by mainViewModel.uiStateFlow.collectAsStateWithLifecycle()
            val dialogUiState by mainViewModel.menuDialogUiStateFlow.collectAsStateWithLifecycle()

            MainScreen(
                uiState = mainUiState,
                dialogUiState = dialogUiState,
                onClickSelectImage = {
                    photoPickerLauncher.launch(
                        PickVisualMediaRequest(
                            mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly
                        )
                    )
                },
                onClickFlyerImage = {
                    // 画像のプレビューに遷移する
                },
                onClickRemoveImage = {
                    mainViewModel.removeSelectedImage()
                },
                onClickGenerator = {
                    onLoadImage(mainUiState.selectedImageUri!!)
                    mainUiState.selectedImageUri?.let(onLoadImage)
                },
                onDismissDialogRequest = {
                    mainViewModel.dismissDialog()
                }
            )
        }
    }
}
