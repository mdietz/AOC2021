package day09

import readInput

fun main() {

    fun getCell(cave: List<List<Int>>, i: Int, j: Int): Int {
        if (i < 0 || i > cave.lastIndex || j < 0 || j > cave.first().lastIndex) {
            return Int.MAX_VALUE
        }
        return cave[i][j]
    }

    fun checkLowest(cave: List<List<Int>>, i: Int, j: Int): Boolean {
        val curr = cave[i][j]
        if (getCell(cave, i + 1, j) > curr
            && getCell(cave, i - 1, j) > curr
            && getCell(cave, i, j + 1) > curr
            && getCell(cave, i, j - 1) > curr) {
            return true
        }
        return false
    }

    fun flowsToBasin(cave: List<List<Int>>, i: Int, j: Int, k: Int, l: Int): Boolean {
        if(i == k && j == l) {
            return true
        }
        val curr = cave[i][j]
        if (curr == 9) {
            return false
        }
        var left = false
        var right = false
        var up = false
        var down = false
        if (i+1 <= cave.lastIndex && cave[i+1][j] < curr) {
            right = flowsToBasin(cave, i+1, j, k, l)
        }
        if (i-1 >= 0 && cave[i-1][j] < curr) {
            left = flowsToBasin(cave, i-1, j, k, l)
        }
        if (j+1 <= cave[0].lastIndex && cave[i][j+1] < curr) {
            up = flowsToBasin(cave, i, j+1, k, l)
        }
        if (j-1 >= 0 && cave[i][j-1] < curr) {
            down = flowsToBasin(cave, i, j-1, k, l)
        }
        return left || right || up || down
    }

    fun getBasin(cave: List<List<Int>>, i: Int, j: Int): Set<Pair<Int,Int>> {
        var basin = mutableSetOf<Pair<Int,Int>>()
        for ((k, row) in cave.withIndex()) {
            for ((l, elem) in row.withIndex()) {
                if (flowsToBasin(cave, k, l, i, j)) {
                    basin.add(Pair(k,l))
                }
            }
        }
        return basin
    }

    fun part1(input: List<String>): Int {
        var cave = mutableListOf<List<Int>>()

        for ((i, row) in input.withIndex()) {
            var elems = mutableListOf<Int>()
            for ((j, c) in row.withIndex()) {
                elems.add(Character.getNumericValue(c))
            }
            cave.add(elems)
        }

        var risk = 0
        for ((i, row) in cave.withIndex()) {
            for ((j, elem) in row.withIndex()) {
                if (checkLowest(cave, i, j)) {
                    risk += 1 + cave[i][j]
                }
            }
        }

        return risk;
    }

    fun part2(input: List<String>): Int {
        var cave = mutableListOf<MutableList<Int>>()

        for ((i, row) in input.withIndex()) {
            var elems = mutableListOf<Int>()
            for ((j, c) in row.withIndex()) {
                elems.add(Character.getNumericValue(c))
            }
            cave.add(elems)
        }

        var basins = mutableListOf<Int>()
        for ((i, row) in cave.withIndex()) {
            for ((j, elem) in row.withIndex()) {
                if (checkLowest(cave, i, j)) {
                    basins.add(getBasin(cave, i, j).size)
                }
            }
        }

        var tot = 1
        for (elem in basins.sorted().reversed().slice(0..2)) {
            tot *= elem
        }
        return tot;
    }

    val testInput = readInput("day09_test")
    println(part1(testInput))
    println(part2(testInput))

    val input = readInput("day09")
    println(part1(input))
    println(part2(input))
}