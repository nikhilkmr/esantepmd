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
package com.evalonsante.pmd.nikhil.rules;

import java.util.regex.Pattern;

import com.evalonsante.pmd.constants.ErrorMessage;
import com.evalonsante.pmd.constants.RegularExpression;

import net.sourceforge.pmd.lang.jsp.ast.ASTJspComment;
import net.sourceforge.pmd.lang.jsp.ast.ASTJspDeclarations;
import net.sourceforge.pmd.lang.jsp.ast.ASTJspDirective;
import net.sourceforge.pmd.lang.jsp.ast.ASTJspDirectiveAttribute;
import net.sourceforge.pmd.lang.jsp.ast.ASTJspDocument;
import net.sourceforge.pmd.lang.jsp.ast.ASTJspExpression;
import net.sourceforge.pmd.lang.jsp.ast.ASTJspExpressionInAttribute;
import net.sourceforge.pmd.lang.jsp.rule.AbstractJspRule;

/**
 * @author nnikhil
 * Created on:15-Oct-2016
 * 
 *         
 *         
 */
public class JspNamingRule extends AbstractJspRule {
	public Object visit(ASTJspExpression node, Object data) {

		Pattern pattern = Pattern
				.compile(RegularExpression.JSP_NAME.toString());
		boolean ok = pattern.matcher(node.getImage()).find();
		if (!ok) {
			
			addViolationWithMessage(data, node,
					ErrorMessage.JSP_NAME_RULE.toString());
		}

		return data;
	}

	public Object visit(ASTJspExpressionInAttribute node, Object data) {

		Pattern pattern = Pattern
				.compile(RegularExpression.JSP_NAME.toString());
		boolean ok = pattern.matcher(node.getImage()).find();
		if (!ok) {
			addViolationWithMessage(data, node,
					ErrorMessage.JSP_NAME_RULE.toString());
		}

		return data;
	}
	
	


	
	public Object visit(ASTJspDeclarations node, Object data) {

		Pattern pattern = Pattern
				.compile(RegularExpression.JSP_NAME.toString());
		boolean ok = pattern.matcher(node.getImage()).find();
		if (!ok) {
			addViolationWithMessage(data, node,
					ErrorMessage.JSP_NAME_RULE.toString());
		}

		return data;
	}

	public Object visit(ASTJspComment node, Object data) {

		Pattern pattern = Pattern
				.compile(RegularExpression.JSP_NAME.toString());
		boolean ok = pattern.matcher(node.getImage()).find();
		if (!ok) {
			addViolationWithMessage(data, node,
					ErrorMessage.JSP_NAME_RULE.toString());
		}

		return data;
	}

	public Object visit(ASTJspDirective node, Object data) {

		Pattern pattern = Pattern
				.compile(RegularExpression.JSP_NAME.toString());
		boolean ok = pattern.matcher(node.getImage()).find();
		if (!ok) {
			addViolationWithMessage(data, node,
					ErrorMessage.JSP_NAME_RULE.toString());
		}

		return data;
	}

	public Object visit(ASTJspDirectiveAttribute node, Object data) {

		Pattern pattern = Pattern
				.compile(RegularExpression.JSP_NAME.toString());
		boolean ok = pattern.matcher(node.getImage()).find();
		if (!ok) {
			addViolationWithMessage(data, node,
					ErrorMessage.JSP_NAME_RULE.toString());
		}

		return data;
	}

	public Object visit(ASTJspDocument node, Object data) {

		Pattern pattern = Pattern
				.compile(RegularExpression.JSP_NAME.toString());
		boolean ok = pattern.matcher(node.getImage()).find();
		if (!ok) {
			addViolationWithMessage(data, node,
					ErrorMessage.JSP_NAME_RULE.toString());
		}

		return data;
	}

}
