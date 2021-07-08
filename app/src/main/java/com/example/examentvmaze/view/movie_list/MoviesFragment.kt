package com.example.examentvmaze.view.movie_list

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.SearchView
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.examentvmaze.*
import com.example.examentvmaze.databinding.FragmentMoviesBinding
import com.example.examentvmaze.view.movie_list.adapter.MoviesAdapter
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class MoviesFragment : Fragment() {

    lateinit var binding: FragmentMoviesBinding
    val viewModel by viewModels<MoviesViewModel>()

    lateinit var mActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        FragmentMoviesBinding.inflate(inflater, container, false).apply {
            mActivity = activity as MainActivity
            binding = this
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        viewActionBar()

        getViewModel()

        createObservers()
    }

    private fun viewActionBar() {
        when (mActivity.queryTextSearch) {
            null -> mActivity.title = getTitleToolbar()
            else -> mActivity.title = mActivity.queryTextSearch
        }
        val supportActionBar = (requireActivity() as AppCompatActivity).supportActionBar
        supportActionBar?.show()
    }

    private fun getViewModel() = with(binding) {
        viewModelLayout = viewModel

        lifecycleOwner = this@MoviesFragment

        rvList.adapter = MoviesAdapter(viewModel)

        recyclerViewSpanCount(requireContext()).apply {
            rvList.layoutManager =
                if (this == 1) {
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                } else GridLayoutManager(requireContext(), this)

        }

        rvList.addItemDecoration(DividerItemDecoration(requireContext(), LinearLayout.VERTICAL))

        getViewData()
    }

    private fun getViewData() {
        when (mActivity.queryTextSearch) {
            null -> getMovies()
            else -> viewModel.getSearchMovies(mActivity.queryTextSearch)
        }
    }

    private fun createObservers() = with(viewModel) {
        updateLoadMovies.observe(requireActivity(), {
            if (it == true) {
                mActivity.queryTextSearch = null
                viewActionBar()
            }
        })

        openDetail.observe(requireActivity(), {
            if (it == true) mActivity.openDetail(viewModel.idMovieDetail)
        })

        error.observe(requireActivity(), {
            if (it == true) Toast.makeText(
                requireContext(),
                getString(R.string.error_not_found),
                Toast.LENGTH_LONG
            ).show()
        })
    }

    private fun getMovies() {
        if (viewModel.moviesList.value == null)
            viewModel.loadMovies()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.menu_activity, menu)

        val searchManager = activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (menu.findItem(R.id.search).actionView as SearchView).apply {
            setSearchableInfo(searchManager.getSearchableInfo(activity?.componentName))
        }

    }

    override fun onStop() {
        super.onStop()
        viewModel.resetDetail()
    }

}