package expressivo;

import java.util.Map;

/**
 * An Expression enclosed by parenthesis
 */
public class Group implements Expression {
    /**
     * Abstract Function:
     * AF(Expression expr) = original Expression
     * 
     * Representation Invariants:
     * Expression can not be null
     * 
     * Safety from exposure:
     * private immutable type
     */
    
    private final Expression expr;
    
    public Group(Expression expr) {
        this.expr = expr;
    }
    
    public void checkRep() {
        assert expr != null;
    }
    
    @Override
    public String toString() {
        return "( " + expr.toString() + " )";
    }
    
    @Override
    public boolean equals(Object thatObject) {
        if (thatObject == this) {
            return true;
        } else {
            return expr.toString().equals(thatObject.toString()) || toString().equals(thatObject.toString());
        }
    }
    
    /**
     * return the inner expression without parenthesis
     * @return
     */
    public String innerToString() {
        return expr.toString();
    }
    
    @Override
    public int hashCode() {
        return expr.hashCode();
    }

    @Override
    public Expression diff(String input) {
        return expr.diff(input);
    }

    @Override
    public Expression simplify(Map<String, Double> env) {
        return expr.simplify(env);
    }

    @Override
    public Expression timeCoefficient(double num) {
        return expr.timeCoefficient(num);
    }

    @Override
    public Expression addCoefficient(double num) {
        return expr.addCoefficient(num);
    }

    @Override
    public Expression addBy(Expression e) {
        return new Plus(this, e);
    }

    @Override
    public Expression timeBy(Expression e) {
        return new Multiply(this, e);
    }
}
