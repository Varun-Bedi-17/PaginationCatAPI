package com.example.paginationcatapi.models.api

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import retrofit2.HttpException
import java.io.IOException

private const val STARTING_PAGE_INDEX = 1


class ApiPagingSource(
    private val service: Api,
) : PagingSource<Int, ApiDataModelItem>() {

    /*override val keyReuseSupported: Boolean
        get() = true*/

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ApiDataModelItem> {
        val pageIndex = params.key ?: STARTING_PAGE_INDEX
        Log.i("KEY", "load: $pageIndex")
        return try {
            val response = service.getDataFromApi(
                limit = 100,
                page = pageIndex,
                order = "DESC"
            )
            LoadResult.Page(
                data = response,
                prevKey = if (pageIndex == STARTING_PAGE_INDEX) null else pageIndex - 1,
                nextKey = if (response.isEmpty()) null else pageIndex + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        } catch (e : Exception){
            return LoadResult.Error(e)
        }
    }

    /**
     * The refresh key is used for subsequent calls to PagingSource.Load after the initial load.
     */
    override fun getRefreshKey(state: PagingState<Int, ApiDataModelItem>): Int? {
        // We need to get the previous key (or next key if previous is null) of the page
        // that was closest to the most recently accessed index.
        // Anchor position is the most recently accessed index.
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}