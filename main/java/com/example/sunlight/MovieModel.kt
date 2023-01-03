package com.example.sunlight

class MovieModel(title: String?, genre: String?) {
    private var title: String
    private var genre: String
    init {
        this.title = title!!
        this.genre = genre!!
    }
    fun getTitle(): String {
        return title
    }
    fun setTitle(name: String?) {
        title = name!!
    }
    fun getGenre(): String {
        return genre
    }
    fun setGenre(genre: String?) {
        this.genre = genre!!
    }
}