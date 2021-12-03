package day03

import readInput
import java.util.*

fun getOxygen(input: List<String>, index: Int): Int {
    if (input.size == 1) {
        return Integer.valueOf(input[0], 2)
    }
    return getOxygen(filterMost(input, index), index + 1);
}

fun getScrubber(input: List<String>, index: Int): Int {
    if (input.size == 1) {
        return Integer.valueOf(input[0], 2)
    }
    return getScrubber(filterLeast(input, index), index + 1);
}

fun filterMost(input: List<String>, index: Int): List<String> {
    var numOnes = 0
    var half = input.size / 2
    if (input.size % 2 != 0) {
        half += 1;
    }

    for (item in input) {
        numOnes += Character.getNumericValue(item[index]);
    }

    val ret = mutableListOf<String>()
    return if (numOnes >= half) {
        for (item in input) {
            if (item[index] == '1') {
                ret.add(item)
            }
        }
        ret
    } else {
        for (item in input) {
            if (item[index] == '0') {
                ret.add(item)
            }
        }
        ret
    }
}

fun filterLeast(input: List<String>, index: Int): List<String> {
    var numOnes = 0
    var half = input.size / 2
    if (input.size % 2 != 0) {
        half += 1;
    }

    for (item in input) {
        numOnes += Character.getNumericValue(item[index]);
    }

    val ret = mutableListOf<String>()
    return if (numOnes >= half) {
        for (item in input) {
            if (item[index] == '0') {
                ret.add(item)
            }
        }
        ret
    } else {
        for (item in input) {
            if (item[index] == '1') {
                ret.add(item)
            }
        }
        ret
    }
}

fun main() {
    fun part1(input: List<String>): Int {
        val ones = IntArray(input[0].length)
        for (item in input) {
            for (i in item.indices) {
                ones[i] += Character.getNumericValue(item[i]);
            }
        }

        val zeros = IntArray(ones.size)
        for (i in ones.indices) {
            zeros[i] = input.size - ones[i]
        }

        val gamma = StringBuilder()
        val epsilon = StringBuilder()
        for (i in ones.indices) {
            if (ones[i] > zeros[i]) {
                gamma.append("1")
                epsilon.append("0")
            } else {
                epsilon.append("1")
                gamma.append("0")
            }
        }
        return Integer.valueOf(gamma.toString(), 2) * Integer.valueOf(epsilon.toString(), 2)
    }

    fun part2(input: List<String>): Int {
        return getOxygen(input, 0) * getScrubber(input, 0);
    }

    val testInput = readInput("day03_test")
    println(part1(testInput))
    println(part2(testInput))

    val input = readInput("day03")
    println(part1(input))
    println(part2(input))
}