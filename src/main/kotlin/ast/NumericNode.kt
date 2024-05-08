package ast

import lexer.Token

class NumericNode (private val value: Token) : Node {
    fun getValue() : String {
        return value.getValue()
    }

    override fun toString(): String {
        return "Num(${value.getValue()})"
    }
    override fun visit() {
        TODO("Not yet implemented")
    }

}