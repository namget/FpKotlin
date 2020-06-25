package com.namget.fpkotlin

import org.junit.Test
import kotlin.math.cos
import kotlin.math.max

class Chapter3 {

    private fun fiboDynamic(n: Int, fibo: IntArray): Int {
        fibo[0] = 0
        fibo[1] = 1
        for (i in 2..n) {
            fibo[i] = fibo[i - 1] + fibo[i - 2]
        }
        return fibo[n]
    }


    fun helloFunc() {
        println("hello")
        helloFunc()
    }

    fun helloFunc(n: Int) {
        when {
            n < 0 -> return
            else -> {
                println("hello")
                helloFunc(n - 1)
            }
        }
    }


    fun func(n: Int): Int = when {
        n < 0 -> 0
        else -> n + func(n - 1)
    }


    fun practice1(x: Double, n: Int): Double {
        return when {
            n < 0 -> {
                (1 / x) * practice1(x, n + 1)
            }
            n == 0 -> {
                1.0
            }
            else -> {
                x * practice1(x, n - 1)
            }
        }
    }

    fun practice2(n: Int): Int {
        if (n < 0) {
            return 0
        } else if (n == 0) {
            return 1
        }
        return n * practice2(n - 1)
    }

    fun List<Int>.head() = first()
    fun List<Int>.tail() = drop(1)

    fun maximum(items: List<Int>): Int = when {
        items.isEmpty() -> error("")
        1 == items.size -> items[0]
        else -> {
            val head = items.head()
            val tail = items.tail()
            val maxVal = maximum(tail)
            if (head > maxVal) head else maxVal
        }
    }

    fun String.head() = first()
    fun String.tail() = drop(1)

    fun reverse(str: String): String = when {
        str.isEmpty() -> ""
        else -> reverse(str.tail()) + str.head()
    }


    @Test
    fun a() {
        println(practice4(5, 10))
    }


    fun practice3(n: Int): String {
        if (n <= 0) {
            return ""
        }

        if (n / 2 <= 0 && n % 2 == 1) {
            return "1"
        }
        return "${practice3(n / 2)}${(n % 2)}"
    }

    fun practice4(n: Int, element: Int): List<Int> {

        return if (n <= 0) {
            emptyList()
        } else {
            listOf(element) + practice4(n - 1, element)
        }
    }

    fun take(n: Int, list: List<Int>): List<Int> = when {
        n <= 0 -> list
        list.isEmpty() -> listOf()
        else -> listOf(list.head()) + take(n - 1, list.tail())
    }

    fun practice5(num: Int, list: List<Int>): Boolean = when {
        list.isEmpty() -> {
            false
        }
        else -> {
            (num == list.drop(1).first()) || practice5(num, list)
        }
    }

    //    fun repeat(n: Int): Sequence<Int> = generateSequence(n) { it }
//    fun repeat(n: Int): Sequence<Int> = sequenceOf(n) + repeat(n)

    operator fun <T> Sequence<T>.plus(other: () -> Sequence<T>) = object : Sequence<T> {
        private val thisIterator: Iterator<T> by lazy { this@plus.iterator() }
        private val otherIterator: Iterator<T> by lazy { other().iterator() }
        override fun iterator(): Iterator<T> = object : Iterator<T> {
            override fun next(): T =
                if (thisIterator.hasNext())
                    thisIterator.next()
                else
                    otherIterator.next()

            override fun hasNext(): Boolean = thisIterator.hasNext() || otherIterator.hasNext()
        }
    }

    fun repeat(n: Int): Sequence<Int> = sequenceOf(n) + { repeat(n) }

    fun zip(list1: List<Int>, list2: List<Int>): List<Pair<Int, Int>> = when {
        list1.isEmpty() || list2.isEmpty() -> listOf()
        else -> listOf(Pair(list1.head(), list2.head())) + zip(list1.tail(), list2.tail())
    }

    var memo = Array(100, { -1 })

