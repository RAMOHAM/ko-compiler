package parser

import ast.BinaryOpsNode
import ast.NumericNode
import lexer.LexicalAnalyzer
import lexer.Token
import lexer.TokenType
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class SyntaxAnalyzerTest {
    private fun generateToken (input : String) : ArrayList<Token>? {
        val lexer = LexicalAnalyzer()
        val tokens = lexer.generateTokens(input)
        if(tokens != null) return tokens
        return null
    }
    @Test
    fun `should produce a single BinaryOps node with two Numeric Nodes`(){
        val tokens = generateToken("3+5")
        if(tokens != null){
            val parser = SyntaxAnalyzer(tokens)
            val parseResult = parser.parseTokens()

            val expectedLeftNode = NumericNode(Token("3", TokenType.NUMERIC))
            val expectedRightNode = NumericNode(Token("5", TokenType.NUMERIC))
            val expectedNodes = BinaryOpsNode(expectedLeftNode, Token("+", TokenType.OPERATION), expectedRightNode)
            assertEquals(expectedNodes, parseResult)
        }
    }

    @Test
    fun `should prioritize multiplication binary ops first`(){
        val tokens = generateToken("3+5*8")
        if(tokens != null){
            val parser = SyntaxAnalyzer(tokens)
            val parseResult = parser.parseTokens()

            val expectedLeftNode = NumericNode(Token("3", TokenType.NUMERIC))
            val rightNodeFirst = NumericNode(Token("5", TokenType.NUMERIC))
            val rightNodeLast = NumericNode(Token("8", TokenType.NUMERIC))
            val expectedRightNode = BinaryOpsNode(rightNodeFirst, Token("*", TokenType.OPERATION), rightNodeLast )
            val expectedNodes = BinaryOpsNode(expectedLeftNode, Token("+", TokenType.OPERATION), expectedRightNode)
            assertEquals(expectedNodes, parseResult)
        }
    }

    @Test
    fun `should prioritize parenthesis`(){
        val tokens = generateToken(" 3 + ( 5 + 8 ) ")
        if(tokens != null){
            val parser = SyntaxAnalyzer(tokens)
            val parseResult = parser.parseTokens()

            val expectedLeftNode = NumericNode(Token("3", TokenType.NUMERIC))
            val rightNodeFirst = NumericNode(Token("5", TokenType.NUMERIC))
            val rightNodeLast = NumericNode(Token("8", TokenType.NUMERIC))
            val expectedRightNode = BinaryOpsNode(rightNodeFirst, Token("+", TokenType.OPERATION), rightNodeLast )
            val expectedNodes = BinaryOpsNode(expectedLeftNode, Token("+", TokenType.OPERATION), expectedRightNode)
            assertEquals(expectedNodes, parseResult)
        }
    }
}