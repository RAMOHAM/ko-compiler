package compiler

import lexer.LexicalAnalyzer
import parser.SyntaxAnalyzer

fun main() {
    while (true){
        print(">> ")
        val userInput = readln()
        if(userInput == "exit") break

        // Lexical Analysis
        val lexer = LexicalAnalyzer()
        val tokens = lexer.generateTokens(userInput)
        // Syntax Analysis
        if (tokens != null) {
            val parser = SyntaxAnalyzer(tokens)
            val parseResult = parser.parseTokens()
            println(parseResult.toString())
        }
    }
}