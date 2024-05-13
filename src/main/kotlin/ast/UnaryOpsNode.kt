package ast

import lexer.Token

class UnaryOpsNode(private val value : Token, private val unaryOps : Token) : Node {
    fun getValue() : String {
        return value.getValue()
    }

    override fun toString(): String {
        return "Unary(${value.getValue()})"
    }
}