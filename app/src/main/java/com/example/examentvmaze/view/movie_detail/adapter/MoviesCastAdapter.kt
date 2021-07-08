package com.example.examentvmaze.view.movie_detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.examentvmaze.binding_adapter.IBindingRecyclerAdapter
import com.example.examentvmaze.databinding.ItemCastMovieBinding
import com.example.examentvmaze.retrofit.model.TVCast

class MoviesCastAdapter : RecyclerView.Adapter<MoviesCastAdapter.MoviesCastHolder>(),
    IBindingRecyclerAdapter<ArrayList<TVCast>> {

    var moviesCastList = ArrayList<TVCast>()

    class MoviesCastHolder(var binding: ItemCastMovieBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesCastHolder {
        return MoviesCastHolder(
            ItemCastMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MoviesCastHolder, position: Int) = with(holder.binding) {
        castMovie = moviesCastList[position]
    }

    override fun getItemCount(): Int = moviesCastList.size

    override fun setData(data: ArrayList<TVCast>?) = data.let {
        if (!it.isNullOrEmpty()) {
            moviesCastList = it
            notifyDataSetChanged()
        }
    }

}
