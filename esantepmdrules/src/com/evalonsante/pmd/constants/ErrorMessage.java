/*
 * Copyright (c) 2018 Nikhil Kumar

 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions: 

 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.

 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */


package com.evalonsante.pmd.constants;


/**
 * 
 * 
 * @author nnikhil
 * commit test
 * @author nisnegi
 * modified on :21-feb-2018
 */

public enum ErrorMessage {
	INTERFACE_NAME_RULE("An interface name must start with 'I'"), GETTER_METHOD_RULE(
			"getter methods must not have anycode other than getting value."), SETTER_METHOD_RULE(
			"setter methods must not have anycode other than getting value."), JSP_NAME_RULE(
			"Name of a JSP must start with transaction code"), METHOD_PARAMETER_RULE(
			"Method must not have excessive number of parameters"), REMOVE_METHOD_RULE(
			"Use of remove() and removeAll() method should be avoided"), OBJECT_NAME_RULE(
			"Object name should be same as class name with camel case."), STRING_COMPARISON_RULE(
			"String literal should appear at the beginning while comparing Strings,to avoid Null Pointer Exception.");

	private final String msg;

	private ErrorMessage(final String msg) {
		this.msg = msg;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return msg;
	}
}
