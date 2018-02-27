package com.evalonsante.pmd.nikhil.rules;

import java.util.List;

import org.jaxen.JaxenException;

import com.evalonsante.pmd.constants.ErrorMessage;

import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.java.ast.ASTClassOrInterfaceDeclaration;
import net.sourceforge.pmd.lang.java.ast.ASTMethodDeclaration;
import net.sourceforge.pmd.lang.java.ast.ASTVariableDeclaratorId;
import net.sourceforge.pmd.lang.java.rule.AbstractJavaRule;

/**
 * @author nisnegi
 * Created on :22-feb-2018
 */
public class AvoidRemoveMethodRemoveMethodRule extends AbstractJavaRule
{
    public Object visit(ASTMethodDeclaration node, Object data)
    {

        ASTClassOrInterfaceDeclaration cls = node.getFirstParentOfType(ASTClassOrInterfaceDeclaration.class);
        try
        {
            List<Node> functioncalls = cls.findChildNodesWithXPath("//BlockStatement/Statement/StatementExpression/PrimaryExpression/PrimaryPrefix/Name");
                    for (Node functioncall : functioncalls)
                    {
                        String funcName = functioncall.getImage();
                        if (funcName.endsWith("remove") || funcName.endsWith("removeAll"))
                        {
                    String[] varName = funcName.split("\\.", 2);
                    String var = varName[0].toString();
                    List<Node> nodes = cls.findChildNodesWithXPath("//Type/ReferenceType/ClassOrInterfaceType");
                    for (Node node1 : nodes)
                    {
                        String nodeImage = node1.getImage();
                        if (nodeImage.equals("List") || nodeImage.equals("Set") || nodeImage.equals("ArrayList") || nodeImage.equals("LinkedList") || nodeImage.equals("Vector"))
                        {
                            Node CollectionNode = node1.findDescendantsOfType(ASTVariableDeclaratorId.class).get(0);
                            if (var.equals(CollectionNode.getImage()))
                            {
                                addViolationWithMessage(data, node, ErrorMessage.REMOVE_METHOD_RULE.toString());
                                break;
                            }
                        }
                    }
                }

            }

        }
        catch (JaxenException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return data;
    }

}
