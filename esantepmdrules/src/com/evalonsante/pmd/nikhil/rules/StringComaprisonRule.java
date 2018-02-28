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
