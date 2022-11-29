package de.amirrocker.mobile.dolphinkmpamstechday.model

import kotlinx.serialization.Serializable

@Serializable
data class Contract(
    val id: Int,
    val business: Boolean,
    val city: String,
    val country_code: String,
    val firstname: String,
    val house_number: String,
    val is_confirmed: String,
    val salutation: String,
    val state: String,
    val street: String,
    val surname: String,
    val title: String,
    val zip_code: String,
    val mobile: String,
    val email: String
)
