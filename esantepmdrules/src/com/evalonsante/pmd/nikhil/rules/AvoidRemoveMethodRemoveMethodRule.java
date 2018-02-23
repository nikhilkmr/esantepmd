package com.evalonsante.pmd.nikhil.rules;

import java.util.List;

import org.jaxen.JaxenException;

import com.evalonsante.pmd.constants.ErrorMessage;

import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.java.ast.ASTClassOrInterfaceBodyDeclaration;
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
                    // List<Node> declarations = cls.findChildNodesWithXPath("//ClassOrInterfaceBody/ClassOrInterfaceBodyDeclaration");
                    List<Node> declarations = cls
                            .findChildNodesWithXPath("//ClassOrInterfaceType[@Image='List' or @Image='Set' or @Image='ArrayList' or @Image='LinkedList' or @Image='Vector']");
                    //                    List<Node> dec1 = cls.findChildNodesWithXPath("//ClassOrInterfaceType[@Image='Set']");
                    //                    List<Node> dec2 = cls.findChildNodesWithXPath("//ClassOrInterfaceType[@Image='ArrayList']");
                    //                    List<Node> dec3 = cls.findChildNodesWithXPath("//ClassOrInterfaceType[@Image='LinkedList']");
                    //                    List<Node> dec4 = cls.findChildNodesWithXPath("//ClassOrInterfaceType[@Image='Vector']");
                    //                    declarations.addAll(dec1);
                    //                    declarations.addAll(dec2);
                    //                    declarations.addAll(dec3);
                    //                    declarations.addAll(dec4);
                    //List<ASTClassOrInterfaceType> type = null;
                    // List<ASTVariableDeclaratorId> name = null;
                    for (Node declaration : declarations)
                    {
                        // type = declaration.findDescendantsOfType(ASTClassOrInterfaceType.class);
                        // String typeImage = type.get(0).getImage();
                        ASTClassOrInterfaceBodyDeclaration parent = declaration.getFirstParentOfType(ASTClassOrInterfaceBodyDeclaration.class);

                        List<ASTVariableDeclaratorId> name = parent.findDescendantsOfType(ASTVariableDeclaratorId.class);
                        for (ASTVariableDeclaratorId nameImg : name)
                        {
                            if (nameImg.getImage().equals(var))
                            {
                                addViolationWithMessage(data, functioncall, ErrorMessage.REMOVE_METHOD_RULE.toString());
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
