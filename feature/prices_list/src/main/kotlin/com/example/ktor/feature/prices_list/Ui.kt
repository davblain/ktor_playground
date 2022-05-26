package com.example.ktor.feature.prices_list

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ktor.feature.prices_list.feature.*
import com.example.ktor.feature.prices_list.feature.PriceItem
import com.example.ktor.feature.prices_list.feature.PricesExternalMsg
import com.example.ktor.feature.prices_list.feature.PricesFeature
import com.example.ktor.feature.prices_list.feature.PricesViewState


@Preview
@Composable
internal fun PricesListUiPreview() {
    Surface {
        PricesListUi(
            pricesViewState = PricesViewState(
                listOf(
                    PriceItem(id = "asdasd", name = "BTC", priceValue = "50000.0"),
                    PriceItem(id = "asssdaa", name = "ETF", priceValue = "2132")
                )
            ),
            onClickItem = {}
        )
    }
}

@Composable
internal fun PricesListUi(pricesViewState: PricesViewState, onClickItem: (PriceItem) -> Unit) {
    Column(Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.height(8.dp))
        PriceListHeader()
        LazyColumn {
            itemsIndexed(pricesViewState.prices, { _, priceItem -> priceItem.id }) { index, priceItem ->
                Row(
                    Modifier
                        .background(if (index % 2 == 0) Color.LightGray else MaterialTheme.colors.background)
                        .fillParentMaxWidth()
                        .clickable { onClickItem(priceItem) }
                        .padding(vertical = 16.dp)

                ) {
                    Row(Modifier.width(150.dp)) {
                        Spacer(Modifier.width(8.dp))
                        Text(text = index.inc().toString(), modifier = Modifier.width(32.dp))
                        Text(text = priceItem.name)
                    }
                    Text(text = priceItem.priceValue)
                }
            }
        }
    }
}

@Composable
private fun PriceListHeader() {
    Row(
        Modifier
            .fillMaxWidth()
            .border(width = 2.dp, color = Color.Gray)
    ) {
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "Name",
            modifier = Modifier.width(150.dp)
        )
        Text(
            text = "Price",
            modifier = Modifier.width(120.dp)
        )
    }
}

@Composable
internal fun PricesListUi(feature: PricesFeature, onClickItem: (PriceItem) -> Unit) {
    val state by feature.states.collectAsState(initial = null)
    ObservePriceEffect(feature)
    state?.let { PricesListUi(it, onClickItem) }
}

@Composable
internal fun ObservePriceEffect(feature: PricesFeature) {
    DisposableEffect(key1 = Unit) {
        feature.dispatch(PricesExternalMsg.StartObservePrices)
        onDispose { feature.dispatch(PricesExternalMsg.StopObservePrices) }
    }
}
