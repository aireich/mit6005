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
   * Enter a parse tree produced by the {@code Group}
   * labeled alternative in {@link ExpressionParser#expr}.
   * @param ctx the parse tree
   */
  void enterGroup(ExpressionParser.GroupContext ctx);
  /**
   * Exit a parse tree produced by the {@code Group}
   * labeled alternative in {@link ExpressionParser#expr}.
   * @param ctx the parse tree
   */
  void exitGroup(ExpressionParser.GroupContext ctx);
  /**
   * Enter a parse tree produced by the {@code Variable}
   * labeled alternative in {@link ExpressionParser#expr}.
   * @param ctx the parse tree
   */
  void enterVariable(ExpressionParser.VariableContext ctx);
  /**
   * Exit a parse tree produced by the {@code Variable}
   * labeled alternative in {@link ExpressionParser#expr}.
   * @param ctx the parse tree
   */
  void exitVariable(ExpressionParser.VariableContext ctx);
  /**
   * Enter a parse tree produced by the {@code Number}
   * labeled alternative in {@link ExpressionParser#expr}.
   * @param ctx the parse tree
   */
  void enterNumber(ExpressionParser.NumberContext ctx);
  /**
   * Exit a parse tree produced by the {@code Number}
   * labeled alternative in {@link ExpressionParser#expr}.
   * @param ctx the parse tree
   */
  void exitNumber(ExpressionParser.NumberContext ctx);
  /**
   * Enter a parse tree produced by the {@code Multiply}
   * labeled alternative in {@link ExpressionParser#expr}.
   * @param ctx the parse tree
   */
  void enterMultiply(ExpressionParser.MultiplyContext ctx);
  /**
   * Exit a parse tree produced by the {@code Multiply}
   * labeled alternative in {@link ExpressionParser#expr}.
   * @param ctx the parse tree
   */
  void exitMultiply(ExpressionParser.MultiplyContext ctx);
  /**
   * Enter a parse tree produced by the {@code Plus}
   * labeled alternative in {@link ExpressionParser#expr}.
   * @param ctx the parse tree
   */
  void enterPlus(ExpressionParser.PlusContext ctx);
  /**
   * Exit a parse tree produced by the {@code Plus}
   * labeled alternative in {@link ExpressionParser#expr}.
   * @param ctx the parse tree
   */
  void exitPlus(ExpressionParser.PlusContext ctx);
}