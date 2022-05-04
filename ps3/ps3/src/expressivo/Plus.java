package expressivo;

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
        return left.toString() + " + " + right.toString();
    }
    
    @Override 
    public boolean equals(Object thatObject) {
        if (thatObject == this) {
            return true;
        } else if (!(thatObject instanceof Plus)) {
            return false;
        }else {
            return left.toString().equals(right.toString());
        }
    }
    
    @Override
    public int hashCode() {
        return left.hashCode() * 31 + right.hashCode() * 31;
    }

}
