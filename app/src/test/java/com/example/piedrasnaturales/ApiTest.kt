package com.example.piedrasnaturales

import com.example.piedrasnaturales.Retrofit.ApiJoyas
import com.example.piedrasnaturales.model.PiedrasItem
import com.google.common.truth.Truth
import com.google.common.truth.Truth.assertThat
import com.google.gson.Gson
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiTest {

    lateinit var mMockWebServer: MockWebServer
    lateinit var ApiJoyas : ApiJoyas

    @Before
    fun setUp() {
        mMockWebServer = MockWebServer()
        val retro = Retrofit.Builder()
            .baseUrl(mMockWebServer.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        ApiJoyas =  retro.create(com.example.piedrasnaturales.Retrofit.ApiJoyas::class.java)
    }

    @After
    fun shutDown() {
        mMockWebServer.shutdown()
    }

    @Test
    fun getAllDrugstore_happy_case() = runBlocking {
        //given
        val mresultList = listOf<PiedrasItem>(
            PiedrasItem("PZ-01",
            "",
            1, "https://github.com/SebaChuaqui/PiedrasNaturales/blob/master/images/PZ01-removebg-preview.png?raw=true",
            "Pulsera Jaspe rojo / turquesa"
            ,"\$4900")
        )
        mMockWebServer.enqueue(MockResponse().setBody(Gson().toJson(mresultList)))

        //when
        val result = ApiJoyas.getDataFromApiCoroutines()
        //then
        assertThat(result).isNotNull()
        assertThat(result.isSuccessful).isTrue()
        val message = result.body()
        assertThat(message).hasSize(1)
        println(message.toString())
        assertThat(message?.get(0)?.codigo?.contains("PZ-01")).isTrue()
        val request = mMockWebServer.takeRequest()
        Truth.assertThat(request.path).isEqualTo("/joyas/")
    }


}