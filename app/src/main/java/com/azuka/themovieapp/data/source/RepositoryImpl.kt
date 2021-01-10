package com.azuka.themovieapp.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.azuka.themovieapp.data.BaseResponse
import com.azuka.themovieapp.data.source.local.LocalDataSource
import com.azuka.themovieapp.data.source.remote.RemoteDataSource
import com.azuka.themovieapp.data.source.remote.response.MovieResponse
import com.azuka.themovieapp.data.source.remote.response.TvSeriesResponse
import com.azuka.themovieapp.presentation.entity.FavoriteGeneral
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

    override fun getFavoriteMovies(): LiveData<PagedList<FavoriteGeneral>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()

        return LivePagedListBuilder(localSource.getFavoriteMovies(), config).build()
    }

    override fun getFavoriteTvShow(): LiveData<PagedList<FavoriteGeneral>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()

        val favoriteSource = localSource.getFavoriteTvSeries().map {
            with(it) {
                FavoriteGeneral(
                    id = id,
                    title = name,
                    overview = overview,
                    voteAverage = voteAverage,
                    voteCount = voteCount,
                    releaseDate = firstAirDate,
                    originalLanguage = originalLanguage,
                    posterPath = posterPath
                )
            }
        }
        return LivePagedListBuilder(favoriteSource, config).build()
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

    override fun removeFavoriteMovie(movieId: Long) {
        CoroutineScope(coroutineProvider.backgroundDispatcher()).launch {
            localSource.deleteFavoriteMovie(movieId)
        }
    }

    override fun removeFavoriteTvSeries(tvSeriesId: Long) {
        CoroutineScope(coroutineProvider.backgroundDispatcher()).launch {
            localSource.deleteFavoriteTvSeries(tvSeriesId)
        }
    }

    override fun checkIfFavoriteMovie(movieId: Long): LiveData<Boolean> {
        return Transformations.switchMap(localSource.checkIfFavoriteMovie(movieId)) {
            val result = MutableLiveData<Boolean>()
            result.value = it.isNotEmpty()
            result
        }
    }

    override fun checkIfFavoriteTvSeries(tvSeriesId: Long): LiveData<Boolean> {
        return Transformations.switchMap(localSource.checkIfFavoriteTvSeries(tvSeriesId)) {
            val result = MutableLiveData<Boolean>()
            result.value = it.isNotEmpty()
            result
        }
    }
}