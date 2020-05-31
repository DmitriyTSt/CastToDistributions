package ru.dmitriyt.distributions.distribution

import kotlin.math.floor

class ST(
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
            (floor(this * "1e$digits".toDouble()) / "1e$digits".toDouble()).toString()
        }
    }

    private fun getNumber(x: Double, a: Double, b: Double, m: Double): Double {
        return a + x / m * b
    }
}