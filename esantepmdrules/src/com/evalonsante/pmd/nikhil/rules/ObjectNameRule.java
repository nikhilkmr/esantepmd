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
 * modified on :23-feb-2018
 */
public class ObjectNameRule extends AbstractJavaRule
{
    public Object visit(ASTMethodDeclaration node, Object data)
    {

        ASTClassOrInterfaceDeclaration cls = node.getFirstParentOfType(ASTClassOrInterfaceDeclaration.class);
        try
        {
            List<Node> declarations = cls.findChildNodesWithXPath("//Type/ReferenceType/ClassOrInterfaceType");
            for (Node declaration : declarations)
            {
                String ClassName = declaration.getImage().toLowerCase();
                ASTClassOrInterfaceBodyDeclaration parent = declaration.getFirstParentOfType(ASTClassOrInterfaceBodyDeclaration.class);
                List<ASTVariableDeclaratorId> names = parent.findDescendantsOfType(ASTVariableDeclaratorId.class);
                for (ASTVariableDeclaratorId name : names)
                {
                    String ObjName = name.getImage();
                    if (!ObjName.equals(ClassName))
                    {
                        addViolationWithMessage(data, declaration, ErrorMessage.OBJECT_NAME_RULE.toString());

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
