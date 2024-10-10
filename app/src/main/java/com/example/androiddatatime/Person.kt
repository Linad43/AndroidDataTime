package com.example.androiddatatime

import java.io.Serializable
import java.time.LocalDate

class Person(
    val firstName: String,
    val secondName: String,
    val dataBirthday: LocalDate,
    val image: String,
) : Serializable {
}