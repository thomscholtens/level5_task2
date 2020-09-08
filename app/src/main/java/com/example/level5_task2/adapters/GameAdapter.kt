package com.example.level5_task2.adapters

import android.content.Context
import android.content.res.Resources
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.TypedArrayUtils.getText
import androidx.recyclerview.widget.RecyclerView
import com.example.level5_task2.R
import com.example.level5_task2.model.Game
import com.google.android.material.internal.ContextUtils.getActivity
import kotlinx.android.synthetic.main.item_game.view.*
import java.security.AccessController.getContext
import java.text.SimpleDateFormat

class GameAdapter(private val games: List<Game>) : RecyclerView.Adapter<GameAdapter.ViewHolder>(){

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun databind(game: Game) {
            itemView.tvTitle.text = game.title;
            itemView.tvPlatform.text = game.platform;

            val simpleDateFormat = SimpleDateFormat("dd MMM yyyy")
            val date = simpleDateFormat.format(game.releaseDate)
            itemView.tvRelease.text = itemView.context.getString(R.string.release, date);
        }
    }


    /**
     * Creates and returns a ViewHolder object, inflating a standard layout called simple_list_item_1.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_game, parent, false)
        )
    }

    /**
     * Returns the size of the list
     */
    override fun getItemCount(): Int {
        return games.size
    }

    /**
     * Called by RecyclerView to display the data at the specified position.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.databind(games[position])
    }

}