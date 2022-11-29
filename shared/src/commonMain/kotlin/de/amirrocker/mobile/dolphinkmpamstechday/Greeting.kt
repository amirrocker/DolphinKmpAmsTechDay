package de.amirrocker.mobile.dolphinkmpamstechday

import de.amirrocker.mobile.dolphinkmpamstechday.model.Contract
import de.amirrocker.mobile.dolphinkmpamstechday.model.Contracts
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone.Companion.currentSystemDefault
import kotlinx.datetime.todayIn
import kotlinx.serialization.json.Json

const val URL = "https://run.mocky.io/v3/6b60d3d9-d566-4909-b7fe-ae89d39dec49"

class Greeting {

    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(
                Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                }
            )
        }
    }

    @Throws(Exception::class)
    suspend fun greeting(): String {
        val contracts: Contracts =
            httpClient
                .get(URL)
                .body()
        val contractsWithName: List<Contract> = contracts.contracts.filter {
            it.surname.isNotEmpty()
        }
        return "Hi ${getPlatform().name} user, you have contracts size: ${contracts.contracts.size} with contracts with name size: ${contractsWithName.size}"
    }

    fun greetingLocal(): String {
        val today = Clock.System.todayIn(currentSystemDefault())
        val daysLeft = daysUntilNewYearAsExpression()
        val newYear = newYear()
        return "Hello, today is $today, and there are only $daysLeft days until the new year $newYear, ${getPlatform().name}!"
    }
}
