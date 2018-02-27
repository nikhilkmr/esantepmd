package com.evalonsante.pmd.nikhil.rules;

import java.util.List;

import com.evalonsante.pmd.constants.ErrorMessage;

import net.sourceforge.pmd.lang.java.ast.ASTClassOrInterfaceDeclaration;
import net.sourceforge.pmd.lang.java.ast.ASTExpression;
import net.sourceforge.pmd.lang.java.ast.ASTLiteral;
import net.sourceforge.pmd.lang.java.ast.ASTMethodDeclaration;
import net.sourceforge.pmd.lang.java.ast.ASTName;
import net.sourceforge.pmd.lang.java.ast.ASTPrimaryPrefix;
import net.sourceforge.pmd.lang.java.ast.ASTPrimarySuffix;
import net.sourceforge.pmd.lang.java.rule.AbstractJavaRule;

/** 
 * @author nisnegi
 * modified on :27-feb-2018
 */
public class StringComaprisonRule extends AbstractJavaRule
{
    public Object visit(ASTMethodDeclaration node, Object data)
    {
        boolean flag;

        ASTClassOrInterfaceDeclaration cls = node.getFirstParentOfType(ASTClassOrInterfaceDeclaration.class);

        List<ASTExpression> exprs = cls.findDescendantsOfType(ASTExpression.class);

        if (!exprs.isEmpty())
        {
        for (ASTExpression expr : exprs)
            {
                flag = true;
                ASTPrimaryPrefix prefix = expr.getFirstDescendantOfType(ASTPrimaryPrefix.class);
                ASTPrimarySuffix suffix = expr.getFirstDescendantOfType(ASTPrimarySuffix.class);
                if (prefix != null && suffix != null)
                {
                    if (prefix.getFirstDescendantOfType(ASTName.class) != (null))
                {
                    if (prefix.getFirstDescendantOfType(ASTName.class).getImage().endsWith("equalsIgnoreCase"))
                    {
                        if (suffix.getFirstDescendantOfType(ASTLiteral.class).getImage().equals(null))
                        {
                            flag = false;
                        }
                    }

            if (flag == true)
            {
                    addViolationWithMessage(data, expr, ErrorMessage.STRING_COMPARISON_RULE.toString());
                    break;
            }
                }
                }
        }
        }


        return data;
    }

}
