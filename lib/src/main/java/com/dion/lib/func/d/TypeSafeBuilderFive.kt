package com.dion.lib.func.d

interface Element {
    fun render(builder: StringBuilder, indent: String)
}

class TextElement(val text: String) : Element {
    override fun render(builder: StringBuilder, indent: String) {
        builder.append("$indent$text\n")
    }
}

@DslMarker
annotation class HtmlTagMarker

@HtmlTagMarker
abstract class Tag(val name: String) : Element {
    val children = arrayListOf<Element>()
    val attributes = hashMapOf<String, String>()

    fun <T : Element> initTag(t: T, init: T.() -> Unit): T {
        t.init()
        children.add(t)
        return t
    }

    override fun render(builder: StringBuilder, indent: String) {
        builder.append("$indent<$name${renderAttributes()}>\n")
        for (c in children) {
            c.render(builder, "  ")
        }
        builder.append("$indent</$name>\n")
    }

    private fun renderAttributes(): String {
        val builder = StringBuilder()
        for ((attr, value) in attributes) {
            builder.append(" $attr=\"$value\"")
        }
        return builder.toString()
    }

    override fun toString(): String {
        val builder = StringBuilder()
        render(builder, "")
        return builder.toString()
    }
}

fun html(init: HTML.() -> Unit): HTML {
    val html = HTML()
    html.init()
    return html
}

class HTML() : Tag("html") {
    fun head(init: Head.() -> Unit) = initTag(Head(), init)

    fun body(init: Body.() -> Unit) = initTag(Body(), init)
}

class Head() : Tag("head") {
    fun title(init: Title.() -> Unit) = initTag(Title(), init)
}

abstract class TagWithText(name: String) : Tag(name) {
    operator fun String.unaryPlus() {
        children.add(TextElement(this))
    }
}

class Title : TagWithText("title")

abstract class BodyTag(name: String) : TagWithText(name) {
    fun p(init: P.() -> Unit): P = initTag(P(), init)

    fun b(init: B.() -> Unit): B = initTag(B(), init)

    fun h1(init: H1.() -> Unit): H1 = initTag(H1(), init)

    fun a(href: String, init: A.() -> Unit): A {
        val a = initTag(A(), init)
        a.href = href
        return a
    }
}

class Body() : BodyTag("body")

class P() : BodyTag("p")

class B() : BodyTag("b")

class H1() : BodyTag("h1")

class A() : BodyTag("a") {
    var href: String
        get() = attributes["href"]!!
        set(value) {
            attributes["href"] = value
        }
}


class TypeSafeBuilderFive {
    val html: HTML = html {
        head {
            title { +"nice day" }
        }
        body {
            h1 { +"onepiece 1" }
            b { +"match with Kaido" }
            p { +"what is an attractive match" }
            a("localhost") { +"click here to view" }
        }
    }
}
