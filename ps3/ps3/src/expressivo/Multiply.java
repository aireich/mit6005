package expressivo;

import java.util.Map;

/**
 * An multiplication expression
 *
 */
public class Multiply implements Expression {
    
    private final Expression left;
    private final Expression right;
    /**
     * Abstract Function:
     * AF(Expression left, Expression right): an multiplication expression made of left + right
     * 
     * Representation Invariants:
     * left and right can not be null
     * 
     * Safety from Exposure:
     * private immutable value
     */
    public Multiply(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }
    
    private void checkRep() {
        assert left != null && right != null;
    }
    
    @Override 
    public String toString() {
        return "(" + left.toString() + " * " + right.toString() + ")";
    }
    
    @Override 
    public boolean equals(Object thatObject) {
        if (thatObject == this) {
            return true;
        } else if (!(thatObject instanceof Multiply) && !(thatObject instanceof Group)) {
            return false;
        }else {
            checkRep();
            return this.toString().equals(thatObject.toString());
        }
    }
    
    @Override
    public int hashCode() {
        return left.hashCode() * 31 + right.hashCode() * 31;
    }

    @Override
    public Expression diff(String input) {
        return new Plus(new Multiply(left, right.diff(input)), new Multiply(right, left.diff(input)));
    }

    @Override
    public Expression simplify(Map<String, Double> env) {
        return left.simplify(env).timeBy(right.simplify(env));
    }

    @Override
    public Expression timeCoefficient(double num) {
        return new Multiply(this, new Number(num));
    }

    @Override
    public Expression addCoefficient(double num) {
        return new Plus(this, new Number(num));
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
