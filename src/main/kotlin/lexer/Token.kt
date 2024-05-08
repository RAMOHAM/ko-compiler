package lexer

class Token (private val value : String, private val type: TokenType) {
    fun getValue() : String {
        return value
    }
    fun getType() : TokenType {
        return type
    }
    override fun toString(): String {
        return "Token(value=$value, type=$type)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Token) return false

        if (value != other.value) return false
        if (type != other.type) return false

        return true
    }

    override fun hashCode(): Int {
        var result = value.hashCode()
        result = 31 * result + type.hashCode()
        return result
    }
}