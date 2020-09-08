package com.example.level5_task2.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.level5_task2.R
import com.example.level5_task2.adapters.GameAdapter
import com.example.level5_task2.model.Game
import com.example.level5_task2.viewmodels.GameViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_games.*


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class GamesFragment : Fragment() {

    private lateinit var gameAdapter: GameAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    private val viewModel: GameViewModel by viewModels()

    private var games: ArrayList<Game> = arrayListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_games, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRv();
        observeAddGameResult()
    }

    private fun initRv() {
        gameAdapter = GameAdapter(games)
        viewManager = LinearLayoutManager(activity)
        rvGames.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = gameAdapter
        }
        createItemTouchHelper().attachToRecyclerView(rvGames)

    }

    private fun observeAddGameResult() {
        viewModel.games.observe(viewLifecycleOwner, Observer { games ->
            this@GamesFragment.games.clear()
            this@GamesFragment.games.addAll(games)
            gameAdapter.notifyDataSetChanged()
        })
    }

    private fun createItemTouchHelper(): ItemTouchHelper {

        // Callback which is used to create the ItemTouch helper. Only enables left swipe.
        // Use ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) to also enable right swipe.
        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            // Enables or Disables the ability to move items up and down.
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            // Callback triggered when a user swiped an item.
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val reminderToDelete = games[position]
                viewModel.deleteGame(reminderToDelete)

            }
        }
        return ItemTouchHelper(callback)
    }
}