package com.example.basicsample.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.basicsample.models.api.ApiDataModel
import com.example.basicsample.models.api.ApiDataModelItem
import com.example.basicsample.models.api.ApiHelper
import com.example.basicsample.models.api.ApiPagingSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
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

    fun getDataUsingPagination() = Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                ApiPagingSource(service = apiService)
            }
        ).liveData

    /*fun getData() : Flow<PagingData<ApiDataModelItem>>{
        getDataUsingPagination().map {pagingData ->
            pagingData.map {
                 it
            }

        }.cachedIn(viewModelScope)
    }*/
}