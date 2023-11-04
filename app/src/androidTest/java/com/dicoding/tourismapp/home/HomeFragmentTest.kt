package com.dicoding.tourismapp.home

import com.septalfauzan.core.data.TourismRepository
import com.dicoding.tourismapp.core.di.*
import com.septalfauzan.core.domain.model.Tourism
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.loadKoinModules
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import kotlin.test.assertNotNull

class HomeFragmentTest : KoinTest {
    private val repository: TourismRepository by inject()
    private val dummyData = Tourism(
        tourismId = "-",
        name = "gemah",
        description = "-",
        address = "tulungagung",
        latitude = 0.0,
        longitude = 0.0,
        like = 1,
        image = "-",
        isFavorite = false,
    )

        @Before
        fun setup() {
            loadKoinModules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule,
                )
            )
        }

        @After
        fun tearDown() {
            stopKoin()
        }

        @Test
        fun check_data_from_repository_is_not_null() {
            val response = repository.getFavoriteTourism()
            println(repository.getFavoriteTourism())

            assertNotNull(response)
        }
}