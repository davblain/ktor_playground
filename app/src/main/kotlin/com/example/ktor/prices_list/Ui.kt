package com.example.ktor.prices_list

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.ktor.prices_list.feature.PricesFeature


@Composable
internal fun PricesListUi(feature: PricesFeature) {
    val state by feature.states.collectAsState(initial = null)
    state?.let {
        LazyColumn {
            items(it.prices) {
                Text(text = it.name)
                Text(text = it.price.toString())
            }
        }
    }
}
