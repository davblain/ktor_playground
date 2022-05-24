package com.example.ktor.feature.prices_list.feature.utils

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import family.amma.keemun.Connector
import family.amma.keemun.StateTransform
import family.amma.keemun.feature.Feature
import family.amma.keemun.feature.FeatureParams
import family.amma.keemun.teaFeature

internal inline fun <reified State : Parcelable, Msg : Any, ViewState : Any, Effect : Any> createFeature(
    savedStateHandle: SavedStateHandle,
    crossinline featureParams: () -> FeatureParams<State, Msg, Effect>,
    noinline getStateTransform: () -> StateTransform<State, ViewState>
): Feature<ViewState, Msg> {
    return Connector(
        savedStateHandle = savedStateHandle,
        createFeature = { coroutineScope, state -> teaFeature(coroutineScope, state, featureParams()) },
        stateTransform = getStateTransform()
    )
}
