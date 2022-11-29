package de.amirrocker.mobile.dolphinkmpamstechday.model

import kotlinx.serialization.Serializable

@Serializable
data class Contracts(
    val contracts: List<Contract> = listOf()
)
