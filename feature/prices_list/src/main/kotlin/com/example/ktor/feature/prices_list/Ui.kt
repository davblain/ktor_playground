package com.example.ktor.feature.prices_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ktor.feature.prices_list.feature.PriceItem
import com.example.ktor.feature.prices_list.feature.PricesFeature
import com.example.ktor.feature.prices_list.feature.PricesViewState


@Preview
@Composable
internal fun PricesListUiPreview() {
    PricesListUi(
        PricesViewState(
            listOf(PriceItem(id = "asdasd", name = "BTC", "1110.0"))
        )
    )
}

@Composable
internal fun PricesListUi(priceViewState: PricesViewState) {
    Column {
        Row {
            Text(
                text = "Name",
                modifier = Modifier.width(150.dp)
            )
            Text(
                text = "Price",
                modifier = Modifier.width(100.dp)
            )
        }
        LazyColumn {
            itemsIndexed(priceViewState.prices, { _, priceItem -> priceItem.id }) { index, priceItem ->
                Row {
                    Row(Modifier.width(150.dp)) {
                        Text(text = index.toString(), Modifier.width(32.dp))
                        Text(text = priceItem.name)
                    }
                    Text(text = priceItem.priceValue)
                }
            }
        }
    }
}

@Composable
internal fun PricesListUi(feature: PricesFeature) {
    val state by feature.states.collectAsState(initial = null)
    state?.let { PricesListUi(it) }
}
