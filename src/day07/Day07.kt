package day07

import asInts
import readInput

fun main() {

    fun part1(input: List<String>): Int {
        val crabsPos = input[0].split(',').asInts();

        println(crabsPos)
        var min = Integer.MAX_VALUE
        var max = Integer.MIN_VALUE
        for (crab in crabsPos) {
            if (crab < min) {
                min = crab
            }
            if (crab > max) {
                max = crab
            }
        }

        var minDistance = Integer.MAX_VALUE
        for (i in min..max) {
            var distance = 0
            for (crab in crabsPos) {
                distance += Math.abs(crab-i)
            }
            if (distance < minDistance) {
                minDistance = distance
            }
        }
        return minDistance
    }

    fun part2(input: List<String>): Int {
        val crabsPos = input[0].split(',').asInts();

        println(crabsPos)
        var min = Integer.MAX_VALUE
        var max = Integer.MIN_VALUE
        for (crab in crabsPos) {
            if (crab < min) {
                min = crab
            }
            if (crab > max) {
                max = crab
            }
        }

        var minDistance = Integer.MAX_VALUE
        for (i in min..max) {
            var distance = 0
            for (crab in crabsPos) {
                for (j in 1..Math.abs(crab-i)) {
                    distance += j
                }
            }
            if (distance < minDistance) {
                minDistance = distance
            }
        }
        return minDistance
    }

    val testInput = readInput("day07_test")
    println(part1(testInput))
    println(part2(testInput))

    val input = readInput("day07")
    println(part1(input))
    println(part2(input))
}