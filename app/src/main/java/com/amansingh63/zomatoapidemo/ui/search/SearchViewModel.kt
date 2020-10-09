package com.amansingh63.zomatoapidemo.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.amansingh63.zomatoapidemo.data.AppRepository
import com.amansingh63.zomatoapidemo.models.ApiResult
import com.amansingh63.zomatoapidemo.models.ListHeader
import com.amansingh63.zomatoapidemo.models.search.Restaurant
import com.amansingh63.zomatoapidemo.models.search.Restaurants
import com.amansingh63.zomatoapidemo.ui.BaseViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val appRepository: AppRepository
) : BaseViewModel() {

    private val _restrauntsByCuisines = MutableLiveData<List<Any>>().apply { value = emptyList() }
    val restrauntsByCuisines: LiveData<List<Any>> = _restrauntsByCuisines

    fun getSearchResults(
        query: String = "",
        fromApi: Boolean = false,
        firstCall: Boolean = false
    ) {

        viewModelScope.launch {
            _dataLoading.value = true

            val searchResults = appRepository.getResturants(query, fromApi)

            if (searchResults is ApiResult.Error) {
                _dataLoading.value = false
                if (searchResults.apiError.code == 1000) {
                    getSearchResults(query, false)
                    showSnackbarMessage(searchResults.apiError.message + " Showing local results")
                } else {
                    showSnackbarMessage(searchResults.apiError.message)
                }
            } else if (searchResults is ApiResult.Success) {
                _dataLoading.value = false

                if (searchResults.data.results_found == 0) {
                    if (!firstCall) {
                        _noDataFound.value = true
                        _restrauntsByCuisines.value = emptyList()
                        showSnackbarMessage("No result found")
                    }
                } else {
                    _noDataFound.value = false
                    _restrauntsByCuisines.value =
                        parseRestrauntByCuisines(searchResults.data.restaurants)

                    if (fromApi) {
                        appRepository.saveRestaurantsDb(searchResults.data)
                    }
                }
            }
        }
    }

    fun markUnmarkFavorite(restaurants: Restaurants, favorite: Boolean) {
        if (favorite) {
            showSnackbarMessage("Restaurant marked as Favorite")
        } else {
            showSnackbarMessage("Restaurant removed from Favorite")
        }

        val newList = _restrauntsByCuisines.value
        (newList?.get(newList.indexOf(restaurants)) as Restaurants).restaurant.isFavourite = true
        _restrauntsByCuisines.value = ArrayList(newList)

        viewModelScope.launch {
            appRepository.markUnmarkFavorite(restaurants, favorite)
        }
    }

    private fun parseRestrauntByCuisines(restaurantsList: List<Restaurants>): List<Any> {
        val restaurantsListWithHeader = mutableListOf<Any>()
        val distinctCuisines = restaurantsList.map { it.restaurant.cuisines }

        Timber.d("Unique cuisines found (${distinctCuisines.size}) : $distinctCuisines")

        for (cuisine in distinctCuisines) {
            restaurantsListWithHeader.add(ListHeader(cuisine))
            restaurantsListWithHeader.addAll(restaurantsList.filter { it.restaurant.cuisines == cuisine })
        }
        return restaurantsListWithHeader
    }

    fun clearResults() {
        _restrauntsByCuisines.value = emptyList()
    }


    fun onSearchResultClicked(restaurant: Restaurant) {

    }


}

