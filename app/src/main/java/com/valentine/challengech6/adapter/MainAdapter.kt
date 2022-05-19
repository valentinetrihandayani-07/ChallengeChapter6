package com.valentine.challengech6.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.valentine.challengech6.databinding.ItemMovieBinding
import com.valentine.challengech6.model.DetailMovie
import com.valentine.challengech6.model.Result
import com.valentine.challengech6.ui_screen.home.HomeFragmentDirections


class MainAdapter(
    private val item: List<Result>
) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {


    class MainViewHolder(val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.binding.tvTitle.text = item[position].title
        holder.binding.tvOverview.text = item[position].overview


        Glide.with(holder.itemView.context)
            .load("https://image.tmdb.org/t/p/w780/" + item[position].backdropPath)
            .into(holder.binding.ivImage);


        holder.itemView.setOnClickListener {
            var image = item[position].backdropPath.toString()
            var title = item[position].title.toString()
            var mediatype = item[position].mediaType.toString()
            var date = item[position].releaseDate.toString()
            var overview = item[position].overview.toString()

            var detail = DetailMovie(image,title,mediatype, date, overview)

            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(detail)
            it.findNavController().navigate(action)



        }
    }

    override fun getItemCount(): Int {
        return item.size
    }
}