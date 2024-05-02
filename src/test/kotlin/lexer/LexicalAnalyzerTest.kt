package lexer

import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import kotlin.test.assertEquals

class LexicalAnalyzerTest {
    private fun captureStdOut(block: () -> Unit): String {
        val outputStream = ByteArrayOutputStream()
        val printStream = PrintStream(outputStream)
        val oldOut = System.out
        System.setOut(printStream)
        block()
        System.setOut(oldOut)
        return outputStream.toString().trim()
    }
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
        if (tokensGenerated != null) {
            assertEquals(expectedTokens, tokensGenerated)
        }
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
        if(tokensGenerated != null){
            assertEquals(expectedTokens, tokensGenerated)
        }
    }

    @Test
    fun `Should generate tokens for an multi operation case with parenthesis`(){
        val testInput = "5 - ( 4 / 2.5 )"
        val expectedTokens = listOf(
            Token("5", TokenType.NUMERIC),
            Token("-", TokenType.OPERATION),
            Token("(", TokenType.PARENTHESIS),
            Token("4", TokenType.NUMERIC),
            Token("/", TokenType.OPERATION),
            Token("2.5", TokenType.NUMERIC),
            Token(")", TokenType.PARENTHESIS),
            Token("", TokenType.EOF)
        )
        val lexicalAnalyzer = LexicalAnalyzer()
        val tokensGenerated = lexicalAnalyzer.generateTokens(testInput)
        if(tokensGenerated != null){
            assertEquals(expectedTokens, tokensGenerated)
        }
    }

    @Test
    fun `Should print out error message for invalid character`(){
        val testInput = "2 + 5 - h"
        val expectedException = "Syntax error : h is not a valid character"
        val lexicalAnalyzer = LexicalAnalyzer()
        val tokensGenerated = lexicalAnalyzer.generateTokens(testInput)
        val receivedException = captureStdOut { lexicalAnalyzer.generateTokens(testInput) }
        assertEquals(null, tokensGenerated)
        assertEquals(expectedException, receivedException)
    }

}