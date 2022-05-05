package expressivo;

/**
 * A non-negative decimal number
 *
 */
public class Number implements Expression{
    
    /**
     * Abstract Function:
     * AF(String value) = a non-negative decimal number
     * 
     * Representation Invariants:
     * non-negative number
     * 
     * Safety from Exposure:
     * private immutable value
     */
    private final double value;
    
    public Number(String value) {
        this.value = Double.parseDouble(value);
    }
    
    private void checkRep() {
        assert value >= 0 && value <= Double.MAX_VALUE;
    }
    
    @Override 
    public String toString() {
        return "" + value;
    }
    
    @Override 
    public boolean equals(Object thatObject) {
        if (thatObject == this) {
            return true;
        } else if (!(thatObject instanceof Number)) {
            return false;
        } else {
            double that = Double.parseDouble(thatObject.toString());
            if (-0.00001 <= that - value && that - value <= 0.00001) {
                return true;
            } else {
                return false;
            }
        }
    }
    
    // To avoid confusion about decimal places, we just use the integer part of the value
    @Override
    public int hashCode() {
        return Integer.hashCode((int) value);
    }
}
