package com.evg.database.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.evg.database.domain.models.EpisodeDBO
import com.evg.database.domain.models.EpisodeFilterDB
import com.evg.database.domain.repository.DatabaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class EpisodePageSourceLocal @Inject constructor(
    private val databaseRepository: DatabaseRepository,
): PagingSource<Int, EpisodeDBO>() {
    private var totalLoadedCount = 0
    var filter = EpisodeFilterDB()

    override fun getRefreshKey(state: PagingState<Int, EpisodeDBO>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, EpisodeDBO> {
        val page = params.key ?: 0
        val pageSize = params.loadSize

        try {
            val episodes = withContext(Dispatchers.IO) {
                databaseRepository.getAllEpisodesByPage(
                    pageSize = params.loadSize,
                    offset = page * params.loadSize,
                    filter = filter,
                )
            }
            totalLoadedCount += episodes.size

            val prevKey = if (page == 0) null else page - 1
            val nextKey = if (episodes.isEmpty()) null else page + 1

            return LoadResult.Page(
                data = episodes,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}