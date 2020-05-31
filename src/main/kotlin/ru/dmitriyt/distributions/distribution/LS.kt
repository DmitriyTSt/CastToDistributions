package ru.dmitriyt.distributions.distribution

import kotlin.math.floor
import kotlin.math.ln

class LS(
    input: String?,
    output: String?,
    m: Double,
    a: Double,
    b: Double
) : BaseDistribution(input, output, m, a, b) {

    override fun processNumbers(numbers: List<Double>): List<Double> {
        return numbers.map { logistic(it, a, b, m) }
    }

    override fun Double.smartFormat(digits: Int): String {
        return if (floor(this) == this) {
            this.toInt().toString()
        } else {
            var str = this.toString()
            str = str.substring(0, str.indexOf(".") + digits + 1)
            while (str.last() == '0') {
                str = str.substring(0, str.length - 1)
            }
            str
        }
    }

    fun logistic(x: Double, a: Double, b: Double, m: Double): Double {
        val u = x / m
        return a + b * ln(u / (1 - u))
    }
}