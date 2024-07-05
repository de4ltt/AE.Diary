package com.example.deathnote.presentation.ui.util

object Validator {

    fun validateNameSurname(text: String) = text.matches("([А-Яа-яA-Za-zёїЇЄє]-?\\s?)+".toRegex())

    fun validatePatronymic(text: String) = text.matches("([А-Яа-яA-Za-zёїЇЄє]-?\\s?)*".toRegex())

    fun validateSubjectName(text: String) = text.matches("([А-Яа-яA-Za-zёїЇЄє]-?\\s?)+".toRegex())
}