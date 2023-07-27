package com.example.todolistapp.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object DetailTodo : Screen("detail") {
        const val itemIdArg = "itemId"
        val routeWithArgs = "$route/{$itemIdArg}"
    }
    object AddTodo : Screen("add")
    object EditTodo : Screen("edit") {
        const val itemIdArg = "itemId"
        val routeWithArgs = "$route/{$itemIdArg}"
    }
}