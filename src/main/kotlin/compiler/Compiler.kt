package compiler

import lexer.LexicalAnalyzer

fun main() {
    while (true){
        print(">> ")
        val userInput = readln()
        if(userInput == "exit") break

        val lexer = LexicalAnalyzer()
        println(lexer.generateTokens(userInput))
    }
}