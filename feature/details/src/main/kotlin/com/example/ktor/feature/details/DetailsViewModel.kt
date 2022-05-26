package com.example.ktor.feature.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ktor.core.std.Either
import com.example.ktor.core.std.fold
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val cryptoId: String,
    private val detailsProvider: suspend (id: String) -> Either<DetailsLoadingFailure, DetailsEntity>
) : ViewModel() {
    private val stateFlow = MutableStateFlow<DetailsState>(DetailsState.Loading)
    val state: StateFlow<DetailsState> = stateFlow.asStateFlow()

    init {
        viewModelScope.launch {
            stateFlow.value = detailsProvider(cryptoId).fold(
                ifLeft = {
                    DetailsState.Error(it, null)
                },
                ifRight = { DetailsState.Loaded(it) }
            )
        }
    }

    fun refreshInfo() {
        val currentState = stateFlow.value
        if (currentState != DetailsState.Loading) {
            viewModelScope.launch {
                detailsProvider(cryptoId).fold(
                    ifLeft = { DetailsState.Error(it, currentState.previousLoadedDetails()) },
                    ifRight = { DetailsState.Loaded(it) }
                )
            }
        }
    }

    private fun DetailsState.previousLoadedDetails(): DetailsEntity? {
        return when (this) {
            is DetailsState.Error -> previousDetails
            is DetailsState.Loaded -> details
            DetailsState.Loading -> null
        }
    }
}

sealed class DetailsLoadingFailure {
    object NoInternet : DetailsLoadingFailure()
    object Other : DetailsLoadingFailure()
}

data class DetailsEntity(
    val overview: String
)


sealed class DetailsState {
    object Loading : DetailsState()
    data class Loaded(val details: DetailsEntity) : DetailsState()
    data class Error(val failure: DetailsLoadingFailure, val previousDetails: DetailsEntity?) : DetailsState()
}
