/*
 * Copyright (c) 2018 Nikhil Kumar

 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions: 

 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.

 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

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

                    List<Node> declarations = cls
                            .findChildNodesWithXPath("//ClassOrInterfaceType[@Image='List' or @Image='Set' or @Image='ArrayList' or @Image='LinkedList' or @Image='Vector']");

                    for (Node declaration : declarations)
                    {

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
