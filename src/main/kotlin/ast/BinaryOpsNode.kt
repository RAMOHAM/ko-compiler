package ast

import lexer.Token

class BinaryOpsNode (private val leftValue : Node, private val opsToken : Token, private val rightValue : Node) : Node {
    override fun visit(){
        TODO("Not yet implemented")
    }
    override fun toString(): String {
        return "BinOps(left=($leftValue, Ops=${opsToken.getValue()} right=$rightValue))"
    }

}