package com.example.moviex.navigation

enum class MovieScreens {
    HomeScreen,
    DetailScreen;
   companion object {
       fun fromRoute(route:String?):MovieScreens = when(route?.substringBefore("/")){
           HomeScreen.name -> HomeScreen
           DetailScreen.name -> DetailScreen
           null -> HomeScreen
           else -> throw  IllegalAccessException("Route $route is not recognized")
       }

    }
}