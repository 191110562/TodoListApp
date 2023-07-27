package com.example.todolistapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.todolistapp.ui.screen.home.HomeScreen
import com.example.todolistapp.ui.screen.todo.AddTodoScreen
import com.example.todolistapp.ui.screen.todo.DetailTodoScreen
import com.example.todolistapp.ui.screen.todo.EditTodoScreen

@Composable
fun TodoNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen(
                navigateToAddEntry = { navController.navigate(Screen.AddTodo.route)},
                navigateToUpdate = { itemId ->
                    val route = Screen.DetailTodo.routeWithArgs.replace("{${Screen.DetailTodo.itemIdArg}}", itemId.toString())
                    navController.navigate(route)
                }
            )
        }
        composable(route = Screen.AddTodo.route) {
            AddTodoScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() }
            )
        }
        composable(
            route = Screen.DetailTodo.routeWithArgs,
            arguments = listOf(navArgument(Screen.DetailTodo.itemIdArg) {
                type = NavType.IntType
            })
        ) {
            DetailTodoScreen(
                navigateToEditItem = { itemId ->
                    val route = Screen.EditTodo.routeWithArgs.replace("{${Screen.EditTodo.itemIdArg}}", itemId.toString())
                    navController.navigate(route) },
                navigateBack = { navController.navigateUp() }
            )
        }
        composable(
            route = Screen.EditTodo.routeWithArgs,
            arguments = listOf(navArgument(Screen.EditTodo.itemIdArg) {
                type = NavType.IntType
            })
        ) {
            EditTodoScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() }
            )
        }
    }
}