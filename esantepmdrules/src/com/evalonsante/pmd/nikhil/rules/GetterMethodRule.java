/**
 * 
 */
package com.evalonsante.pmd.nikhil.rules;

import java.util.List;

import org.jaxen.JaxenException;

import com.evalonsante.pmd.constants.ErrorMessage;

import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.java.ast.ASTClassOrInterfaceDeclaration;
import net.sourceforge.pmd.lang.java.ast.ASTFieldDeclaration;
import net.sourceforge.pmd.lang.java.ast.ASTMethodDeclaration;
import net.sourceforge.pmd.lang.java.ast.ASTReturnStatement;
import net.sourceforge.pmd.lang.java.ast.ASTVariableDeclaratorId;
import net.sourceforge.pmd.lang.java.rule.AbstractJavaRule;

/**
 * @author nnikhil
 * Created on: 15-Oct-2016
 * @author nisnegi
 * modified on :21-feb-2018
 *
 *        
 */
public class GetterMethodRule extends AbstractJavaRule
{
    public Object visit(ASTMethodDeclaration node, Object data)
    {
        ASTClassOrInterfaceDeclaration cls = node.getFirstParentOfType(ASTClassOrInterfaceDeclaration.class);
        List<ASTFieldDeclaration> fields = cls.findDescendantsOfType(ASTFieldDeclaration.class);
        String fieldNameFrmMethod = null;
        if (node.getMethodName().startsWith("get") || node.getMethodName().startsWith("is"))
        {
            if (node.getMethodName().startsWith("get"))
            {
                fieldNameFrmMethod = node.getMethodName().substring(3);
            }
            else
            {
                fieldNameFrmMethod = node.getMethodName().substring(2);
            }
            boolean isGenuineGetter = false;
            for (ASTFieldDeclaration field : fields)
            {
                Node node1 = field.findDescendantsOfType(ASTVariableDeclaratorId.class).get(0);
                if (node1.getImage().toLowerCase().equals(fieldNameFrmMethod.toLowerCase()))
                {
                    isGenuineGetter = true;
                    break;
                }

            }
            if (isGenuineGetter)
            { boolean flag = true;

                int i = node.getBlock().jjtGetNumChildren();
                if (i == 1)
                {                  
                    if (node.getFirstDescendantOfType(ASTReturnStatement.class) != null)
                    {
                    Node node1 = node.getFirstDescendantOfType(ASTReturnStatement.class);
                        Node primaryPrefix = null;
                        try
                        {
                            primaryPrefix = node1.findChildNodesWithXPath("./Expression/PrimaryExpression/PrimaryPrefix/Name").get(0);
                        }
                        catch (JaxenException e)
                        {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        if (primaryPrefix != null && fieldNameFrmMethod.toLowerCase().equals(primaryPrefix.getImage()))
                        {
                            flag = false;
                        }

                }
                    if (flag == true)
                    {
                        addViolationWithMessage(data, node, ErrorMessage.GETTER_METHOD_RULE.toString());
                    }
                }
                else
                {
                    addViolationWithMessage(data, node, ErrorMessage.GETTER_METHOD_RULE.toString());
                }
            }
        }

        return data;
    }

}
