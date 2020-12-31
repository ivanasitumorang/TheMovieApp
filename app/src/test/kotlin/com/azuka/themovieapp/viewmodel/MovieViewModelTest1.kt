package com.azuka.themovieapp.viewmodel

/**
 * Created by ivanaazuka on 27/11/20.
 * Android Engineer
 */
//
//object MovieViewModelTest : Spek({
//
//
//
//
//
//    Feature("Movie View Model") {
//        val dummy: Dummy = mock()
//        val mockRepository: Repository = mock()
//        val viewModel by memoized { MovieViewModel(mockRepository) }
//
//        val observer: Observer<List<Movie>> = mock()
//
//        beforeEachTest {
//            ArchTaskExecutor.getInstance().setDelegate(object : TaskExecutor() {
//                override fun executeOnDiskIO(runnable: Runnable) {
//                    runnable.run()
//                }
//
//                override fun isMainThread(): Boolean {
//                    return true
//                }
//
//                override fun postToMainThread(runnable: Runnable) {
//                    runnable.run()
//                }
//            })
//        }
//
//        afterEachTest { ArchTaskExecutor.getInstance().setDelegate(null) }
//
//
//        Scenario("Successfully get all movie data") {
//            val dummyMovieList = TestUtils.getMovieDataFromJson()
//            val movieListLiveData = MutableLiveData<List<Movie>>()
//            movieListLiveData.value = dummyMovieList
//
//            lateinit var result: List<Movie>
//
//            beforeEachGroup {
//                doReturn(movieListLiveData)
//                    .`when`(mockRepository)
//                    .getMovies()
//            }
//
////            Given("the list of movies should be returned") {
////                doReturn(movieListLiveData)
////                    .`when`(mockRepository)
////                    .getMovies()
////            }
//
//            When("trigger get all movie data") {
//                viewModel.getMovieList()
//                val movieList = viewModel.getMovieList().value
////                result = viewModel.getMovieList()
//            }
////
////            Then("make sure the repository.getMovieList() is called") {
////                verify(mockRepository).getMovies()
////            }
//
////            Then("should return list of movies result") {
////                assertEquals(movieList, result)
////            }
//        }
//
////        Scenario("Successfully get all movie data but is empty") {
////
////            val emptyMovieList = TestUtils.getEmptyMovieData()
////
////            lateinit var result: List<Movie>
////
////            Given("the empty data should be returned") {
////                doReturn(BaseResponse<Movie>())
////                    .`when`(dummy)
////                    .getDummyMovies()
////            }
////
////            When("trigger get all movie data") {
////                result = viewModel.getMoviesDummy()
////            }
////
////            Then("should return empty list of movie") {
////                assertEquals(emptyMovieList, result)
////            }
////        }
//
////        Scenario("Failed get all movie data") {
////
////            val emptyMovieList = TestUtils.getEmptyMovieData()
////
////            lateinit var result: List<Movie>
////
////            Given("the empty data should be returned when failed") {
////                doReturn(BaseResponse<Movie>())
////                    .`when`(dummy)
////                    .getDummyMovies()
////            }
////
////            When("trigger get all movie data") {
////                result = viewModel.getMoviesDummy()
////            }
////
////            Then("should return empty list of movie") {
////                assertEquals(emptyMovieList, result)
////            }
////        }
//    }
//})