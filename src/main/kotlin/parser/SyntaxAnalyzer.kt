package parser

import ast.BinaryOpsNode
import ast.Node
import ast.NumericNode
import lexer.Token

class SyntaxAnalyzer (private val tokens: ArrayList<Token>){
    private var position = 0
    fun parseTokens() : Node {
        val parseResult = getExpression()
        return parseResult
    }

    private fun getFactor() : Node {
        val resultNode =  NumericNode(tokens[position])
        position++
        return resultNode
    }

    private fun getTerm() : Node {
        val termOps = listOf("*","/")
        var leftValue = getFactor()
        while (termOps.contains(tokens[position].getValue())) {
            val opsValue = tokens[position]
            position++
            leftValue = BinaryOpsNode(leftValue, opsValue, getFactor())
        }
        return leftValue
    }

    private fun getExpression() : Node {
        val expOps = listOf("+","-")
        var leftValue = getTerm()
        while (expOps.contains(tokens[position].getValue())){
            val opsValue = tokens[position]
            position++
            leftValue = BinaryOpsNode(leftValue, opsValue, getTerm())
        }
        return leftValue
    }
}