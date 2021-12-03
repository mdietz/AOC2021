package day02

import readInput

fun main() {
    fun part1(input: List<String>): Int {
        var pos = 0;
        var depth = 0;
        for (item in input) {
            val matched = "([a-z]+)\\s+([0-9]+)".toRegex().find(item)
            val (dir, cnt) = matched!!.destructured
            val cntInt = cnt.toInt()
            if (dir == "forward") {
                pos += cntInt
            } else if (dir == "up") {
                depth -= cntInt
            } else if (dir == "down") {
                depth += cntInt
            }
        }
        return pos*depth;
    }

    fun part2(input: List<String>): Int {
        var pos = 0;
        var depth = 0;
        var aim = 0;
        for (item in input) {
            val matched = "([a-z]+)\\s+([0-9]+)".toRegex().find(item)
            val (dir, cnt) = matched!!.destructured
            val cntInt = cnt.toInt()
            if (dir == "forward") {
                pos += cntInt
                depth += cntInt*aim;
            } else if (dir == "up") {
                aim -= cntInt
            } else if (dir == "down") {
                aim += cntInt
            }
        }
        return pos*depth;
    }

    val testInput = readInput("day02_test")
    println(part1(testInput))
    println(part2(testInput))

    val input = readInput("day02")
    println(part1(input))
    println(part2(input))
}