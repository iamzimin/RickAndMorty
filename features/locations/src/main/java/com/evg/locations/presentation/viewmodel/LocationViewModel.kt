package com.evg.locations.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.evg.locations.domain.model.Character
import com.evg.locations.domain.model.Location
import com.evg.locations.domain.model.LocationFilter
import com.evg.locations.domain.usecase.LocationUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class LocationViewModel @Inject constructor(
    private val locationUseCases: LocationUseCases,
) : ViewModel() {
    private val filter = MutableStateFlow(LocationFilter())
    val locations: StateFlow<PagingData<Location>> = filter.flatMapLatest { currentFilter ->
        locationUseCases.getAllLocations.invoke(filter = currentFilter)
    }.cachedIn(viewModelScope).stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())

    private val _locationInfo = MutableStateFlow<Location?>(null)
    val locationInfo: StateFlow<Location?> get() = _locationInfo

    private val _locationCharacters = MutableStateFlow<List<Character>?>(null)
    val locationCharacters: StateFlow<List<Character>?> get() = _locationCharacters

    private val _isInfoLoading = MutableStateFlow(true)
    val isInfoLoading: StateFlow<Boolean> = _isInfoLoading

    private val _isResidentsLoading = MutableStateFlow(true)
    val isResidentsLoading: StateFlow<Boolean> = _isResidentsLoading

    fun getLocationInfo(id: Int) {
        viewModelScope.launch {
            _isInfoLoading.value = true
            locationUseCases.getLocationById(id = id)
                .collect { location ->
                    _locationInfo.value = location
                    location?.let {
                        getLocationCharacters(it.residents)
                    }
                    _isInfoLoading.value = false
                }
        }
    }

    private fun getLocationCharacters(episodeUrls: List<String>) {
        viewModelScope.launch {
            _isResidentsLoading.value = true
            locationUseCases.getCharactersList(episodeUrls)
                .collect { characters ->
                    _locationCharacters.value = characters
                    _isResidentsLoading.value = false
                }
        }
    }

    fun setNameFilter(name: String) {
        filter.value = filter.value.copy(name = name)
    }

    fun setLocationType(status: String?) {
        filter.value = filter.value.copy(type = status)
    }
}