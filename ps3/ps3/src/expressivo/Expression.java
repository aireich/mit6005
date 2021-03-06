/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package expressivo;

import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.antlr.v4.gui.Trees;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;

import expressivo.parser.ExpressionLexer;
import expressivo.parser.ExpressionListener;
import expressivo.parser.ExpressionParser;
import expressivo.parser.ExpressionParser.ExprContext;
import expressivo.parser.ExpressionParser.GroupContext;
import expressivo.parser.ExpressionParser.MultiplyContext;
import expressivo.parser.ExpressionParser.NumberContext;
import expressivo.parser.ExpressionParser.PlusContext;
import expressivo.parser.ExpressionParser.VariableContext;
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
    public static Expression parse(String input) throws IllegalArgumentException{
        try {
            assert input != null;
            CharStream stream = new ANTLRInputStream(input);
            ExpressionLexer lexer = new ExpressionLexer(stream);
            lexer.reportErrorsAsExceptions();
            TokenStream tokens = new CommonTokenStream(lexer);
     
            ExpressionParser parser = new ExpressionParser(tokens);
            parser.reportErrorsAsExceptions();
            ParseTree tree = parser.expr();
            
         // *** Debugging option #1: print the tree to the console
//            System.err.println(tree.toStringTree(parser));

            // *** Debugging option #2: show the tree in a window
//            Trees.inspect(tree, parser);
            MakeExpression exprMaker = new MakeExpression();
            new ParseTreeWalker().walk(exprMaker, tree);
            return exprMaker.getExpression();
        } catch(ParseCancellationException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
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
    
    /**
     * @param input should have the same format as Variable
     * @return the derivative of this expression with respect to input string expression 
     */
    public Expression diff(String input);
    
    /**
     * simplify this Expression to the simplest format by using given environment
     * @param env A map for variables and their value
     * @return simplest Expression
     */
    public Expression simplify(Map<String, Double> env);
    
    /**
     * define the behavior when another Expression add this Expression
     * @param e another Expression need to be added
     * @return Expression added
     */
    // the main purpose is to use this method combined with addCoefficient() in Number Expression to realize two Number
    // added. In other Expression, this method will act the same as new Plus(.., ..)
    public Expression addBy(Expression e); 
    
    /**
     * define the behavior when another Expression times this Expression
     * @param e another Expression need to be timed
     * @return Expression timed
     */
    // the main purpose is to use this method combined with timeCoefficient() in Number Expression to realize two Number
    // timed. In other Expression, this method will act the same as new Multiply(.., ..)
    public Expression timeBy(Expression e);
    
    /**
     * times a coefficient 
     * @param num coefficient as double type
     * @return the result of this expression times a coefficient
     */
    public Expression timeCoefficient(double num);
    
    /**
     * add a coefficient 
     * @param num coefficient as double type
     * @return the result of this expression adds a coefficient
     */
    public Expression addCoefficient(double num);
    
    
    // TODO more instance methods
    class MakeExpression implements ExpressionListener {
        private Stack<Expression> stack = new Stack<>();
        
        public Expression getExpression() {
            return stack.get(0);
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
        public void enterGroup(GroupContext ctx) {}

        @Override
        public void exitGroup(GroupContext ctx) {
            Expression gr = stack.pop();
            stack.push(new Group(gr));
        }

        @Override
        public void enterVariable(VariableContext ctx) {}

        @Override
        public void exitVariable(VariableContext ctx) {
            String var = ctx.getText();
            Expression variable = new Variable(var);
            stack.push(variable);    
        }

        @Override
        public void enterNumber(NumberContext ctx) {}

        @Override
        public void exitNumber(NumberContext ctx) {
            String value = ctx.getText();
            Expression num = new Number(value);
            stack.push(num);  
        }

        @Override
        public void enterMultiply(MultiplyContext ctx) {}

        @Override
        public void exitMultiply(MultiplyContext ctx) {
            List<ExprContext> adds = ctx.expr();
            assert stack.size() >= adds.size();
            assert adds.size() > 0;
            Expression sum = stack.pop();
            for (int i = 1; i < adds.size(); ++i) {
                sum = stack.pop().timeBy(sum);
            }
            stack.push(sum);
        }

        @Override
        public void enterPlus(PlusContext ctx) {}

        @Override
        public void exitPlus(PlusContext ctx) {
            List<ExprContext> adds = ctx.expr();
            assert stack.size() >= adds.size();
            assert adds.size() > 0;
            Expression sum = stack.pop();
            for (int i = 1; i < adds.size(); ++i) {
                sum = stack.pop().addBy(sum);
            }
            stack.push(sum);    
        }
    }
}
