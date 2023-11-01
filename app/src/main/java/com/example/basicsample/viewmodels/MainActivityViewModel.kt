package com.example.basicsample.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.basicsample.models.api.ApiDataModel
import com.example.basicsample.models.api.ApiHelper
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {

    private val apiService = ApiHelper.api

    var dataObserver = MutableLiveData<ApiDataModel>()
        private set

    fun getDataFromViewModel() {
        viewModelScope.launch {
            dataObserver.value = apiService.getDataFromApi(100, 10, "DESC")
        }
    }

    /*fun getDataUsingPagination() : Flow<PagingData<ApiDataModelItem>> {
        return   Pager(
            config = PagingConfig(
                pageSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                ApiPagingSource(service = apiService)
            }
        ).flow

    }*/

    /*fun getData() : Flow<PagingData<ApiDataModelItem>>{
        getDataUsingPagination().map {pagingData ->
            pagingData.map {
                 it
            }

        }.cachedIn(viewModelScope)
    }*/
}