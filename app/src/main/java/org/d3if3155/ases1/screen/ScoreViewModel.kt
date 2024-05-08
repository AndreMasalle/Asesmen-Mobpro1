package org.d3if3155.ases1.screen

import androidx.lifecycle.ViewModel
import org.d3if3155.ases1.data.Score

class ScoreViewModel : ViewModel() {

    val data = getDataDummy()
    private fun getDataDummy() : List<Score> {
        val data = mutableListOf<Score>()
        for (i in 11 downTo 5){
            data.add(
                Score(
                    i.toLong(),
                    "Matematika",
                    "Skor anda: $i",
                    "Saya masih membutuhkan banyak latihan",
                    "2024-05-$i 12:11:50"
                )
            )
        }
        return data
    }
}