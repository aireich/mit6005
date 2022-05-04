// Generated from Expression.g4 by ANTLR 4.5.1

package expressivo.parser;
// Do not edit this .java file! Edit the grammar in Expression.g4 and re-run Antlr.

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ExpressionParser}.
 */
public interface ExpressionListener extends ParseTreeListener {
  /**
   * Enter a parse tree produced by {@link ExpressionParser#root}.
   * @param ctx the parse tree
   */
  void enterRoot(ExpressionParser.RootContext ctx);
  /**
   * Exit a parse tree produced by {@link ExpressionParser#root}.
   * @param ctx the parse tree
   */
  void exitRoot(ExpressionParser.RootContext ctx);
  /**
   * Enter a parse tree produced by {@link ExpressionParser#expression}.
   * @param ctx the parse tree
   */
  void enterExpression(ExpressionParser.ExpressionContext ctx);
  /**
   * Exit a parse tree produced by {@link ExpressionParser#expression}.
   * @param ctx the parse tree
   */
  void exitExpression(ExpressionParser.ExpressionContext ctx);
  /**
   * Enter a parse tree produced by {@link ExpressionParser#atom}.
   * @param ctx the parse tree
   */
  void enterAtom(ExpressionParser.AtomContext ctx);
  /**
   * Exit a parse tree produced by {@link ExpressionParser#atom}.
   * @param ctx the parse tree
   */
  void exitAtom(ExpressionParser.AtomContext ctx);
  /**
   * Enter a parse tree produced by {@link ExpressionParser#plus}.
   * @param ctx the parse tree
   */
  void enterPlus(ExpressionParser.PlusContext ctx);
  /**
   * Exit a parse tree produced by {@link ExpressionParser#plus}.
   * @param ctx the parse tree
   */
  void exitPlus(ExpressionParser.PlusContext ctx);
  /**
   * Enter a parse tree produced by {@link ExpressionParser#multiply}.
   * @param ctx the parse tree
   */
  void enterMultiply(ExpressionParser.MultiplyContext ctx);
  /**
   * Exit a parse tree produced by {@link ExpressionParser#multiply}.
   * @param ctx the parse tree
   */
  void exitMultiply(ExpressionParser.MultiplyContext ctx);
}