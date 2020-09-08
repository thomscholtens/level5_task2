package com.example.level5_task2.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.level5_task2.model.Game

class GameRepository(context: Context) {

    private var gameDao: GameDao

    init {
        val gameRoomDatabase = GameRoomDatabase.getDatabase(context)
        gameDao = gameRoomDatabase!!.gameDao()
    }

    fun getAllGames(): LiveData<List<Game>> {
        return gameDao.getAllGames() ?: MutableLiveData(emptyList())

    }

    suspend fun insertGame(game: Game) {
        gameDao.insertGame(game)
    }

    suspend fun deleteGame(game: Game) {
        gameDao.deleteGame(game)
    }

    suspend fun deleteAllGames() {
        gameDao.deleteAllGames()
    }

    suspend fun updateReminder(game: Game) {
        gameDao.updateGame(game)
    }
}