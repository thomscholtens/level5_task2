package com.example.level5_task2.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.level5_task2.R
import com.example.level5_task2.model.Game
import com.example.level5_task2.viewmodels.GameViewModel
import kotlinx.android.synthetic.main.fragment_add_game.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class AddGameFragment : Fragment() {

    private val viewModel: GameViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnSave.setOnClickListener {
            onAddGame()
        }
    }

    private fun onAddGame() {
        val gameTitle = etTitle.text.toString()
        val gamePlatform = etPlatform.text.toString()
        val year = etYear.text.toString()
        var month = etMonth.text.toString()
        if (month.length < 2) {
            month = "0".plus(month)
        }
        var day = etDay.text.toString()
        if (day.length < 2) {
            day = "0".plus(day)
        }
        val gameRelease = year.plus("-").plus(month).plus("-").plus(day)
        val localDate = LocalDate.parse(gameRelease)
        val defaultZoneId: ZoneId = ZoneId.systemDefault()
        val date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

        if (gameTitle.isNotBlank()) {
            viewModel.insertGame(Game(gameTitle, gamePlatform, date))
            findNavController().popBackStack()

        } else {
            Toast.makeText(
                activity,
                R.string.not_valid_game, Toast.LENGTH_SHORT
            ).show()
        }
    }
}