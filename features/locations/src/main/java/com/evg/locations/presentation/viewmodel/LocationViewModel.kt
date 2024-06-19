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

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    //var selectedType by mutableStateOf<String?>(null)

    fun getLocationInfo(id: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            locationUseCases.getLocationById(id = id)
                .collect { location ->
                    _locationInfo.value = location
                    location?.let {
                        getCharacterEpisodes(it.residents)
                    }
                    _isLoading.value = false
                }
        }
    }

    private fun getCharacterEpisodes(episodeUrls: List<String>) {
        viewModelScope.launch {
            locationUseCases.getCharactersList(episodeUrls)
                .collect { characters ->
                    _locationCharacters.value = characters
                }
        }
    }

    fun setNameFilter(name: String) {
        filter.value = filter.value.copy(name = name)
    }

    fun setLocationType(status: String?) {
        filter.value = filter.value.copy(type = status)
        //selectedType = filter.value.type
    }
}