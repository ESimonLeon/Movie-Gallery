package com.example.examentvmaze.view.movie_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.examentvmaze.binding_adapter.IBindingRecyclerAdapter
import com.example.examentvmaze.databinding.ItemMovieBinding
import com.example.examentvmaze.retrofit.model.TVMovie
import com.example.examentvmaze.view.movie_list.MoviesViewModel


class MoviesAdapter(val viewModel: MoviesViewModel) : RecyclerView.Adapter<MoviesAdapter.MoviesHolder>(),
    IBindingRecyclerAdapter<ArrayList<TVMovie>> {

    var moviesList = ArrayList<TVMovie>()

    class MoviesHolder(var binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesHolder {
        return MoviesHolder(
            ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MoviesHolder, position: Int) = with(holder.binding) {
        viewModelLayout = viewModel
        movie = moviesList[position]
    }

    override fun getItemCount(): Int = moviesList.size

    override fun setData(data: ArrayList<TVMovie>?) = data.let {
        if (!it.isNullOrEmpty()) {
            moviesList = it
            notifyDataSetChanged()
        }
    }

}
