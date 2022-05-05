package expressivo;

import java.util.Map;

/**
 * Representing an addition expression
 *
 */
public class Plus implements Expression {
    /**
     * Abstract Function:
     * AF(Expression left, Expression right): an addition expression made of left + right
     * 
     * Representation Invariants:
     * left and right can not be null
     * 
     * Safety from Exposure:
     * private immutable value
     */
    private final Expression left;
    private final Expression right;
    
    public Plus(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }
    
    private void checkRep() {
        assert left != null && right != null;
    }
    
    @Override 
    public String toString() {
        String rep = "";
        if (left instanceof Group && !(right instanceof Group)) {
            rep += ((Group) left).innerToString() + " + " + right.toString();
        } else if (right instanceof Group && !(left instanceof Group)) {
            rep += left.toString() + " + " + "( " + ((Group) right).innerToString() + " )";
        } else if (right instanceof Group && left instanceof Group) {
            rep += ((Group) left).innerToString() + " + " + ((Group) right).innerToString();
        } else {
            rep += left.toString() + " + " + right.toString();
        }
        return rep;
    }
    
    @Override 
    public boolean equals(Object thatObject) {
        if (thatObject == this) {
            return true;
        } else if (!(thatObject instanceof Plus) && !(thatObject instanceof Group)) {
            return false;
        }else if (thatObject instanceof Group) {
            String group = "( " + toString() + " )";
            checkRep();
            return group.equals(thatObject.toString());
        } else { 
            checkRep();
            return toString().equals(thatObject.toString());
        }
    }
    
    @Override
    public int hashCode() {
        return left.hashCode() * 31 + right.hashCode() * 31;
    }

    @Override
    public Expression diff(String input) {
        return new Plus(left.diff(input), right.diff(input));
    }

    @Override
    public Expression simplify(Map<String, Double> env) {
        return left.simplify(env).addBy(right.simplify(env));
    }

    @Override
    public Expression timeCoefficient(double num) {
        Expression newNum = new Number(num);
        return new Plus(new Multiply(left, newNum), new Multiply(right, newNum));
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
