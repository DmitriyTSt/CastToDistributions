package ru.dmitriyt.distributions.distribution

import kotlin.math.floor

class TR(
    input: String?,
    output: String?,
    m: Double,
    a: Double,
    b: Double
) : BaseDistribution(input, output, m, a, b) {

    override fun processNumbers(numbers: List<Double>): List<Double> {
        val first = numbers.filterIndexed { index, _ -> index % 2 == 0 }
        val second = numbers.filterIndexed { index, _ -> index % 2 == 1 }
        if (first.size != second.size) {
            throw Exception("Нечетное количество чисел в последовательности")
        }
        return first.mapIndexed { index, d -> d to second[index] }.map { getNumber(it, a, b, m) }
    }

    override fun Double.smartFormat(digits: Int): String {
        return if (floor(this) == this) {
            this.toInt().toString()
        } else {
            val str = (floor(this * "1e$digits".toDouble()) / "1e$digits".toDouble()).toString()
            if (this < 0 && str.length == 6) str.substring(0, str.length - 1) else str
        }
    }

    private fun getNumber(x: Pair<Double, Double>, a: Double, b: Double, m: Double): Double {
        return a + b * (x.first / m + x.second / m - 1)
    }
}