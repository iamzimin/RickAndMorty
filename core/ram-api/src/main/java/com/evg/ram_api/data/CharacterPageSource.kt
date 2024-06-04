package com.evg.ram_api.data

import android.net.Uri
import android.net.http.HttpException
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.evg.ram_api.domain.Response
import com.evg.ram_api.domain.models.CharactersResponse
import com.evg.ram_api.domain.repository.ApiRepository
import javax.inject.Inject

class CharacterPageSource @Inject constructor(
    private val apiRepository: ApiRepository,
): PagingSource<Int, CharactersResponse>() {
    override fun getRefreshKey(state: PagingState<Int, CharactersResponse>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharactersResponse> {
        val page = params.key ?: 1

        when (val response = apiRepository.getAllCharactersByPage(page = page)) {
            is Response.Success -> {
                val characters = response.data.results
                val prevKey = getPage(response.data.info.next)
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