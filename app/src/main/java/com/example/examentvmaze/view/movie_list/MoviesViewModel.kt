package com.example.examentvmaze.view.movie_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.examentvmaze.getDateNow
import com.example.examentvmaze.retrofit.model.TVMovie
import com.example.examentvmaze.retrofit.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _moviesList = MutableLiveData<ArrayList<TVMovie>>()
    val moviesList: LiveData<ArrayList<TVMovie>> get() = _moviesList

    val _updateLoadMovies = MutableLiveData<Boolean>(false)
    val updateLoadMovies: LiveData<Boolean> get() = _updateLoadMovies

    private val _openDetail = MutableLiveData<Boolean>()
    val openDetail: LiveData<Boolean> get() = _openDetail

    private val _error = MutableLiveData<Boolean>(false)
    val error: LiveData<Boolean> get() = _error

    var idMovieDetail = 0

    fun loadMovies() = viewModelScope.launch {
        val repository = withContext(IO) {
            repository.getMovies(getDateNow())
        }
        createResult(repository)
    }

    fun getSearchMovies(query: String?) = viewModelScope.launch {
        val repository = withContext(IO) {
            repository.getSearchMovies(query)
        }
        createResult(repository)
    }

    private fun createResult(repository: Response<ArrayList<TVMovie>>) =
        when (repository.isSuccessful) {
            true -> {
                _moviesList.postValue(repository.body())
                _updateLoadMovies.value = false
            }
            else -> _error.postValue(true)
        }

    fun selectMovie(idMovie: Int) = with(_openDetail) {
        idMovieDetail = idMovie
        postValue(true)
    }

    fun resetDetail() = with(_openDetail) {
        postValue(false)
    }

    fun refreshList() = with(_updateLoadMovies) {
        loadMovies()
        value = true
    }

}