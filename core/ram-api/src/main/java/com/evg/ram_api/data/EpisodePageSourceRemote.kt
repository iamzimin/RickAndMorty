package com.evg.ram_api.data

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.evg.ram_api.domain.Response
import com.evg.ram_api.domain.models.EpisodeFilterDTO
import com.evg.ram_api.domain.models.EpisodeResponse
import com.evg.ram_api.domain.repository.ApiRepository
import javax.inject.Inject

class EpisodePageSourceRemote @Inject constructor(
    private val apiRepository: ApiRepository,
): PagingSource<Int, EpisodeResponse>() {
    var filter = EpisodeFilterDTO()

    override fun getRefreshKey(state: PagingState<Int, EpisodeResponse>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, EpisodeResponse> {
        val page = params.key ?: 1

        when (val response = apiRepository.getAllEpisodesByPage(page = page, filter = filter)) {
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
                return LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                )
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