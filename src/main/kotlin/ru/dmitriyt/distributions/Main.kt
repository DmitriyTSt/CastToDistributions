package ru.dmitriyt.distributions

import ru.dmitriyt.distributions.distribution.*

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
            "tr" -> {
                TR(
                    arguments[ArgsHelper.INPUT.code],
                    arguments[ArgsHelper.OUTPUT.code],
                    m,
                    a,
                    b
                )
            }
            "ex" -> {
                EX(
                    arguments[ArgsHelper.INPUT.code],
                    arguments[ArgsHelper.OUTPUT.code],
                    m,
                    a,
                    b
                )
            }
            "nr" -> {
                NR(
                    arguments[ArgsHelper.INPUT.code],
                    arguments[ArgsHelper.OUTPUT.code],
                    m,
                    a,
                    b
                )
            }
            "ln" -> {
                LN(
                    arguments[ArgsHelper.INPUT.code],
                    arguments[ArgsHelper.OUTPUT.code],
                    m,
                    a,
                    b
                )
            }
            "ls" -> {
                LS(
                    arguments[ArgsHelper.INPUT.code],
                    arguments[ArgsHelper.OUTPUT.code],
                    m,
                    a,
                    b
                )
            }
            "bi" -> {
                BI(
                    arguments[ArgsHelper.INPUT.code],
                    arguments[ArgsHelper.OUTPUT.code],
                    m,
                    a,
                    b
                )
            }
            "gm" -> {
                val c = arguments[ArgsHelper.C.code]?.toDouble() ?: throw Exception("Некорректный атрибут c")
                GM(
                    arguments[ArgsHelper.INPUT.code],
                    arguments[ArgsHelper.OUTPUT.code],
                    m,
                    a,
                    b,
                    c
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