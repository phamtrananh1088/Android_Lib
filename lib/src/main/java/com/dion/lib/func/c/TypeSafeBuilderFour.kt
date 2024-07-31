package com.dion.lib.func.c

interface Element {
    fun render(builder: StringBuilder, indent: String)
}

class TextElement(val text: String): Element {
    override fun render(builder: StringBuilder, indent: String) {
        builder.append("$indent$text\n")
    }
}

@DslMarker
annotation class HtmlTagMarker

@HtmlTagMarker
abstract class Tag(val name: String): Element {
    val children = arrayListOf<Element>()
    val attributes = hashMapOf<String, String>()

    fun <T: Element> initTag(element: T, init: T.() -> Unit): T {
        element.init()
        children.add(element)
        return  element
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
        return  builder.toString()
    }
}

class HTML: Tag("html") {
    fun head(init: Head.() -> Unit) = initTag(Head(), init)
    fun body(init: Body.() -> Unit) = initTag(Body(), init)
}

abstract class TagWithText(name: String): Tag(name) {
    operator fun String.unaryPlus() {
        children.add(TextElement(this))
    }
}

class Head: TagWithText("head") {
    fun title(init: Title.() -> Unit): Title = initTag(Title(), init)
}

class Title(): TagWithText("title"){}

abstract class BodyTag(name: String): TagWithText(name) {
    fun p(init: P.() -> Unit) = initTag(P(), init)
    fun b(init: B.() -> Unit) = initTag(B(), init)
    fun h1(init: H1.() -> Unit) = initTag(H1(), init)
    fun a(href: String, init: A.() -> Unit) {
        val a = initTag(A(), init)
        a.href = href
    }
}

class Body: BodyTag("body")
class P: BodyTag("p")
class B: BodyTag("b")
class H1: BodyTag("h1")
class A: BodyTag("a") {
    var href: String
        get() = attributes["href"]!!
        set(value) {
            attributes["href"] = value
        }
}

fun html(init: HTML.() -> Unit): HTML {
    val html = HTML()
    html.init()
    return html
}
class TypeSafeBuilderFour {
    val html: HTML = html {
        head {
            title { +"The Last day of July" }
        }
        body {
            h1 { +"Hello" }
            b { +"Good Morning"}
            p { +"Now, we should have a breakfast"}
            a("localhost") { "go to"}
        }
    }
}