package de.amirrocker.mobile.dolphinkmpamstechday

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone.Companion.currentSystemDefault
import kotlinx.datetime.daysUntil
import kotlinx.datetime.todayIn

fun newYear(): Int = Clock.System.todayIn(currentSystemDefault()).run {
    this.nextYear().year
}

fun daysUntilNewYearAsExpression(): Int =
    Clock.System.todayIn(currentSystemDefault()).run {
        daysUntil(nextYear())
    }

private fun LocalDate.nextYear() = LocalDate(this.year + 1, 1, 1)
