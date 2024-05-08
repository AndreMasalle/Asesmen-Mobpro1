package org.d3if3155.ases1.screen

import androidx.lifecycle.ViewModel
import org.d3if3155.ases1.data.Score

class CatatanViewModel : ViewModel() {

    fun getCatatan(id: Long): Score{
        return Score(
            id,
            "Matematika",
            "Skor anda: 50",
            "Saya masih membutuhkan banyak latihan",
            "2024-05-08 12:11:50"
        )
    }
}