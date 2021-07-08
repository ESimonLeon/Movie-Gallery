package com.example.examentvmaze

import android.app.SearchManager
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import com.example.examentvmaze.constant.AppConstant.ARGS_DETAIL
import com.example.examentvmaze.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    var queryTextSearch: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        when (resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK) {
            Configuration.SCREENLAYOUT_SIZE_LARGE -> {
                Log.wtf(javaClass.simpleName, " screenLayout SCREENLAYOUT_SIZE_LARGE")
            }
            Configuration.SCREENLAYOUT_SIZE_SMALL, Configuration.SCREENLAYOUT_SIZE_NORMAL -> {
                requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                Log.wtf(javaClass.simpleName, " screenLayout SCREENLAYOUT_SIZE_SMALL")
            }
            else -> {
                Log.wtf(javaClass.simpleName, " screenLayout ninguno")
            }
        }



        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getExtraSearch()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        getExtraSearch()
    }

    private fun getExtraSearch() {
        queryTextSearch = if (Intent.ACTION_SEARCH == intent.action) {
            intent.getStringExtra(SearchManager.QUERY)
        } else null
    }

    fun openDetail(idMovie: Int) = with(binding.fNavHost) {

        findNavController().apply {
            if (currentDestination?.id == R.id.nav_fragmentMovies) {
                val bundle = bundleOf(ARGS_DETAIL to idMovie)
                navigate(R.id.nav_moviesToDetail, bundle)
            }
        }
    }


}