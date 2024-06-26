package lexer

class LexicalAnalyzer {
    private var position = 0
    private val operationsList = listOf("+","-","/","*")
    private val parenthesisList = listOf("(", ")")
    fun generateTokens(codeInput : String) : ArrayList<Token>? {
        val formatInput = codeInput.split(" ")
        val tokens = ArrayList<Token>()
        while (position < formatInput.size){
            if(operationsList.contains(formatInput[position])){
                tokens.add(Token(formatInput[position], TokenType.OPERATION))
                position++
            }
            else if(formatInput[position].matches(("-?\\d+(\\.\\d+)?".toRegex()))){
                tokens.add(Token(formatInput[position], TokenType.NUMERIC))
                position++
            }
            else if(parenthesisList.contains(formatInput[position])){
                tokens.add(Token(formatInput[position],TokenType.PARENTHESIS))
                position++
            }else{
                println("Syntax error : " + formatInput[position] + " is not a valid character")
                return null
            }
        }
        tokens.add(Token("", TokenType.EOF))
        return tokens
    }
}