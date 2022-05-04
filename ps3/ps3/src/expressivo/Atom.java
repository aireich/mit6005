package expressivo;

public class Atom implements Expression {
    
    /**
     * if the Atom is a terminal Number
     * @return if the Atom is a Number
     */
    public boolean isNumber() {
        return false;
    }
    
    /**
     * if the Atom is a terminal Variable
     * @return if the Atom is a Variable
     */
    public boolean isVariable() {
        return false;
    }
    
    /**
     * if the Atom is a non-terminal '(' Expression ')'
     * @return if the Atom is a '(' Expression ')'
     */
    public boolean isGroup() {
        return !isVariable() && !isNumber();
    }
}