    fun fiboMemoization(n: Int): Int = when {
        n == 0 -> 0
        n == 1 -> 1
        memo[n] != -1 -> memo[n]
        else -> {
            println("fiboMemoization($n")
            memo[n] = fiboMemoization(n - 2) + fiboMemoization(n - 1)
            memo[n]
        }
    }

    fun practice3_10() {

    }

    fun fiboFP(n: Int): Int = fiboFP(n, 0, 1)
    tailrec fun fiboFP(n: Int, first: Int, second: Int): Int = when (n) {
        0 -> first
        1 -> second
        else -> fiboFP(n - 1, second, first + second)
    }

    tailrec fun findFixPoint(x: Double = 1.0): Double {
        return if (x == cos(x)) x else findFixPoint(cos(x))
    }

    fun findFixPoint(): Double {
        var x = 1.0
        while (true) {
            val y = cos(x)
            if (x == y) return x
            x = y
        }
    }

    tailrec fun tailrecMaximun(items: List<Int>, acc: Int = Int.MIN_VALUE): Int = when {
        items.isEmpty() -> error("")
        items.size == 1 -> {
            println("")
            acc
        }
        else -> {
            val head = items.head()
            val maxVal = if (head > acc) head else acc
            tailrecMaximun(items.tail(), maxVal)
        }
    }

    tailrec fun reverse(str: String, acc: String = ""): String = when {
        str.isEmpty() -> acc
        else -> {
            val reversed = str.head() + acc
            reverse(str.tail(), reversed)
        }
    }

    tailrec fun take(n: Int, list: List<Int>, acc: List<Int> = listOf()): List<Int> = when {
        0 >= n -> acc
        list.isEmpty() -> acc
        else -> {
            val takeList = acc + listOf(list.head())
            take(n - 1, takeList)
        }
    }

    tailrec fun zip(
        list1: List<Int>,
        list2: List<Int>,
        acc: List<Pair<Int, Int>> = listOf()
    ): List<Pair<Int, Int>> = when {
        list1.isEmpty() || list2.isEmpty() -> acc
        else -> {
            val zipList = acc + listOf(Pair(list1.head(), list2.head()))
            zip(list1.tail(), list2.tail(), zipList)
        }
    }

//    fun even(n : Int) : Boolean = when(n){
//        0 -> true
//        else -> odd(n-1)
//    }
//    fun odd(n: Int) : Boolean = when(n) {
//        0 -> false
//        else -> even(n-1)
//    }

    sealed class Bounce<A> {
        data class Done<A>(val result: A) : Bounce<A>()
        data class More<A>(val thunk: () -> Bounce<A>) : Bounce<A>()
    }

    tailrec fun <A> trampoline(bounce : Bounce<A>) : A = when(bounce) {
        is Bounce.Done-> bounce.result
        is Bounce.More -> trampoline(bounce.thunk())
    }

    fun odd(n: Int) : Bounce<Boolean> = when(n) {
        0 -> Bounce.Done(false)
        else -> Bounce.More{ even(n-1)}
    }

    fun even(n : Int) : Bounce<Boolean> = when(n){
        0 -> Bounce.Done(true)
        else -> Bounce.More{ odd(n-1)}
    }

    /*fun<T> powerset(s : Set<T>): Set<Set<T>> = when {
        s.isEmpty() -> setOf(setOf())
        else ->{
            setOf(setOf())
        }
    }*/

    fun <T> Set<T>.head() = first()
    fun <T> Set<T>.tail() = drop(1).toSet()
    fun<T> powerset(s : Set<T>): Set<Set<T>> = when {
        s.isEmpty() -> setOf(setOf())
        else ->{
            val head = s.head()
            val restSet = powerset(s.tail())
            restSet + restSet.map { setOf(head) + it }.toSet()
        }
    }

    tailrec fun <T> powerset(s: Set<T>, acc : Set<Set<T>>) : Set<Set<T>> = when{
        s.isEmpty() -> acc
        else -> powerset(s.tail(), acc + acc.map{it + s.head()})
    }



}
