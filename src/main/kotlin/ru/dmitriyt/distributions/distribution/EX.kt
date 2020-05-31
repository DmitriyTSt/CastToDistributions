package ru.dmitriyt.distributions.distribution

import kotlin.math.floor
import kotlin.math.ln

class EX(
    input: String?,
    output: String?,
    m: Double,
    a: Double,
    b: Double
) : BaseDistribution(input, output, m, a, b) {

    override fun processNumbers(numbers: List<Double>): List<Double> {
        return numbers.map { getNumber(it, a, b, m) }
    }

    override fun Double.smartFormat(digits: Int): String {
        return if (floor(this) == this) {
            this.toInt().toString()
        } else {
            val str = (floor(this * "1e$digits".toDouble()) / "1e$digits".toDouble()).toString()
            if (this < 0 && str.length == 6) str.substring(0, str.length - 1) else str
        }
    }

    private fun getNumber(x: Double, a: Double, b: Double, m: Double): Double {
        return a - b * ln(x / m)
    }
}