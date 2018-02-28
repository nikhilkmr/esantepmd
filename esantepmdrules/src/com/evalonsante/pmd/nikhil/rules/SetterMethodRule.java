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
import net.sourceforge.pmd.lang.java.ast.ASTAssignmentOperator;
import net.sourceforge.pmd.lang.java.ast.ASTClassOrInterfaceDeclaration;
import net.sourceforge.pmd.lang.java.ast.ASTExpression;
import net.sourceforge.pmd.lang.java.ast.ASTFieldDeclaration;
import net.sourceforge.pmd.lang.java.ast.ASTMethodDeclaration;
import net.sourceforge.pmd.lang.java.ast.ASTPrimaryExpression;
import net.sourceforge.pmd.lang.java.ast.ASTPrimaryPrefix;
import net.sourceforge.pmd.lang.java.ast.ASTPrimarySuffix;
import net.sourceforge.pmd.lang.java.ast.ASTStatementExpression;
import net.sourceforge.pmd.lang.java.ast.ASTVariableDeclaratorId;
import net.sourceforge.pmd.lang.java.rule.AbstractJavaRule;

/**
 * @author nnikhil
 * Created on:15-Oct-2016
 * @author nisnegi
 * modified on :21-feb-2018
 */
public class SetterMethodRule extends AbstractJavaRule
{
    public Object visit(ASTMethodDeclaration node, Object data)
    {

        ASTClassOrInterfaceDeclaration cls = node.getFirstParentOfType(ASTClassOrInterfaceDeclaration.class);
        List<ASTFieldDeclaration> fields = cls.findDescendantsOfType(ASTFieldDeclaration.class);

        if (node.getMethodName().startsWith("set"))
        {
            String fieldNameFrmMethod = node.getMethodName().substring(3);
            boolean isGenuineSetter = false;
            for (ASTFieldDeclaration field : fields)
            {
                Node node1 = field.findDescendantsOfType(ASTVariableDeclaratorId.class).get(0);
                if (node1.getImage().toLowerCase().equals(fieldNameFrmMethod.toLowerCase()))
                {
                    isGenuineSetter = true;
                    break;
                }

            }
            if (isGenuineSetter)
            {
                boolean flag = true;

                int i = node.getBlock().jjtGetNumChildren();
                if (i == 1)
                {
                    if (node.getFirstDescendantOfType(ASTStatementExpression.class) != null)
                    {
                        Node node1 = node.getFirstDescendantOfType(ASTStatementExpression.class);
                        if (node1.getFirstDescendantOfType(ASTPrimaryExpression.class) != null
                                && node1.getFirstDescendantOfType(ASTPrimaryPrefix.class) != null
                                && node1.getFirstDescendantOfType(ASTPrimarySuffix.class) != null && node1.getFirstDescendantOfType(ASTExpression.class) != null
                                && node1.getFirstDescendantOfType(ASTAssignmentOperator.class) != null)
                        {
                            ASTPrimaryPrefix prefix = node1.getFirstDescendantOfType(ASTPrimaryPrefix.class);
                            ASTPrimarySuffix suffix = node1.getFirstDescendantOfType(ASTPrimarySuffix.class);
                            if (suffix.getImage().equals(fieldNameFrmMethod.toLowerCase()))
                            {
                                Node expr = node1.getFirstDescendantOfType(ASTExpression.class);
                                Node primaryPrefix = null;
                                try
                                {
                                    primaryPrefix = expr.findChildNodesWithXPath("./PrimaryExpression/PrimaryPrefix/Name").get(0);
                                }
                                catch (JaxenException e)
                                {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }
                                if (primaryPrefix.getImage().equals(node.getFirstDescendantOfType(ASTVariableDeclaratorId.class).getImage()))
                                {
                                    flag = false;
                                }

                            }
                        }

                    }
                    if (flag == true)
                    {
                        addViolationWithMessage(data, node, ErrorMessage.SETTER_METHOD_RULE.toString());
                    }
                }
                else
                {
                    addViolationWithMessage(data, node, ErrorMessage.SETTER_METHOD_RULE.toString());
                }

            }
        }

        return data;
    }

}
