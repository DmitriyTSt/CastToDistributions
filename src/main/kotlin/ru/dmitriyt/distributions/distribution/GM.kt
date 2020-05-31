package ru.dmitriyt.distributions.distribution

import kotlin.math.sqrt
import kotlin.math.ln
import kotlin.math.sin
import kotlin.math.cos
import kotlin.math.floor
import kotlin.math.exp

class GM(
    input: String?,
    output: String?,
    m: Double,
    a: Double,
    b: Double,
    private val c: Double
) : BaseDistribution(input, output, m, a, b) {

    override fun processNumbers(numbers: List<Double>): List<Double> {
        val result = mutableListOf<Double>()
        if (c == c.toInt().toDouble()) {
            repeat(numbers.size / c.toInt()) {
                result.add(gammak(numbers.subList(it * c.toInt(), (it + 1) * c.toInt()), a, b, m))
            }
        } else {
            val k = (c - 0.5).toInt()
            val subSize = 2 * k + 2
            repeat(numbers.size / subSize) {
                val values = gammak05(numbers.subList(it * subSize, (it + 1) * subSize), a, b, k, m)
                result.add(values.first)
                result.add(values.second)
            }
        }
        return result
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

    private fun gammak(x: List<Double>, a: Double, b: Double, m: Double): Double {
        var lnbody = 1.0
        x.forEach {
            lnbody *= (1 - it / m)
        }
        return a - b * ln(lnbody)
    }

    private fun gammak05(x: List<Double>, a: Double, b: Double, k: Int, m: Double): Pair<Double, Double> {
        val z = norm(x[0] to x[1], 0.0, 1.0, m)
        var xx1 = 1.0
        x.subList(2, k + 2).forEach {
            xx1 *= (1 - it / m)
        }
        var xx2 = 1.0
        x.subList(k + 2, x.size).forEach {
            xx2 *= (1 - it / m)
        }
        return a + b * (z.first * z.first / 2 - ln(xx1)) to a + b * (z.second * z.second / 2 - ln(xx2))
    }

    private fun norm(x: Pair<Double, Double>, a: Double, b: Double, m: Double): Pair<Double, Double> {
        return a + b * sqrt(-2 * ln(1 - x.first / m)) * cos(2 * Math.PI * x.second / m) to
                a + b * sqrt(-2 * ln(1 - x.first / m)) * sin(2 * Math.PI * x.second / m)
    }
}