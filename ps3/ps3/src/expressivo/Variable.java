package expressivo;

import java.util.Map;

/**
 * Representing a case-sensitive variable in an expression
 *
 */
public class Variable implements Expression {
    /**
     * Abstract Function:
     * AF(String name): a case-sensitive variable
     * 
     * Representation Invariants:
     * name can not be null or empty
     * name can only consists of letters
     * 
     * Safety from Exposure:
     * private immutable value
     */
    private final String name;
    
    public Variable(String name) {
        this.name = name;
    }
    
    private void checkRep() {
        assert name != null;
        assert name.length() > 0;
        assert name.matches("[A-Za-z]+");
    }
    
    @Override 
    public String toString() {
        return name;
    }
    
    @Override 
    public boolean equals(Object thatObject) {
        if (thatObject == this) {
            return true;
        } else if (!(thatObject instanceof Variable)) {
            return false;
        } else {
            return name.equals(thatObject.toString());
        }
    }
    
    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public Expression diff(String input) {
        if (input.equals(toString())) {
            return new Number("1");
        }
        checkRep();
        return new Number("0");
    }

    @Override
    public Expression simplify(Map<String, Double> env) {
        assert env != null;
        if (env.keySet().contains(name)) {
            checkRep();
            return new Number(env.get(name));
        } else {
            return this;
        }
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
