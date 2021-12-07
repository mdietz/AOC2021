package day06

import asInts
import readInput

fun main() {

    fun part1(input: List<String>): Int {
        var fishByDay = ArrayDeque<Int>(9)

        for (i in 0..8) {
            fishByDay.add(0)
        }

        val inputFish = input[0].split(',').asInts();

        for (fish in inputFish) {
            fishByDay[fish] += 1
        }

        for (i in 0..79) {
            val numSpawn = fishByDay.removeFirst()
            fishByDay.addLast(numSpawn)
            fishByDay[6] += numSpawn
        }

        var tot = 0
        for (numFish in fishByDay) {
            tot += numFish
        }

        return tot
    }

    fun part2(input: List<String>): Long {
        var fishByDay = ArrayDeque<Long>(9)

        for (i in 0..8) {
            fishByDay.add(0)
        }

        val inputFish = input[0].split(',').asInts();

        for (fish in inputFish) {
            fishByDay[fish] = fishByDay[fish] + 1
        }

        for (i in 0..255) {
            val numSpawn = fishByDay.removeFirst()
            fishByDay.addLast(numSpawn)
            fishByDay[6] += numSpawn
        }

        var tot : Long = 0
        for (numFish in fishByDay) {
            tot += numFish
        }

        return tot
    }

    val testInput = readInput("day06_test")
    println(part1(testInput))
    println(part2(testInput))

    val input = readInput("day06")
    println(part1(input))
    println(part2(input))
}