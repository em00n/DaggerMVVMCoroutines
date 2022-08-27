package com.emon.dagger_mvvm_coroutines.ui.moviedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.emon.dagger_mvvm_coroutines.App
import com.emon.dagger_mvvm_coroutines.databinding.FragmentMovieDetailsBinding
import com.emon.dagger_mvvm_coroutines.ui.view.BaseFragment
import com.emon.dagger_mvvm_coroutines.utils.Constants
import com.emon.dagger_mvvm_coroutines.utils.Resource
import com.emon.dagger_mvvm_coroutines.utils.autoCleared
import com.emon.dagger_mvvm_coroutines.viewmodel.MovieViewModel
import com.emon.dagger_mvvm_coroutines.viewmodel.MovieViewModelFactory
import javax.inject.Inject

class MovieDetailsFragment : BaseFragment() {
    private var binding: FragmentMovieDetailsBinding by autoCleared()
    var movieId: Int? = null

    private lateinit var movieViewModel :MovieViewModel
    @Inject
    lateinit var mainViewModelFactory: MovieViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().application as App).getApplicationComponent().inject(this)

        arguments?.let {
            movieId = it.getInt("com_emon_dagger_mvvm_coroutines_movie_id")
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)

        movieId?.let { it1 -> getMovieDetails(it1) }

        return binding.root
    }

    private fun getMovieDetails(movieId: Int) {
        movieViewModel = ViewModelProvider(this, mainViewModelFactory)[MovieViewModel::class.java]

        movieViewModel.getMovieDetails(movieId).observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    try {
                        it.data.let { it2 ->
                            binding.movieTitle.text = "Title : " + it2?.title
                            binding.movieLanguage.text = "Language : " + it2?.originalLanguage
                            binding.movieOverview.text = it2?.overview
                            binding.movieImage.load(Constants.BASE_URL_IMAGE + it2?.posterPath)
                        }

                    } catch (e: Exception) {
                    }

                }
                Resource.Status.LOADING -> {

                }
                Resource.Status.ERROR -> {
                    showToast(it.message)
                }
            }
        })
    }


}