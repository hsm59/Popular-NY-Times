package com.hussainm.popularnytimes.articles.view

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.gson.Gson
import com.hussainm.popularnytimes.MainCoroutineRule
import com.hussainm.popularnytimes.MockResponseFileReader
import com.hussainm.popularnytimes.articles.repository.PopularArticlesRepo
import com.hussainm.popularnytimes.getOrAwaitValueTest
import com.hussainm.popularnytimes.network.RemoteDataStore
import com.hussainm.popularnytimes.network.utility.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class PopularArticlesViewModelTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val server: MockWebServer = MockWebServer()
    private val MOCK_WEBSERVER_PORT = 8080

    lateinit var remoteDataStore: RemoteDataStore
    lateinit var popularArticlesRepo: PopularArticlesRepo
    lateinit var popularArticlesViewModel: PopularArticlesViewModel

    @Before
    fun init() {
        server.start(MOCK_WEBSERVER_PORT)

        val retrofit = Retrofit.Builder()
            .baseUrl(server.url("/"))
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()

        remoteDataStore = RemoteDataStore(retrofit)

        popularArticlesRepo = PopularArticlesRepo(remoteDataStore)

        popularArticlesViewModel = PopularArticlesViewModel(popularArticlesRepo)
    }

    @Test
    fun `articles are fetched successfully`() = runBlocking {

            server.apply {
                enqueue(
                    MockResponse().setBody(MockResponseFileReader("ny_times_articles_success.json").content)
                )
            }

            popularArticlesViewModel.fetchArticles()

            delay(2000)

            val response = popularArticlesViewModel.articles.getOrAwaitValueTest()


            assertEquals(response.status, Result.Status.SUCCESS)
    }

    @Test
    fun `articles failed to fetch`() = runBlocking {

        server.apply {
            enqueue(
                MockResponse()
                    .setBody(MockResponseFileReader("ny_times_articles_error.json").content)
                    .setResponseCode(400)
            )
        }

        popularArticlesViewModel.fetchArticles()

        delay(2000)

        val response = popularArticlesViewModel.articles.getOrAwaitValueTest()


        assertEquals(response.status, Result.Status.ERROR)

    }

    @After
    fun shutdown() {
        server.shutdown()
    }

}