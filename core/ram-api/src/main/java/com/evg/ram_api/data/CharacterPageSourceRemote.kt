package com.evg.ram_api.data

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.evg.ram_api.domain.Response
import com.evg.ram_api.domain.models.CharacterFilterDTO
import com.evg.ram_api.domain.models.CharactersResponse
import com.evg.ram_api.domain.repository.ApiRepository
import javax.inject.Inject

class CharacterPageSourceRemote @Inject constructor(
    private val apiRepository: ApiRepository,
): PagingSource<Int, CharactersResponse>() {
    var filter = CharacterFilterDTO()

    override fun getRefreshKey(state: PagingState<Int, CharactersResponse>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharactersResponse> {
        val page = params.key ?: 1

        when (val response = apiRepository.getAllCharactersByPage(page = page, filter = filter)) {
            is Response.Success -> {
                val characters = response.data.results
                val prevKey = getPage(response.data.info.prev)
                val nextKey = getPage(response.data.info.next)

                return LoadResult.Page(
                    data = characters,
                    prevKey = prevKey,
                    nextKey = nextKey
                )
            }
            is Response.Failure -> {
                val exception = response.exception
                return LoadResult.Error(exception)
            }
        }
    }

    private fun getPage(page: String?): Int? {
        if (page == null) return null

        val uri = Uri.parse(page)
        val pageQuery = uri.getQueryParameter("page")
        return pageQuery?.toInt()
    }
}