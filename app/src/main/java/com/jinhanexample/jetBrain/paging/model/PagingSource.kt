package com.jinhanexample.jetBrain.paging.model

import androidx.paging.PagingSource

class PagingSource(
    private val repository: PagingRepository,
) : PagingSource<Int, String>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, String> {
        return try {
            val nextPage = params.key ?: 1
            val response = repository.getPagingData(nextPage)

            LoadResult.Page(
                data = response.first,
                prevKey = response.second?.minus(2),
                nextKey = response.second
            )

        } catch (e: Exception) {
            LoadResult.Error(Throwable(e.message))
        }
    }

}
