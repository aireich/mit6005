// Generated from Expression.g4 by ANTLR 4.5.1

package expressivo.parser;
// Do not edit this .java file! Edit the grammar in Expression.g4 and re-run Antlr.

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ExpressionParser extends Parser {
  static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

  protected static final DFA[] _decisionToDFA;
  protected static final PredictionContextCache _sharedContextCache =
    new PredictionContextCache();
  public static final int
    T__0=1, T__1=2, NUM=3, VAR=4, ADD=5, TIME=6, SPACES=7;
  public static final int
    RULE_expr = 0;
  public static final String[] ruleNames = {
    "expr"
  };

  private static final String[] _LITERAL_NAMES = {
    null, "'('", "')'", null, null, "'+'", "'*'"
  };
  private static final String[] _SYMBOLIC_NAMES = {
    null, null, null, "NUM", "VAR", "ADD", "TIME", "SPACES"
  };
  public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

  /**
   * @deprecated Use {@link #VOCABULARY} instead.
   */
  @Deprecated
  public static final String[] tokenNames;
  static {
    tokenNames = new String[_SYMBOLIC_NAMES.length];
    for (int i = 0; i < tokenNames.length; i++) {
      tokenNames[i] = VOCABULARY.getLiteralName(i);
      if (tokenNames[i] == null) {
        tokenNames[i] = VOCABULARY.getSymbolicName(i);
      }

      if (tokenNames[i] == null) {
        tokenNames[i] = "<INVALID>";
      }
    }
  }

  @Override
  @Deprecated
  public String[] getTokenNames() {
    return tokenNames;
  }

  @Override

  public Vocabulary getVocabulary() {
    return VOCABULARY;
  }

  @Override
  public String getGrammarFileName() { return "Expression.g4"; }

  @Override
  public String[] getRuleNames() { return ruleNames; }

  @Override
  public String getSerializedATN() { return _serializedATN; }

  @Override
  public ATN getATN() { return _ATN; }


      // This method makes the lexer or parser stop running if it encounters
      // invalid input and throw a ParseCancellationException.
      public void reportErrorsAsExceptions() {
          // To prevent any reports to standard error, add this line:
          //removeErrorListeners();
          
          addErrorListener(new BaseErrorListener() {
              public void syntaxError(Recognizer<?, ?> recognizer,
                                      Object offendingSymbol,
                                      int line, int charPositionInLine,
                                      String msg, RecognitionException e) {
                  throw new ParseCancellationException(msg, e);
              }
          });
      }

  public ExpressionParser(TokenStream input) {
    super(input);
    _interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
  }
  public static class ExprContext extends ParserRuleContext {
    public ExprContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_expr; }
   
    public ExprContext() { }
    public void copyFrom(ExprContext ctx) {
      super.copyFrom(ctx);
    }
  }
  public static class GroupContext extends ExprContext {
    public ExprContext expr() {
      return getRuleContext(ExprContext.class,0);
    }
    public GroupContext(ExprContext ctx) { copyFrom(ctx); }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterGroup(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitGroup(this);
    }
  }
  public static class VariableContext extends ExprContext {
    public TerminalNode VAR() { return getToken(ExpressionParser.VAR, 0); }
    public VariableContext(ExprContext ctx) { copyFrom(ctx); }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterVariable(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitVariable(this);
    }
  }
  public static class NumberContext extends ExprContext {
    public TerminalNode NUM() { return getToken(ExpressionParser.NUM, 0); }
    public NumberContext(ExprContext ctx) { copyFrom(ctx); }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterNumber(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitNumber(this);
    }
  }
  public static class MultiplyContext extends ExprContext {
    public List<ExprContext> expr() {
      return getRuleContexts(ExprContext.class);
    }
    public ExprContext expr(int i) {
      return getRuleContext(ExprContext.class,i);
    }
    public TerminalNode TIME() { return getToken(ExpressionParser.TIME, 0); }
    public MultiplyContext(ExprContext ctx) { copyFrom(ctx); }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterMultiply(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitMultiply(this);
    }
  }
  public static class PlusContext extends ExprContext {
    public List<ExprContext> expr() {
      return getRuleContexts(ExprContext.class);
    }
    public ExprContext expr(int i) {
      return getRuleContext(ExprContext.class,i);
    }
    public TerminalNode ADD() { return getToken(ExpressionParser.ADD, 0); }
    public PlusContext(ExprContext ctx) { copyFrom(ctx); }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterPlus(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitPlus(this);
    }
  }

  public final ExprContext expr() throws RecognitionException {
    return expr(0);
  }

  private ExprContext expr(int _p) throws RecognitionException {
    ParserRuleContext _parentctx = _ctx;
    int _parentState = getState();
    ExprContext _localctx = new ExprContext(_ctx, _parentState);
    ExprContext _prevctx = _localctx;
    int _startState = 0;
    enterRecursionRule(_localctx, 0, RULE_expr, _p);
    try {
      int _alt;
      enterOuterAlt(_localctx, 1);
      {
      setState(9);
      switch (_input.LA(1)) {
      case T__0:
        {
        _localctx = new GroupContext(_localctx);
        _ctx = _localctx;
        _prevctx = _localctx;

        setState(3);
        match(T__0);
        setState(4);
        expr(0);
        setState(5);
        match(T__1);
        }
        break;
      case NUM:
        {
        _localctx = new NumberContext(_localctx);
        _ctx = _localctx;
        _prevctx = _localctx;
        setState(7);
        match(NUM);
        }
        break;
      case VAR:
        {
        _localctx = new VariableContext(_localctx);
        _ctx = _localctx;
        _prevctx = _localctx;
        setState(8);
        match(VAR);
        }
        break;
      default:
        throw new NoViableAltException(this);
      }
      _ctx.stop = _input.LT(-1);
      setState(19);
      _errHandler.sync(this);
      _alt = getInterpreter().adaptivePredict(_input,2,_ctx);
      while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
        if ( _alt==1 ) {
          if ( _parseListeners!=null ) triggerExitRuleEvent();
          _prevctx = _localctx;
          {
          setState(17);
          switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
          case 1:
            {
            _localctx = new MultiplyContext(new ExprContext(_parentctx, _parentState));
            pushNewRecursionContext(_localctx, _startState, RULE_expr);
            setState(11);
            if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
            setState(12);
            match(TIME);
            setState(13);
            expr(6);
            }
            break;
          case 2:
            {
            _localctx = new PlusContext(new ExprContext(_parentctx, _parentState));
            pushNewRecursionContext(_localctx, _startState, RULE_expr);
            setState(14);
            if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
            setState(15);
            match(ADD);
            setState(16);
            expr(5);
            }
            break;
          }
          } 
        }
        setState(21);
        _errHandler.sync(this);
        _alt = getInterpreter().adaptivePredict(_input,2,_ctx);
      }
      }
    }
    catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    }
    finally {
      unrollRecursionContexts(_parentctx);
    }
    return _localctx;
  }

  public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
    switch (ruleIndex) {
    case 0:
      return expr_sempred((ExprContext)_localctx, predIndex);
    }
    return true;
  }
  private boolean expr_sempred(ExprContext _localctx, int predIndex) {
    switch (predIndex) {
    case 0:
      return precpred(_ctx, 5);
    case 1:
      return precpred(_ctx, 4);
    }
    return true;
  }

  public static final String _serializedATN =
    "\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\t\31\4\2\t\2\3"+
      "\2\3\2\3\2\3\2\3\2\3\2\3\2\5\2\f\n\2\3\2\3\2\3\2\3\2\3\2\3\2\7\2\24"+
      "\n\2\f\2\16\2\27\13\2\3\2\2\3\2\3\2\2\2\33\2\13\3\2\2\2\4\5\b\2\1"+
      "\2\5\6\7\3\2\2\6\7\5\2\2\2\7\b\7\4\2\2\b\f\3\2\2\2\t\f\7\5\2\2\n\f"+
      "\7\6\2\2\13\4\3\2\2\2\13\t\3\2\2\2\13\n\3\2\2\2\f\25\3\2\2\2\r\16"+
      "\f\7\2\2\16\17\7\b\2\2\17\24\5\2\2\b\20\21\f\6\2\2\21\22\7\7\2\2\22"+
      "\24\5\2\2\7\23\r\3\2\2\2\23\20\3\2\2\2\24\27\3\2\2\2\25\23\3\2\2\2"+
      "\25\26\3\2\2\2\26\3\3\2\2\2\27\25\3\2\2\2\5\13\23\25";
  public static final ATN _ATN =
    new ATNDeserializer().deserialize(_serializedATN.toCharArray());
  static {
    _decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
    for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
      _decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
    }
  }
}