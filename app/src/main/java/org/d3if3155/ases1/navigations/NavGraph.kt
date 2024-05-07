package org.d3if3155.ases1.navigations

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.d3if3155.ases1.AboutScreen
import org.d3if3155.ases1.screen.MainScreen
import org.d3if3155.ases1.screen.QuizScreen



@Composable
fun SetupNavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = "mainScreen"
    ){
        composable("mainScreen"){
            MainScreen(navController)
        }
        composable("aboutScreen"){
            AboutScreen(navController)
        }
        composable("quizScreen"){
            QuizScreen(navController = navController)
        }
        composable("scoreScreen"){
            QuizScreen(navController = navController)
        }



    }
}