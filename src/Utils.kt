import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = File("src/inputs", "$name.txt").readLines()

fun List<String>.asInts() = this.map { it.toInt() }.toList()
fun List<String>.asBoolPair() = this.map { Pair(it.toInt(), false) }.toTypedArray()