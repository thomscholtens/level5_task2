package com.example.level5_task2.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.level5_task2.database.GameRepository
import com.example.level5_task2.model.Game
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GameViewModel (application: Application) : AndroidViewModel(application) {
    private val ioScope = CoroutineScope(Dispatchers.IO)
    private val gameRepository = GameRepository(application.applicationContext)

    val games: LiveData<List<Game>> = gameRepository.getAllGames()

    fun insertGame(game: Game) {
        ioScope.launch {
            gameRepository.insertGame(game)
        }
    }

    fun deleteGame(game: Game) {
        ioScope.launch {
            gameRepository.deleteGame(game)
        }
    }

    fun removeAllGames() {
        ioScope.launch {
            gameRepository.deleteAllGames()
        }
    }
}