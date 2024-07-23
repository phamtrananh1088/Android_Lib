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
    class Message(
        val x: Int
    ) {}

}