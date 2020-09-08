package com.example.level5_task2.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.level5_task2.model.Game

@Dao
interface GameDao {

    @Query("SELECT * FROM gamesTable")
    fun getAllGames(): LiveData<List<Game>>

    @Insert
    suspend fun insertGame(game: Game)

    @Delete
    suspend fun deleteGame(game: Game)

    @Query("DELETE FROM gamesTable")
    suspend fun deleteAllGames()

    @Update
    suspend fun updateGame(game: Game)

}