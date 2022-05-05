package expressivo;

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
}
