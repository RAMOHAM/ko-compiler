package interpreter

import ast.BinaryOpsNode
import ast.NumericNode
import ast.UnaryOpsNode
import lexer.Token
import lexer.TokenType
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class InterpreterTest {

    @Test
    fun `should perform a simple addition parse`(){
        val leftNode = NumericNode(Token("3", TokenType.NUMERIC))
        val rightNode = NumericNode(Token("5", TokenType.NUMERIC))
        val opsToken = Token("+", TokenType.OPERATION)
        val parseTree = BinaryOpsNode(leftNode, opsToken, rightNode)

        val interpreter = Interpreter()
        val parseResult = interpreter.parseAST(parseTree)

        assertEquals(parseResult, 8.0)
    }

    @Test
    fun `should perform a simple addition parse with unary operations`(){
        val leftNode = NumericNode(Token("3", TokenType.NUMERIC))
        val rightNode = UnaryOpsNode(Token("-5", TokenType.NUMERIC), (Token("-",TokenType.OPERATION)))
        val opsToken = Token("+", TokenType.OPERATION)
        val parseTree = BinaryOpsNode(leftNode, opsToken, rightNode)

        val interpreter = Interpreter()
        val parseResult = interpreter.parseAST(parseTree)

        assertEquals(parseResult, -2.0)
    }
}