package com.evalonsante.pmd.nikhil.rules;

import java.util.List;

import com.evalonsante.pmd.constants.ErrorMessage;

import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.java.ast.ASTClassOrInterfaceDeclaration;
import net.sourceforge.pmd.lang.java.ast.ASTFormalParameters;
import net.sourceforge.pmd.lang.java.ast.ASTMethodDeclaration;
import net.sourceforge.pmd.lang.java.rule.AbstractJavaRule;

/**
 * @author nisnegi
 * Created on :22-feb-2018
 */
public class ParametersRule extends AbstractJavaRule
{
    public Object visit(ASTMethodDeclaration node, Object data)
    {

        ASTClassOrInterfaceDeclaration cls = node.getFirstParentOfType(ASTClassOrInterfaceDeclaration.class);

        List<ASTMethodDeclaration> methods = cls.findDescendantsOfType(ASTMethodDeclaration.class);

        for (ASTMethodDeclaration method : methods)
        {
            Node node1 = method.findDescendantsOfType(ASTFormalParameters.class).get(0);
            int count = node1.jjtGetNumChildren();
            if (count > 5)
            {
                addViolationWithMessage(data, node, ErrorMessage.METHOD_PARAMETER_RULE.toString());
            }

        }

        return data;
    }

}
