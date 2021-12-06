package day04

import readInput
import asInts
import asBoolPair

fun main() {
    data class Input(var drawn: List<Int>, var boards: List<Array<Array<Pair<Int, Boolean>>>>)

    fun parseInput(input: List<String>): Input {
        val drawn = input[0].split(',').asInts()

        val boards = mutableListOf<Array<Array<Pair<Int, Boolean>>>>()

        for (rows in input.slice(2..input.lastIndex).chunked(6)) {
            boards.add(
                buildList() {
                    add(rows[0].trimStart().split("\\s+".toRegex()).asBoolPair())
                    add(rows[1].trimStart().split("\\s+".toRegex()).asBoolPair())
                    add(rows[2].trimStart().split("\\s+".toRegex()).asBoolPair())
                    add(rows[3].trimStart().split("\\s+".toRegex()).asBoolPair())
                    add(rows[4].trimStart().split("\\s+".toRegex()).asBoolPair())
                }.toTypedArray())
        }

        return Input(drawn, boards)
    }

    fun calculateBoard(board: Array<Array<Pair<Int, Boolean>>>): Int {
        var tot = 0
        for (i in 0..4) {
            for (j in 0..4) {
                if (!board[i][j].second) {
                    tot += board[i][j].first
                }
            }
        }
        return tot
    }

    fun checkWin(boards: List<Array<Array<Pair<Int, Boolean>>>>): Int {
        for (board in boards) {
            for (i in 0..4) {
                var count = 0
                for (j in 0..4) {
                    if (board[j][i].second) {
                        count++
                    }
                }
                if (count == 5) {
                    return calculateBoard(board)
                }
            }
        }
        for (board in boards) {
            for (i in 0..4) {
                var count = 0
                for (j in 0..4) {
                    if (board[i][j].second) {
                        count++
                    }
                }
                if (count == 5) {
                    return calculateBoard(board)
                }
            }
        }
        return -1
    }

    fun removeWinners(boards: List<Array<Array<Pair<Int, Boolean>>>>): List<Array<Array<Pair<Int, Boolean>>>> {
        var ret = mutableListOf<Array<Array<Pair<Int, Boolean>>>>()
        val toRemove = mutableSetOf<Int>()
        for (h in boards.indices) {
            for (i in 0..4) {
                var count = 0
                for (j in 0..4) {
                    if (boards[h][j][i].second) {
                        count++
                    }
                }
                if (count == 5) {
                    toRemove.add(h)
                }
            }
        }
        for (h in boards.indices) {
            for (i in 0..4) {
                var count = 0
                for (j in 0..4) {
                    if (boards[h][i][j].second) {
                        count++
                    }
                }
                if (count == 5) {
                    toRemove.add(h)
                }
            }
        }
        for (h in boards.indices) {
            if (!toRemove.contains(h)) {
                ret.add(boards[h])
            }
        }
        return ret
    }

    fun part1(input: List<String>): Int {
        val (drawn, boards) = parseInput(input)
        for (num in drawn) {
            for (board in boards) {
                for (i in 0..4) {
                    for (j in 0..4) {
                        if (board[i][j].first == num) {
                            board[i][j] = Pair(board[i][j].first, true)
                        }
                    }
                }
            }
            if (checkWin(boards) != -1) {
                return num*checkWin(boards)
            }
        }
        return -1
    }

    fun part2(input: List<String>): Int {
        var (drawn, boards) = parseInput(input)
        for (num in drawn) {
            for (board in boards) {
                for (i in 0..4) {
                    for (j in 0..4) {
                        if (board[i][j].first == num) {
                            board[i][j] = Pair(board[i][j].first, true)
                        }
                    }
                }
            }
            val newBoards = removeWinners(boards)
            if (newBoards.isEmpty()) {
                return calculateBoard(boards[0])*num
            } else {
                boards = newBoards
            }
        }
        return -1
    }

    val testInput = readInput("day04_test")
    parseInput(testInput)
    println(part1(testInput))
    println(part2(testInput))

    val input = readInput("day04")
    println(part1(input))
    println(part2(input))
}