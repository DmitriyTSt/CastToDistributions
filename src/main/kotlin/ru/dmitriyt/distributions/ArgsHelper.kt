package ru.dmitriyt.distributions

enum class ArgsHelper(val code: String, val description: String) {
    INPUT("f", "имя файла с входной последовательностью. Если не указано, то используется стандартный поток ввода"),
    M("m", "неотрицательное целое число (32 бит), на вход подаются числа от 0 до m-1"),
    DISTRIBUTION("d", "код распределения для преобразования последовательности"),
    OUTPUT("o", "имя файлы с выходной последовательностью. Если отсутствует, то вывод происходит в стандартный поток вывода"),
    HELP("h", "Информация о допустимых параметрах командной строки программы"),
    A("a", "Параметр a для распределений"),
    B("b", "Параметр b для распределений"),
    C("c", "Параметр c для распределений (где требуется)")
}