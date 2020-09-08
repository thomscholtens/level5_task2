package com.example.level5_task2.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.level5_task2.R
import com.example.level5_task2.viewmodels.GameViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: GameViewModel
    private lateinit var navController: NavController
    private var hideMenuOptions = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))



        navController = findNavController(R.id.nav_host_fragment)
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            navController.navigate(R.id.action_gamesFragment_to_addGameFragment)
        }
        buttonsToggler()
        initViewModel()
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)
    }

    private fun buttonsToggler() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id in arrayOf(R.id.addGameFragment)) {
                hideMenuOptions = true
                invalidateOptionsMenu();
                fab.hide()
            } else {
                hideMenuOptions = false
                invalidateOptionsMenu();
                fab.show()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        if (hideMenuOptions) {
            menu.getItem(0).isVisible = false
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_delete_all -> {
                viewModel.removeAllGames()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}