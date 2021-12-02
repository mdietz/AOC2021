package day01

import readInput

fun main() {
    fun part1(input: List<String>): Int {
        var last = input.first().toInt();
        var countIncrease = 0;
        for (item in input.slice(1..input.lastIndex)) {
            val itemInt = item.toInt();
            if (itemInt > last) {
                countIncrease++;
            }
            last = itemInt;
        }
        return countIncrease;
    }

    fun part2(input: List<String>): Int {
        var last = input.slice(0..2).sumOf { it.toInt() }
        var countIncrease = 0;
        for (i in 1..input.lastIndex - 2) {
            var curr = input.slice(i..i + 2).sumOf { it.toInt() }
            if (curr > last) {
                countIncrease++;
            }
            last = curr;
        }
        return countIncrease;
    }

    val testInput = readInput("day01_test")
    println(part1(testInput))
    println(part2(testInput))

    val input = readInput("day01")
    println(part1(input))
    println(part2(input))
}
