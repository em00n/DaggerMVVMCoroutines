package com.emon.dagger_mvvm_coroutines.ui.movie_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.emon.dagger_mvvm_coroutines.App
import com.emon.dagger_mvvm_coroutines.databinding.FragmentMovieListBinding
import com.emon.dagger_mvvm_coroutines.ui.view.BaseFragment
import com.emon.dagger_mvvm_coroutines.utils.autoCleared
import com.emon.dagger_mvvm_coroutines.viewmodel.MovieViewModel
import com.emon.dagger_mvvm_coroutines.viewmodel.MovieViewModelFactory
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieListFragment : BaseFragment(), MovieItemClickListener {
    private var binding: FragmentMovieListBinding by autoCleared()
    lateinit var movieListAdapter: MovieListAdapter

    private lateinit var movieViewModel: MovieViewModel

    @Inject
    lateinit var mainViewModelFactory: MovieViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (requireActivity().application as App).getApplicationComponent().inject(this)
        movieListAdapter = MovieListAdapter(this)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMovieListBinding.inflate(inflater, container, false)

        binding.movieListRV.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = movieListAdapter
        }

        getMoviesList()

        return binding.root
    }

    override fun onMovieItemClick(id: Int) {
        requireView().findNavController()
            .navigate(MovieListFragmentDirections.actionMovielistFragmentToMovieDetailsFragment(id))
    }

    private fun getMoviesList() {
        movieViewModel = ViewModelProvider(this, mainViewModelFactory)[MovieViewModel::class.java]

        lifecycleScope.launch {
            movieViewModel.getMoviesList().observe(viewLifecycleOwner) {
                it.let {
                    movieListAdapter.submitData(lifecycle, it)
                }
            }
        }
    }

}