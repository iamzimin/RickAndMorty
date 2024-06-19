package com.evg.database.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.evg.database.domain.models.LocationDBO
import com.evg.database.domain.models.LocationFilterDB
import com.evg.database.domain.repository.DatabaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LocationPageSourceLocal @Inject constructor(
    private val databaseRepository: DatabaseRepository,
): PagingSource<Int, LocationDBO>() {
    private var totalLoadedCount = 0
    var filter = LocationFilterDB()

    override fun getRefreshKey(state: PagingState<Int, LocationDBO>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, LocationDBO> {
        val page = params.key ?: 0
        val pageSize = params.loadSize

        try {
            val location = withContext(Dispatchers.IO) {
                databaseRepository.getAllLocationsByPage(
                    pageSize = params.loadSize,
                    offset = page * params.loadSize,
                    filter = filter,
                )
            }
            totalLoadedCount += location.size

            val prevKey = if (page == 0) null else page - 1
            val nextKey = if (location.isEmpty()) null else page + 1

            return LoadResult.Page(
                data = location,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}