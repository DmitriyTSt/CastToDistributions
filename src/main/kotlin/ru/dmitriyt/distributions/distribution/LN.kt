package ru.dmitriyt.distributions.distribution

import kotlin.math.sqrt
import kotlin.math.ln
import kotlin.math.sin
import kotlin.math.cos
import kotlin.math.floor
import kotlin.math.exp

class LN(
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
        val preResult = first.mapIndexed { index, d -> d to second[index] }.map { longnorm(it, a, b, m) }
        val result = mutableListOf<Double>()
        preResult.forEach {
            result.add(it.first)
            result.add(it.second)
        }
        return result
    }

    override fun Double.smartFormat(digits: Int): String {
        val d = digits + 2
        return if (floor(this) == this) {
            this.toInt().toString()
        } else {
            var str = (floor(this * "1e$d".toDouble()) / "1e$d".toDouble()).toString()
            str = str.substring(0, str.length - 2)
            if (str.last() == '0') {
                str = str.substring(0, str.length - 1)
            }
            str
        }
    }

    private fun longnorm(x: Pair<Double, Double>, a: Double, b: Double, m: Double): Pair<Double, Double> {
        val z = norm(x, 0.0, 1.0, m)
        return a + exp(b - z.first) to a + exp(b - z.second)
    }


    private fun norm(x: Pair<Double, Double>, a: Double, b: Double, m: Double): Pair<Double, Double> {
        return a + b * sqrt(-2 * ln(1 - x.first / m)) * cos(2 * Math.PI * x.second / m) to
                a + b * sqrt(-2 * ln(1 - x.first / m)) * sin(2 * Math.PI * x.second / m)
    }
}