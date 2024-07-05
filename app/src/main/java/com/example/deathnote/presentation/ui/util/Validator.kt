package com.example.deathnote.presentation.ui.util

object Validator {

    fun validateNameSurname(text: String) = text.matches("[А-Яа-яA-Za-zёїЇЄє]+".toRegex())

    fun validatePatronymic(text: String) = text.matches("[А-Яа-яA-Za-zёїЇЄє]*".toRegex())

}