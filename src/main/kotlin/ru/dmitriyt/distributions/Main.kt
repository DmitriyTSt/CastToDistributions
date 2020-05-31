package ru.dmitriyt.distributions

import ru.dmitriyt.distributions.distribution.ST

fun main(args: Array<String>) {
    val arguments = parseArguments(args)
    if (arguments.containsKey(ArgsHelper.HELP.code)) {
        println("Описание параметров")
        println("/<code>:<value>")
        ArgsHelper.values().forEach {
            println("${it.code} ${it.description}")
        }
        println("Возможные распределения:")
        println(
            "st – стандартное равномерное с заданным интервалом;\n" +
                    "tr – треугольное распределение;\n" +
                    "ex – общее экспоненциальное распределение;\n" +
                    "nr – нормальное распределение;\n" +
                    "gm – гамма распределение;\n" +
                    "ln – логнормальное распределение;\n" +
                    "ls – логистическое распределение;\n" +
                    "bi – биномиальное распределение."
        )
    } else {
        val m = arguments[ArgsHelper.M.code]?.toDouble() ?: throw Exception("Некорректный атрибут m")
        val a = arguments[ArgsHelper.A.code]?.toDouble() ?: throw Exception("Некорректный атрибут a")
        val b = arguments[ArgsHelper.B.code]?.toDouble() ?: throw Exception("Некорректный атрибут b")

        val distribution = when (arguments[ArgsHelper.DISTRIBUTION.code]) {
            "st" -> {
                ST(
                    arguments[ArgsHelper.INPUT.code],
                    arguments[ArgsHelper.OUTPUT.code],
                    m,
                    a,
                    b
                )
            }
            else -> {
                println("Некорректный параметр d")
                null
            }
        }
        distribution?.castToDistribution()
    }
}

private fun parseArguments(args: Array<String>): HashMap<String, String> {
    val arguments = hashMapOf<String, String>()
    args.forEach {
        val divider = it.indexOf(":")
        if (divider != -1) {
            arguments[it.substring(1, divider)] = it.substring(divider + 1)
        } else {
            arguments[it.substring(1)] = ""
        }
    }
    return arguments
}