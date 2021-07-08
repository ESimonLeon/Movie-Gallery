package com.example.examentvmaze.view.movie_detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.examentvmaze.MainActivity
import com.example.examentvmaze.R
import com.example.examentvmaze.constant.AppConstant.ARGS_DETAIL
import com.example.examentvmaze.databinding.FragmentDetailMovieBinding
import com.example.examentvmaze.view.movie_detail.adapter.GridMarginDecoration
import com.example.examentvmaze.view.movie_detail.adapter.MoviesCastAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailMovieFragment : Fragment() {

    lateinit var binding: FragmentDetailMovieBinding

    lateinit var mActivity: MainActivity
    val viewModel by viewModels<DetailMovieViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        FragmentDetailMovieBinding.inflate(inflater, container, false).apply {
            mActivity = activity as MainActivity
            binding = this
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewActionBar()

        getViewModel()

        createListener()

        createObserver()

    }

    private fun createListener() {
        binding.mbWebSite.setOnClickListener {
            val webpage = Uri.parse(viewModel.moviesDetail.value?.url)
            val myIntent = Intent(Intent.ACTION_VIEW, webpage)
            startActivity(myIntent);
        }
    }

    private fun createObserver() = with(viewModel) {
        error.observe(requireActivity(), {
            if (it == true) Toast.makeText(
                requireContext(),
                getString(R.string.error_not_found),
                Toast.LENGTH_LONG
            ).show()
        })
    }

    private fun viewActionBar() {
        val supportActionBar = (requireActivity() as AppCompatActivity).supportActionBar
        supportActionBar?.hide()
    }

    private fun getViewModel() = with(binding) {
        viewModelLayout = viewModel
        lifecycleOwner = this@DetailMovieFragment
        rvCast.adapter = MoviesCastAdapter()
        rvCast.addItemDecoration(GridMarginDecoration(resources.getDimension(R.dimen.dp_2)))
        viewModel.openDetailMovie(arguments?.getInt(ARGS_DETAIL)!!)
    }
}