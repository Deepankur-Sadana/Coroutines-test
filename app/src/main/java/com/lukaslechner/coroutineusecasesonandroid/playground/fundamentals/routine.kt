package com.lukaslechner.coroutineusecasesonandroid.playground.fundamentals



fun main() {
    println("main function start..........")
    routine(1, 500)
    routine(2, 300)
    println("main function ends..........")
}

fun routine(number : Int, delay: Long) {
    println("Routine number $number starts")
    Thread.sleep(delay)
    println("Routine number $number finished")

}