package parser

import ast.BinaryOpsNode
import ast.Node
import ast.NumericNode
import ast.UnaryOpsNode
import lexer.Token
import lexer.TokenType

class SyntaxAnalyzer (private val tokens: ArrayList<Token>){
    private var position = 0
    fun parseTokens() : Node {
        val parseResult = getExpression()
        return parseResult
    }

    private fun getFactor() : Node {
        if(tokens[position].getType() == TokenType.PARENTHESIS){
            position++
            val resultNode = getExpression()
            position++
            return resultNode
        }
        if(tokens[position].getValue().toCharArray()[0] == '-'){
            val resultNode = UnaryOpsNode(tokens[position], Token("-", TokenType.OPERATION))
            position++
            return resultNode
        }
        val resultNode =  NumericNode(tokens[position])
        position++
        return resultNode
    }

    private fun getTerm() : Node {
        val opsList = listOf("*","/")
        return getBinaryOpsExpression(::getFactor, opsList)
    }

    private fun getExpression() : Node {
        val opsList = listOf("+","-")
        return getBinaryOpsExpression(::getTerm, opsList)
    }

    private fun getBinaryOpsExpression(getLeftNode: () -> Node, opsList: List<String>) : Node {
        var leftValue = getLeftNode()
        while (opsList.contains(tokens[position].getValue())){
            val opsValue = tokens[position]
            position++
            leftValue = BinaryOpsNode(leftValue, opsValue, getLeftNode())
        }
        return leftValue
    }
}