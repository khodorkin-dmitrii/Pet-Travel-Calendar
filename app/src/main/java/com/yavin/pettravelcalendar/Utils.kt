package com.yavin.pettravelcalendar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.maxkeppeker.sheets.core.models.base.rememberSheetState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import java.time.LocalDate
import java.time.temporal.ChronoUnit

//Script "First Vaccination" for Cats and Dogs
//- Day 0 - Microchip and rabies vaccine, and a comprehensive vaccine (1 injection)
//- 21 days (from day 0) - Comprehensive vaccine (2nd injection)
//- 30 days (from day 0) - Blood titers can be tested
//- 21 days (from day 22) - Eligible for travel from Russia

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FirstVaccineCase() {
    val calendarAState = rememberSheetState()
    var selectedDateA by remember {
        mutableStateOf(LocalDate.now())
    }
    CalendarDialog(
        state = calendarAState,
        selection = CalendarSelection.Date(selectedDate = selectedDateA) { date: LocalDate ->
            selectedDateA = date
        },
    )

    Column(Modifier.padding(16.dp)) {
        Text(
            text = "Сценарий: 'Первая вакцинация'", // TODO localize
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = "День 0 - чип и вакцина от бешенства и комплексная (1 укол)",
            style = MaterialTheme.typography.labelLarge
        )
        Button(onClick = { calendarAState.show() }) {
            Text("День 0: $selectedDateA")
        }
        Text(
            text = "Тогда:",
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = "${
                ChronoUnit.DAYS.addTo(
                    selectedDateA,
                    21
                )
            } :  21 день (от дня 0) - вакцина комплексная (2 укол);",
            style = MaterialTheme.typography.labelLarge
        )
        Text(
            text = "${
                ChronoUnit.DAYS.addTo(
                    selectedDateA,
                    30
                )
            } :  30 дней (от дня 0) - можно сдавать кровь на титры;",
            style = MaterialTheme.typography.labelLarge
        )
        Text(
            text = "${
                ChronoUnit.DAYS.addTo(
                    selectedDateA,
                    43
                )
            } :  21 день (от дня 22) - можно ехать из РФ (+43?).",
            style = MaterialTheme.typography.labelLarge
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleDatePickerCompare() {

    val calendarAState = rememberSheetState()
    val calendarBState = rememberSheetState()

    var selectedDateA by remember {
        mutableStateOf(LocalDate.now())
    }
    var selectedDateB by remember {
        mutableStateOf(LocalDate.now())
    }

    CalendarDialog(
        state = calendarAState,
        selection = CalendarSelection.Date(selectedDate = selectedDateA) { date: LocalDate ->
            selectedDateA = date
        },
    )

    CalendarDialog(
        state = calendarBState,
        selection = CalendarSelection.Date(selectedDate = selectedDateB) { date: LocalDate ->
            selectedDateB = date
        })

    Column(Modifier.padding(16.dp)) {
        Button(onClick = { calendarAState.show() }) {
            Text("A: $selectedDateA")
        }
        Button(onClick = { calendarBState.show() }) {
            Text("B: $selectedDateB")
        }
        Text("Compare: ${calculateDateDifference(selectedDateA, selectedDateB)}")

    }
}

fun calculateDateDifference(dateA: LocalDate, dateB: LocalDate): Long {
    return ChronoUnit.DAYS.between(dateA, dateB)
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun FirstVaccineCasePreview() {
    FirstVaccineCase()
}