package com.evg.database.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.evg.database.domain.models.CharacterDBO
import com.evg.database.domain.repository.DatabaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CharacterPageSourceLocal @Inject constructor(
    private val databaseRepository: DatabaseRepository,
): PagingSource<Int, CharacterDBO>() {
    private var totalLoadedCount = 0

    override fun getRefreshKey(state: PagingState<Int, CharacterDBO>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterDBO> {
        val page = params.key ?: 0
        val pageSize = params.loadSize

        try {
            val characters = withContext(Dispatchers.IO) {
                databaseRepository.getAllCharactersByPage(pageSize = params.loadSize, offset = page * params.loadSize)
            }
            totalLoadedCount += characters.size

            val prevKey = if (page == 0) null else page - 1
            val nextKey = if (characters.isEmpty()) null else page + 1

            return LoadResult.Page(
                data = characters,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}