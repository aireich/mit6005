package expressivo;

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
        } else if (!(thatObject instanceof Group)) {
            return false;
        } else {
            return toString().equals(thatObject.toString());
        }
    }
    
    @Override
    public int hashCode() {
        return expr.hashCode();
    }
}
