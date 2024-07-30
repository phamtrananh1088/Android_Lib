package com.dion.lib.func

class PackageT {
    fun printMessage(): String {
        val c = kotlin.Char(10)
        val e: List<Message> = listOf(Message(1), Message(2), Message(0))
        val f = e.sortedBy { m -> m.x }
        val g = kotlin.Comparator<Message>{m,n -> n.x - m.x}
        val h= e.sortedWith(g)
        val k = h.joinToString { it.x.toString() }
        return k
    }

    fun printMessage2(): String {
        val g= kotlin.Comparator<Int> { m, n -> n - m }
        val a: List<Int> = listOf(2,1,3)
        val h = a.sortedWith(g)
        return h.joinToString { it.toString() }
    }

    fun mapNotNull(): List<Message> {
        val a = listOf(1,2,3,4,5)
        return a.mapNotNull { i -> if (i%2==1) Message(i) else null }
    }

    class Message(
        val x: Int
    ) {}

}

public inline fun <T> Iterable<T>.forEach2(crossinline action: (T)-> Unit): Unit{
    for (element in this) {
        action(element)
    }
}

