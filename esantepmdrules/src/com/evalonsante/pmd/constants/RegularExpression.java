package com.evalonsante.pmd.constants;

public enum RegularExpression {

	JSP_NAME("[A-Z]+[0-9]+(.)+");
	
	private final String regex;

	   
    private RegularExpression(final String regex) {
        this.regex = regex;
    }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return regex;
    }
}
