package day08

import readInput

fun main() {

    fun parseInput(input: List<String>): List<Pair<List<String>, List<String>>> {
        var res = mutableListOf<Pair<List<String>, List<String>>>()

        for (item in input) {
            val matched = "([[a-g]+\\s*]+)\\s+\\|\\s+([[a-g]+\\s+]+)".toRegex().find(item)
            res.add(Pair(matched!!.destructured.match.groupValues[1].split(' '), matched!!.destructured.match.groupValues[2].split(' ')))
        }
        return res
    }

    fun part1(input: List<String>): Int {
        val parsed = parseInput(input)
        var tot = 0
        for(row in parsed) {
            for (elem in row.second) {
                when (elem.length) {
                    2,3,4,7 -> tot += 1
                    else -> continue
                }
            }
        }
        return tot
    }

    fun part2(input: List<String>): Int {
        val parsed = parseInput(input)
        var tot = 0
        for(row in parsed) {
            val obs = row.first
            val numMap = buildList {
                add("")
                add(obs.first { it.length == 2 })
                add("")
                add("")
                add(obs.first { it.length == 4 })
                add("")
                add("")
                add(obs.first { it.length == 3 })
                add(obs.first { it.length == 7 })
                add("")
            }.toMutableList()

            var sixes = obs.filter { it.length == 6 }
            var fives = obs.filter { it.length == 5 }

            numMap[6] = sixes.first { !it.toSet().containsAll(numMap[1].toSet()) }
            sixes -= numMap[6]
            val f = numMap[6].toSet().intersect(numMap[1].toSet()).first()
            numMap[2] = fives.first { !it.toSet().contains(f) }
            fives -= numMap[2]
            val b = numMap[6].toSet().subtract(numMap[2].toSet()).minus(f).first()
            numMap[5] = fives.first { it.toSet().contains(b) }
            fives -= numMap[5]
            numMap[3] = fives.first()
            val e = numMap[2].toSet().subtract(numMap[3].toSet()).first()
            numMap[0] = sixes.first { it.toSet().contains(e) }
            sixes -= numMap[0]
            numMap[9] = sixes.first()

            var num = ""
            for (elem in row.second) {
                for (i in numMap.indices) {
                    if (elem.toSet() == numMap[i].toSet()) {
                        num += i.toString()
                    }
                }
            }
            tot += num.toInt()

        }
        return tot
    }

    val testInput = readInput("day08_test")
    println(part1(testInput))
    println(part2(testInput))

    val input = readInput("day08")
    println(part1(input))
    println(part2(input))
}