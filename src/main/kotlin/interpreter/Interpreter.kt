package interpreter

import ast.BinaryOpsNode
import ast.Node
import ast.NumericNode
import ast.UnaryOpsNode

class Interpreter : Visitor() {
    fun parseAST(astTree : Node): Any {
        return this.visit(astTree)
    }
    fun visitBinaryOpsNode(node: BinaryOpsNode): Any {
        val left = this.visit(node.getLeftValue()).toString().toDouble()
        val right = this.visit(node.getRightValue()).toString().toDouble()
        return when (val operator = node.getOperatorToken()) {
            "+" -> left + right
            "-" -> left - right
            "*" -> left * right
            "/" -> left / right
            else -> throw IllegalArgumentException("Unsupported operator: $operator")
        }
    }
    fun visitNumericNode(node : NumericNode): String {
        return node.getValue()
    }

    fun visitUnaryOpsNode(node: UnaryOpsNode) : String {
        return node.getValue()
    }
}