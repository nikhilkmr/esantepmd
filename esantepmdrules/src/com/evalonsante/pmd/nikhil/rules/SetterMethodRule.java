/**
 * 
 */
package com.evalonsante.pmd.nikhil.rules;

import com.evalonsante.pmd.constants.ErrorMessage;

import net.sourceforge.pmd.lang.java.ast.ASTMethodDeclaration;
import net.sourceforge.pmd.lang.java.rule.AbstractJavaRule;

/**
 * @author nnikhil
 *
 * 15-Oct-2016
 */
public class SetterMethodRule extends AbstractJavaRule {
	public Object visit(ASTMethodDeclaration node, Object data) {

		if (node.getMethodName().startsWith("set")) {
			int i = node.getBlock().jjtGetNumChildren();
			if (i > 1) {
				addViolationWithMessage(data, node,
						ErrorMessage.SETTER_METHOD_RULE.toString());
			}
		}
		return data;
	}
}
