/**
 * 
 */
package com.evalonsante.pmd.nikhil.rules;

import com.evalonsante.pmd.constants.ErrorMessage;

import net.sourceforge.pmd.lang.java.ast.ASTClassOrInterfaceDeclaration;
import net.sourceforge.pmd.lang.java.rule.AbstractJavaRule;

/**
 * @author nnikhil
 *
 */
public class InterfaceNameingRule extends AbstractJavaRule {

	public Object visit(ASTClassOrInterfaceDeclaration node, Object data) {
		
		if (node.isInterface()) {
			if (!node.getImage().startsWith("I")) {				
				addViolationWithMessage(data, node, ErrorMessage.INTERFACE_NAME_RULE.toString());
			}
		}
		return data;
	}

	
	
	
	
	
	
}
