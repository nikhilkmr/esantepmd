/**
 * 
 */
package com.evalonsante.pmd.constants;

/**
 * @author nnikhil
 * commit test
 *
 */
public enum ErrorMessage {
	INTERFACE_NAME_RULE("An interface name must start with 'I'"),
	GETTER_METHOD_RULE("getter methods must not have anycode other than getting value."), 
	SETTER_METHOD_RULE("setter methods must not have anycode other than getting value."),
    JSP_NAME_RULE("Name of a JSP must start with transaction code"), METHOD_PARAMETER_RULE("Method must not have excessive number of parameters");
	
	 private final String msg;

	   
	    private ErrorMessage(final String msg) {
	        this.msg = msg;
	    }

	    /* (non-Javadoc)
	     * @see java.lang.Enum#toString()
	     */
	    @Override
	    public String toString() {
	        return msg;
	    }
}
