/**
 * 
 */
package com.evalonsante.pmd.nikhil.rules;

import net.sourceforge.pmd.lang.java.ast.ASTMethodDeclaration;
import net.sourceforge.pmd.lang.java.rule.AbstractJavaRule;

import com.evalonsante.pmd.constants.ErrorMessage;

/**
 * @author nnikhil
 *
 *         15-Oct-2016
 */
public class GetterMethodRule extends AbstractJavaRule {
	public Object visit(ASTMethodDeclaration node, Object data) {

		if (node.getMethodName().startsWith("get")
				|| node.getMethodName().startsWith("is")) {
			int i = node.getBlock().jjtGetNumChildren();
			if (i > 1) {
				addViolationWithMessage(data, node,
						ErrorMessage.GETTER_METHOD_RULE.toString());
			}
		}
		return data;
	}

}
