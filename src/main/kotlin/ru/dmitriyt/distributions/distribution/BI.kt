package ru.dmitriyt.distributions.distribution

import kotlin.math.floor
import kotlin.math.pow

class BI(
    input: String?,
    output: String?,
    m: Double,
    a: Double,
    b: Double
) : BaseDistribution(input, output, m, a, b) {

    override fun processNumbers(numbers: List<Double>): List<Double> {
        return numbers.map { binominal(it, a, b.toLong(), m) }
    }

    private fun factorial(a: Long): Long {
        var res = 1L
        repeat(a.toInt()) {
            res *= (it + 1)
        }
        return res
    }

    private fun c(b: Long, k: Long): Long {
        return factorial(b) / factorial(k) / factorial(b - k)
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

    private fun binominal(x: Double, a: Double, b: Long, m: Double): Double {
        val u = x / m
        var s = 0.0
        var k = 0L
        while (true) {
            s += c(b, k) * a.pow(k.toInt()) * (1 - a).pow((b - k).toInt())
            if (s > u) {
                return k.toDouble()
            }
            if (k < b - 1) {
                k++
            } else {
                break
            }
        }
        return b.toDouble()
    }
}