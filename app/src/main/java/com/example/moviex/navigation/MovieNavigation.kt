package com.example.moviex.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.moviex.screens.details.DetailsScreen
import com.example.moviex.screens.home.HomeScreen

@Composable
fun MovieNavigation(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination =MovieScreens.HomeScreen.name
    ){
        composable(
            MovieScreens.HomeScreen.name
        ){
       HomeScreen(navController = navController)
        }

//example: Passing arguments www.google.com/detail-screen/id=34
        composable(
            MovieScreens.DetailScreen.name +"/{movie}",
            arguments = listOf(navArgument(name = "movie"){type = NavType.StringType})
        ){
            backStackEntry ->
            DetailsScreen(navController = navController,backStackEntry.arguments?.getString("movie"))
        }
    }
}