package com.example.examentvmaze.view.movie_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.examentvmaze.retrofit.model.TVCast
import com.example.examentvmaze.retrofit.model.TVShow
import com.example.examentvmaze.retrofit.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailMovieViewModel @Inject constructor(var repository: Repository) : ViewModel() {

    private val _moviesDetail = MutableLiveData<TVShow>()
    val moviesDetail: LiveData<TVShow> get() = _moviesDetail

    private val _movieCast = MutableLiveData<ArrayList<TVCast>>()
    val movieCast: LiveData<ArrayList<TVCast>> get() = _movieCast

    private val _loadProgress = MutableLiveData<Boolean>()
    val loadProgress: LiveData<Boolean> get() = _loadProgress

    private val _error = MutableLiveData<Boolean>(false)
    val error: LiveData<Boolean> get() = _error

    fun openDetailMovie(idMovie: Int) = viewModelScope.launch {

        _loadProgress.postValue(true)

        getMovieCast(idMovie)

        val repository = withContext(IO) {
            repository.getDetailMovie(idMovie)
        }

        when (repository.isSuccessful) {
            true -> {
                _moviesDetail.postValue(repository.body())
            }
            else -> _error.postValue(true)
        }

    }

    private fun getMovieCast(idMovie: Int) = viewModelScope.launch {

        val repository = withContext(IO) {
            repository.getDetailCastMovie(idMovie)
        }

        when (repository.isSuccessful) {
            true -> {
                _movieCast.postValue(repository.body())
                _loadProgress.postValue(false)
            }
            else -> _error.postValue(true)
        }

    }
}