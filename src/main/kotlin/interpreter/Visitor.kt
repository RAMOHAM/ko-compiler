package interpreter

import ast.Node

open class Visitor {
    fun visit(node : Node) : Any {
        val visitMethod = "visit" + node.javaClass.simpleName
        val visitor = this::class.java.methods.find { it.name == visitMethod }
        if (visitor != null) {
            return visitor.invoke(this, node)
        }
        return noneVisit(node)
    }

    private fun noneVisit(node: Node){
        throw Exception("no visit method for " + node.javaClass.simpleName)
    }
}


