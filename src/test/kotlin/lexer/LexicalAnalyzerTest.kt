package lexer

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class LexicalAnalyzerTest {
    @Test
    fun `Should generate tokens for an addition case with integers`(){
        val testInput = "3 + 5"
        val expectedTokens = listOf(
            Token("3", TokenType.NUMERIC),
            Token("+", TokenType.OPERATION),
            Token("5", TokenType.NUMERIC),
            Token("", TokenType.EOF)
        )
        val lexicalAnalyzer = LexicalAnalyzer()
        val tokensGenerated = lexicalAnalyzer.generateTokens(testInput)
        assertEquals(expectedTokens, tokensGenerated)
    }

    @Test
    fun `Should generate tokens for an multi operation case with integers and floats`(){
        val testInput = "12 - 5 * 4.25"
        val expectedTokens = listOf(
            Token("12", TokenType.NUMERIC),
            Token("-", TokenType.OPERATION),
            Token("5", TokenType.NUMERIC),
            Token("*", TokenType.OPERATION),
            Token("4.25", TokenType.NUMERIC),
            Token("", TokenType.EOF)
        )
        val lexicalAnalyzer = LexicalAnalyzer()
        val tokensGenerated = lexicalAnalyzer.generateTokens(testInput)
        assertEquals(expectedTokens, tokensGenerated)
    }

}