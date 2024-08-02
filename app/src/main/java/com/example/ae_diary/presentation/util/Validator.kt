package com.example.ae_diary.presentation.util

object Validator {

    fun validateNameSurname(text: String) = text.matches("^[А-Яа-яA-Za-zёїЇЄє]+([-\\s][А-Яа-яA-Za-zёїЇЄє]+)*$".toRegex())

    fun validatePatronymic(text: String) = text.matches("^[А-Яа-яA-Za-zёїЇЄє]*([-\\s][А-Яа-яA-Za-zёїЇЄє]+)*$".toRegex())

    fun validateSubjectName(text: String) = text.matches("^[А-Яа-яA-Za-zёїЇЄє]+([-\\s][А-Яа-яA-Za-zёїЇЄє]+)*$".toRegex())

}