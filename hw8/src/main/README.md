В обычной блокирующей версии результат: 10164 KB использованной памяти и 508 миллисекунд затраченного времени
В реактивной версии результат: 19569 KB использованной памяти и 787 миллисекунд затраченного времени

Вероятно, большее использование ресурсов в реактированной версии связано с тем, что используется обертка над стримом. Работа происходила в одном потоке, поэтому мы не могли полностью реализовать потенциал неблокирующего ввода, которое и позволяет получить project reactor.