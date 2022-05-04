/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package expressivo;

import java.util.List;
import java.util.Stack;

import org.antlr.v4.gui.Trees;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;

import expressivo.parser.ExpressionLexer;
import expressivo.parser.ExpressionListener;
import expressivo.parser.ExpressionParser;
import expressivo.parser.ExpressionParser.AtomContext;
import expressivo.parser.ExpressionParser.ExpressionContext;
import expressivo.parser.ExpressionParser.MultiplyContext;
import expressivo.parser.ExpressionParser.PlusContext;
import expressivo.parser.ExpressionParser.RootContext;
/**
 * An immutable data type representing a polynomial expression of:
 *   + and *
 *   nonnegative integers and floating-point numbers
 *   variables (case-sensitive nonempty strings of letters)
 * 
 * <p>PS3 instructions: this is a required ADT interface.
 * You MUST NOT change its name or package or the names or type signatures of existing methods.
 * You may, however, add additional methods, or strengthen the specs of existing methods.
 * Declare concrete variants of Expression in their own Java source files.
 */
public interface Expression {
    
    // Datatype definition
    //   Expression = Number(value: double) + Variable(name: String) + Plus(left: Expression, right: Expression) + 
    //                Multiply(left: Expression, right: Expression)
    
    /**
     * Parse an expression.
     * @param input expression to parse, as defined in the PS3 handout.
     * @return expression AST for the input
     * @throws IllegalArgumentException if the expression is invalid
     */
    public static Expression parse(String input) {
        assert input != null;
        CharStream stream = new ANTLRInputStream(input);
        ExpressionLexer lexer = new ExpressionLexer(stream);
        lexer.reportErrorsAsExceptions();
        TokenStream tokens = new CommonTokenStream(lexer);
 
        ExpressionParser parser = new ExpressionParser(tokens);
        parser.reportErrorsAsExceptions();
        ParseTree tree = parser.root();
        
     // *** Debugging option #1: print the tree to the console
        System.err.println(tree.toStringTree(parser));

        // *** Debugging option #2: show the tree in a window
//        Trees.inspect(tree, parser);
        MakeExpression exprMaker = new MakeExpression();
        new ParseTreeWalker().walk(exprMaker, tree);
        return exprMaker.getExpression();


    }
    
    /** create an empty expression
     * @return an empty expression
     */
    public static Expression emptyExpression() {
        return new Number("0");
    }
    
    /**
     * @return a parsable representation of this expression, such that
     * for all e:Expression, e.equals(Expression.parse(e.toString())).
     */
    @Override 
    public String toString();

    /**
     * @param thatObject any object
     * @return true if and only if this and thatObject are structurally-equal
     * Expressions, as defined in the PS3 handout.
     */
    @Override
    public boolean equals(Object thatObject);
    
    /**
     * @return hash code value consistent with the equals() definition of structural
     * equality, such that for all e1,e2:Expression,
     *     e1.equals(e2) implies e1.hashCode() == e2.hashCode()
     */
    @Override
    public int hashCode();

    
    // TODO more instance methods
    class MakeExpression implements ExpressionListener {
        private Stack<Expression> stack = new Stack<>();
        
        public Expression getExpression() {
            return stack.get(0);
        }
        
        @Override
        public void exitExpression(ExpressionContext ctx) {
        }
        
        @Override
        public void exitAtom(AtomContext ctx) {
            if (ctx.NUMBER() != null) {
                // match a Number object
                String value = ctx.NUMBER().getText();
                Expression number = new Number(value);
                stack.push(number);
            } else if (ctx.VARIABLE() != null) {
                String value = ctx.VARIABLE().getText();
                Expression var = new Variable(value);
                stack.push(var);
            } else {
                // matched the '(' expression ')' alternative
                // do nothing, because expression's value is already on the stack
            }
            
        }
        
        @Override
        public void exitPlus(PlusContext ctx) {
            List<AtomContext> addends = ctx.atom();
            Expression atom = stack.pop();
            
            for (int i = 1; i < addends.size(); i++) {
                atom = new Plus(stack.pop(), atom);
            }
            
            stack.push(atom);
            
        }
        
        @Override
        public void exitMultiply(MultiplyContext ctx) {
            List<AtomContext> mulends = ctx.atom();
            Expression atom = stack.pop();
            for (int i = 1; i < mulends.size(); i++) {
                atom = new Multiply(stack.pop(), atom);
            }
            stack.push(atom);
        }
        
        @Override
        public void enterEveryRule(ParserRuleContext arg0) {}

        @Override
        public void exitEveryRule(ParserRuleContext arg0) {}

        @Override
        public void visitErrorNode(ErrorNode arg0) {}

        @Override
        public void visitTerminal(TerminalNode arg0) {}

        @Override
        public void enterRoot(RootContext ctx) {}

        @Override
        public void exitRoot(RootContext ctx) {}

        @Override
        public void enterExpression(ExpressionContext ctx) {}

        @Override
        public void enterAtom(AtomContext ctx) {}

        @Override
        public void enterPlus(PlusContext ctx) {}

        @Override
        public void enterMultiply(MultiplyContext ctx) {}
 
    }
}
