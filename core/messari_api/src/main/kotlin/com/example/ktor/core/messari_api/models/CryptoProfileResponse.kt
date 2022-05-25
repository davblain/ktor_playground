package com.example.ktor.core.messari_api.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class CryptoProfileResponse(
    @SerialName("data") val data: CryptoProfileData
)

@Serializable
data class CryptoProfileData(
    @SerialName("profile") val profile: CryptoProfile
)

@Serializable
data class CryptoProfile(
    @SerialName("general") val general: GeneralProfileInfo
)

@Serializable
data class GeneralProfileInfo(
    @SerialName("overview") val overview: OverviewProfileInfo,
    @SerialName("background") val background: BackgroundProfileInfo
)

@Serializable
data class BackgroundProfileInfo(
    @SerialName("background_details") val details: String
)

@Serializable
data class OverviewProfileInfo(
    @SerialName("tagline") val tagline: String,
    @SerialName("category") val category: String,
    @SerialName("project_details") val details: String
)
