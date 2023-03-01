package com.djib.football.domain.model

import com.google.gson.annotations.SerializedName



data class CountryResponse(
	@SerializedName("country_name")
	val countryName: String? = null,

	@SerializedName("country_id")
	val countryId: String? = null,

	@SerializedName("country_logo")
	val countryLogo: String? = null
)

