package com.namget.fpkotlin

import java.io.FileInputStream
import java.util.*

class Chapter2 {
    val value1: Int = 10
    var variable: Int = 10
    var value2 = 10 // 타입추론
    val value3 = 10 // 타입추론
    val value4: Int? = null //널처리

    fun twice(value: Int): Int {
        return value * 2
    }

    fun twice2(value: Int): Int = value * 2
    fun twice3(value: Int) = value * 2

    fun sum(x: Int, y: Int, calculate: (Int, Int) -> Int): Int {
        return calculate(x, y)
    }

    val sum2 = sum(5, 10) { x, y -> x + y }

    fun Int.Product(value: Int): Int {
        return this * value
    }

    fun String.print(str: String) {
        println("hello $str")
    }

    interface foo {
        fun printFoo() {

        }
    }

    interface bar {
        fun printBar()
    }

    interface AbstractProperty {
        val a: Int
    }

    class Kotlin : foo, bar {

        override fun printBar() {
            TODO("Not yet implemented")
        }
    }


    sealed class Expr {
        data class Const(val number: Double) : Expr()
        data class Sum(val number: Double) : Expr()
        object NotANumber : Expr()
    }

    fun eval(expr: Expr): Double = when (expr) {
        is Expr.Const -> {
            0.0
        }
        is Expr.Sum -> {
            0.0
        }
        is Expr.NotANumber -> {
            0.0
        }
    }

    data class User(var name: String, val age: Int = 18)

    fun test33() {
        val user: User = User("kotlin", 28)
        val (name, age) = user
    }


    fun listTest() {
        val user1: User = User("kotlin", 28)
        val user2: User = User("kotlin", 28)
        val user3: User = User("kotlin", 28)
        val users = listOf(user1, user2, user3)
        for ((name, order) in users) {
            println("name : $name , order : $order")
        }
    }

    val map = mapOf<String, Int>("kotlin" to 1)
    fun mapTest() {
        for ((name, order) in map) {
            println("name : $name , order : $order")
        }
    }

    class Box(t: Int) {
        var value = t
    }

    class BoxT<T>(t: T) {
        var value = t
    }

//    fun <T, R> T.let(block: (T) -> R): R
//    fun <T, R> with(receiver : T, block: T.() -> R): R
//    fun <T,R> T.run(block : T.() -> R) : R
//    fun <T> T.apply(block: T.() -> Unit) : T
//    fun <T> T.also(block: (T) -> Unit) : T

    fun useTest(){
        val property = Properties()
        FileInputStream("config.property").use{
            property.load(it)
        }
    }

}