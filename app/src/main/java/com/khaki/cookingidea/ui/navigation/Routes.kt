package com.khaki.cookingidea.ui.navigation

/**
 * Enum class representing all navigation routes in the application.
 * This centralizes route definitions to avoid hardcoded strings and typos.
 */
enum class Routes(val route: String) {
    /**
     * Start screen - the initial screen shown to users
     */
    START("start"),

    /**
     * Theme selection screen - where users select a cooking theme
     */
    SELECT_THEME("selectTheme"),

    /**
     * Main screen - where users can upload images and generate cooking ideas
     */
    MAIN("main");

    companion object {
        /**
         * Get the route value as a string
         */
        fun Routes.routeValue(): String = this.route
    }
}
