package ru.dmitriyt.distributions.distribution

import java.io.File

abstract class BaseDistribution(
    input: String?,
    output: String?,
    protected val m: Double,
    protected val a: Double,
    protected val b: Double
) {
    private var fileIn: File? = null
    private var fileOut: File? = null

    init {
        input?.let {
            try {
                fileIn = File(it)
            } catch (e: Exception) {
                println("Ошибка доступа к файлу INPUT")
            }
        }
        output?.let {
            try {
                fileOut = File(it)
            } catch (e: Exception) {
                println("Ошибка доступа к файлу OUTPUT")
            }
        }
    }

    fun castToDistribution() {
        val inputData = if (fileIn != null) {
            fileIn?.readLines()?.joinToString("\n")
        } else {
            val lines = StringBuilder()
            var s = readLine()
            while (s?.isNotBlank() == true) {
                lines.append(s)
                lines.append('\n')
                s = readLine()
            }
            String(lines)
        }
        val numbers = inputData?.split(Regex("[\n ]"))?.filter { it.isNotEmpty() }?.map { it.toDouble() }
        val result = numbers?.let { processNumbers(it) }
        if (fileOut != null) {
            result?.forEach {
                fileOut?.appendText(it.smartFormat(3) + " ")
            }
        } else {
            result?.forEach {
                print(it.smartFormat(3) + " ")
            }
        }
    }

    abstract fun processNumbers(numbers: List<Double>): List<Double>

    abstract fun Double.smartFormat(digits: Int): String
}