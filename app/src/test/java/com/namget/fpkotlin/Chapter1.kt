package com.namget.fpkotlin

import org.junit.Test
import java.lang.IllegalArgumentException


class Chapter1 {
    var z = 10

    fun inpureFunctionWithSideEffect(x: Int, y: Int): Int {
        z = y
        return x + y
    }

    /*@Test
    fun main() {
        println(impureFunction(1, 2)) // 13
        z = 20
        println(impureFunction(1, 2)) // 23
    }*/


    @Test
    fun main() {
        println(impureFunction(1, 2)) // 13
        println(withSideEffect(10, 20)) // 30 출력
        println(impureFunction(1, 2)) // 23
    }

    fun impureFunction(x: Int, y: Int): Int = x + y + z
    fun withSideEffect(x: Int, y: Int): Int {
        z = y
        return x + y
    }

    data class MutablePerson(var name: String, var age: Int)
    data class ImmutablePerson(val name: String, val age: Int)

    var someName: String = "Joe"
    fun hello1() {
        println("hello $someName")
    }

    fun hello2(name: String) {
        println("hello $name")
    }

    @Test
    fun calculator() {
        val fpCalculator = FpCalculator()
        println(fpCalculator.calculate({ n1, n2 -> n1 + n2 }, 3, 1))
        println(fpCalculator.calculate({ n1, n2 -> n1 - n2 }, 3, 1))
    }

    class FpCalculator {
        fun calculate(calculator: (Int, Int) -> Int, num1: Int, num2: Int): Int {
            if (num1 > num2 && 0 != num2) {
                return calculator(num1, num2)
            } else {
                throw IllegalArgumentException()
            }
        }
    }

    val lazyValue: String by lazy {
        println("지연 초기화")
        "hello"
    }

    val infiniteValue = generateSequence(0) { it + 5 }
    fun test(){
        infiniteValue.take(5).forEach { print(it) }
    }

}