/**
 * 
 */
package com.evalonsante.pmd.nikhil.rules;

import java.util.regex.Pattern;

import net.sourceforge.pmd.lang.java.rule.AbstractJavaRule;
import net.sourceforge.pmd.lang.jsp.ast.ASTJspComment;
import net.sourceforge.pmd.lang.jsp.ast.ASTJspDeclaration;
import net.sourceforge.pmd.lang.jsp.ast.ASTJspDeclarations;
import net.sourceforge.pmd.lang.jsp.ast.ASTJspDirective;
import net.sourceforge.pmd.lang.jsp.ast.ASTJspDirectiveAttribute;
import net.sourceforge.pmd.lang.jsp.ast.ASTJspDocument;
import net.sourceforge.pmd.lang.jsp.ast.ASTJspExpression;
import net.sourceforge.pmd.lang.jsp.ast.ASTJspExpressionInAttribute;
import net.sourceforge.pmd.lang.jsp.rule.AbstractJspRule;

import com.evalonsante.pmd.constants.ErrorMessage;
import com.evalonsante.pmd.constants.RegularExpression;

/**
 * @author nnikhil
 *
 *         15-Oct-2016
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
