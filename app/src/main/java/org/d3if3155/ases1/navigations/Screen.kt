package org.d3if3155.ases1.navigations

sealed class Screen(val route: String) {
    data object Home: Screen("mainScreen")
    data object About: Screen("aboutScreen")
    data object Quiz: Screen("quizScreen")
}