package com.example.piedrasnaturales

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.piedrasnaturales.model.JoyasDAO
import com.example.piedrasnaturales.model.JoyasDB
import com.example.piedrasnaturales.model.PiedrasItem
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TestDao {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var mJoyasDAO: JoyasDAO
    private lateinit var db: JoyasDB

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, JoyasDB::class.java).build()
        mJoyasDAO = db.getJoyasDAO()
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun insertListElements() = runBlocking {
        //given
        val joyasList = listOf(PiedrasItem("PZ-01",
                "",
                1,
                "https://github.com/SebaChuaqui/PiedrasNaturales/blob/master/images/PZ01-removebg-preview.png",
                "Pulsera Jaspe rojo / turquesa",
                "\$4900"))

        // when
        mJoyasDAO.insertAllJoyas(joyasList)

        //then
        mJoyasDAO.getAllJoyasFromDB().observeForever{
            assertThat(it).isNotNull()
            println(it.toString())
            assertThat(it[0].codigo).isEqualTo("PZ-01")

        }
    }

}