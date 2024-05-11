package ast

import lexer.Token

class BinaryOpsNode (private val leftValue : Node, private val opsToken : Token, private val rightValue : Node) : Node {
    fun getLeftValue(): Node {
        return this.leftValue
    }
    fun getOperatorToken(): String {
        return this.opsToken.getValue()
    }
    fun getRightValue(): Node {
        return this.rightValue
    }

    override fun toString(): String {
        return "BinOps(left=($leftValue, Ops=${opsToken.getValue()} right=$rightValue))"
    }

}