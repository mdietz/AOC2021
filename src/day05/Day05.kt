package day05

import asInts
import readInput
import java.util.*
import kotlin.math.max

data class Point(val x: Int, val y: Int) {
    constructor(pair: Pair<Int, Int>) : this(pair.first, pair.second)
}

fun main() {

    fun parseInput(input: List<String>): List<Pair<Point,Point>> {
        var ret = mutableListOf<Pair<Point, Point>>()

        for (item in input) {
            val matched = "([0-9]+,[0-9]+)\\s+->\\s+([0-9]+,[0-9]+)".toRegex().find(item)
            val (first, second) = matched!!.destructured
            first.split(',').zipWithNext().single()
            ret.add(Pair(Point(first.split(',').asInts().zipWithNext().single()), Point(second.split(',').asInts().zipWithNext().single())))
        }
        return ret
    }

    fun getSize(input: List<Pair<Point,Point>>): Int {
        var maxX = 0
        var maxY = 0
        for (s in input) {
            for (t in s.toList()) {
                if (t.x > maxX) {
                    maxX = t.x
                }
                if (t.y > maxY) {
                    maxY = t.y
                }
            }
        }
        return max(maxX+1, maxY+1)
    }

    fun part1(input: List<String>): Int {
        val lines = parseInput(input)
        val size = getSize(lines)

        var map = Array(size) { IntArray(size) }

        for (line in lines) {
            if (line.first.x == line.second.x) {
                val x = line.first.x
                if (line.first.y < line.second.y) {
                    for (y in line.first.y..line.second.y) {
                        map[x][y] += 1
                    }
                } else {
                    for (y in line.second.y..line.first.y) {
                        map[x][y] += 1
                    }
                }
            }
            if (line.first.y == line.second.y) {
                val y = line.first.y
                if (line.first.x < line.second.x) {
                    for (x in line.first.x..line.second.x) {
                        map[x][y] += 1
                    }
                } else {
                    for (x in line.second.x..line.first.x) {
                        map[x][y] += 1
                    }
                }
            }
        }

        var danger = 0
        for (y in 0..map.lastIndex) {
            for (x in 0 .. map[y].lastIndex) {
                if (map[x][y] > 1) {
                    danger++
                }
            }
        }

        return danger
    }

    fun part2(input: List<String>): Int {
        val lines = parseInput(input)
        val size = getSize(lines)

        var map = Array(size) { IntArray(size) }

        for (line in lines) {
            if (line.first.x == line.second.x) {
                val x = line.first.x
                if (line.first.y < line.second.y) {
                    for (y in line.first.y..line.second.y) {
                        map[x][y] += 1
                    }
                } else {
                    for (y in line.second.y..line.first.y) {
                        map[x][y] += 1
                    }
                }
            } else if (line.first.y == line.second.y) {
                val y = line.first.y
                if (line.first.x < line.second.x) {
                    for (x in line.first.x..line.second.x) {
                        map[x][y] += 1
                    }
                } else {
                    for (x in line.second.x..line.first.x) {
                        map[x][y] += 1
                    }
                }
            } else {
                var xRange: Iterable<Int> = line.first.x..line.second.x
                if (line.first.x > line.second.x) {
                    xRange = (line.first.x downTo line.second.x)
                }
                var yRange: Iterable<Int> = line.first.y..line.second.y
                if (line.first.y > line.second.y) {
                    yRange = line.first.y downTo line.second.y
                }
                for ((x,y) in xRange.zip(yRange)) {
                    map[x][y] += 1
                }
            }
        }

        var danger = 0
        for (y in 0..map.lastIndex) {
            for (x in 0 .. map[y].lastIndex) {
                if (map[x][y] > 1) {
                    danger++
                }
            }
        }

        return danger
    }

    val testInput = readInput("day05_test")
    println(part1(testInput))
    println(part2(testInput))

    val input = readInput("day05")
    println(part1(input))
    println(part2(input))
}