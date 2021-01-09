package com.azuka.themovieapp.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.azuka.themovieapp.data.BaseResponse
import com.azuka.themovieapp.data.source.local.LocalDataSource
import com.azuka.themovieapp.data.source.remote.RemoteDataSource
import com.azuka.themovieapp.data.source.remote.response.MovieResponse
import com.azuka.themovieapp.data.source.remote.response.TvSeriesResponse
import com.azuka.themovieapp.presentation.entity.Movie
import com.azuka.themovieapp.presentation.entity.TvSeries
import com.azuka.themovieapp.utils.CoroutineContextProvider
import com.azuka.themovieapp.utils.mapper.MovieDataMapper
import com.azuka.themovieapp.utils.mapper.TvSeriesDataMapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val remoteSource: RemoteDataSource,
    private val localSource: LocalDataSource,
    private val coroutineProvider: CoroutineContextProvider
) : Repository {
    override fun getMovies(): LiveData<List<Movie>> {
        val movies = MutableLiveData<List<Movie>>()
        remoteSource.getMovies(object : RemoteDataSource.LoadMovieCallback {
            override fun onMovieReceived(movieResponse: BaseResponse<MovieResponse>) {
                val movieList = MovieDataMapper.mapResponsesToDomains(movieResponse.results)
                movies.postValue(movieList)
            }
        })

        return movies
    }

    override fun getTvSeries(): LiveData<List<TvSeries>> {
        val tvSeries = MutableLiveData<List<TvSeries>>()
        remoteSource.getTvSeries(object : RemoteDataSource.LoadTvSeriesCallback {
            override fun onTvSeriesReceived(tvSeriesResponse: BaseResponse<TvSeriesResponse>) {
                val tvSeriesList =
                    TvSeriesDataMapper.mapResponsesToDomains(tvSeriesResponse.results)
                tvSeries.postValue(tvSeriesList)
            }

        })

        return tvSeries
    }

    override fun getMovieDetail(movieId: Long): LiveData<Movie> {
        val movieDetail = MutableLiveData<Movie>()
        remoteSource.getMovieDetail(movieId) { movieResponse ->
            movieDetail.postValue(MovieDataMapper.mapResponseToDomain(movieResponse))
        }

        return movieDetail
    }

    override fun getTvSeriesDetail(tvSeriesId: Long): LiveData<TvSeries> {
        val tvSeriesDetail = MutableLiveData<TvSeries>()
        remoteSource.getTvSeriesDetail(tvSeriesId) { tvSeriesResponse ->
            tvSeriesDetail.postValue(TvSeriesDataMapper.mapResponseToDomain(tvSeriesResponse))
        }

        return tvSeriesDetail
    }

    override fun getFavoriteMovies(): LiveData<List<Movie>> {
        return Transformations.map(localSource.getFavoriteMovies()) {
            MovieDataMapper.mapEntitiesToDomains(it)
        }
    }

    override fun getFavoriteTvShow(): LiveData<List<TvSeries>> {
        return Transformations.map(localSource.getTvSeries()) {
            TvSeriesDataMapper.mapEntitiesToDomains(it)
        }
    }

    override fun insertFavoriteMovie(movie: Movie) {
        CoroutineScope(coroutineProvider.backgroundDispatcher()).launch {
            localSource.insertFavoriteMovie(MovieDataMapper.mapDomainToEntity(movie))
        }
    }

    override fun insertFavoriteTvSeries(tvSeries: TvSeries) {
        CoroutineScope(coroutineProvider.backgroundDispatcher()).launch {
            localSource.insertFavoriteTvSeries(TvSeriesDataMapper.mapDomainToEntity(tvSeries))
        }
    }
}