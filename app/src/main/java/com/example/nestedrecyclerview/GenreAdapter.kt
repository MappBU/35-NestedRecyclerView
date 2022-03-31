package com.example.nestedrecyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nestedrecyclerview.databinding.GenresItemBinding

// var genres: ArrayList<GenreModel> - создаем списочный массив типа GenreModel (Шаблон-класс)
class GenreAdapter(val context: Context, var genres: ArrayList<GenreModel>): RecyclerView.Adapter<GenreHolder>() {

    // Создаем объект байндинга в Адаптере
    // Прикрепляем верстку 1 пункта первого RV
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding : GenresItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.genres_item,parent,false)
        return GenreHolder(binding)
    }

    // holder.binding.nameGenres.text = genres[position].nameGenre - В элемент списка подгружаем данные из массива.
    // loadMovies - вызываем функцию для второго RV.
    override fun onBindViewHolder(holder: GenreHolder, position: Int) {
        holder.binding.nameGenres.text = genres[position].nameGenre
        loadMovies(holder.binding.catalogWithMovies, genres[position].catalogWithMovies)
    }

    override fun getItemCount(): Int {
        return genres.size
    }

    // Метод Нужен для второго RV
    private fun loadMovies(recyclerView:RecyclerView, movies: ArrayList<MovieModel>) {

        // Инициализируем movieAdapter
        val movieAdapter = MovieAdapter(movies)
        // Второй RV будет листаться горизонтально
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        // Прикрепляем ко второму RV Адаптер
        recyclerView.adapter = movieAdapter

    }
}

// Здесь метод bind ненужен.
class GenreHolder(val binding: GenresItemBinding): RecyclerView.ViewHolder(binding.root) {

}
