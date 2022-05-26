package com.example.ktor.feature.details

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.ktor.core.messari_api.MessariApi
import com.example.ktor.feature.details.di.detailsModule
import org.koin.androidx.compose.getViewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.parameter.parametersOf

interface DetailsApi {
    fun detailsRoute(id: String): String
    fun registerDetailsScreen(builder: NavGraphBuilder)
}

internal class DetailsApiImpl(deps: DetailsDeps) : DetailsApi {

    init {
        loadKoinModules(detailsModule(deps.messariApi))
    }

    override fun detailsRoute(id: String): String = "$detailsRoute/$id"

    override fun registerDetailsScreen(builder: NavGraphBuilder) {
        builder.composable(
            route = "$detailsRoute/{${idKey}}",
            arguments = listOf(navArgument(idKey) { type = NavType.StringType })
        ) { navBackStackEntry ->
            val id = requireNotNull(navBackStackEntry.arguments?.getString(idKey))
            val viewModel = getViewModel<DetailsViewModel>(parameters = { parametersOf(id) })
            DetailsUi(viewModel = viewModel)
        }
    }

    companion object {
        private const val detailsRoute = "details"
        private const val idKey = "idKey"
    }
}

fun DetailsApi(deps: DetailsDeps): DetailsApi = DetailsApiImpl(deps)

data class DetailsDeps(
    val messariApi: MessariApi
)
