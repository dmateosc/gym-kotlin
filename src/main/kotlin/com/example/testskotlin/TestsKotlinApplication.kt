package com.example.testskotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication as runApplication


@SpringBootApplication
class TestsKotlinApplication

fun main(args: Array<String>) {
    val runApplication = runApplication<TestsKotlinApplication>(*args)
    run{
        runApplication

    }

}
