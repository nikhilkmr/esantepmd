package com.evalonsante.pmd.nikhil.rules;

import java.util.List;

import com.evalonsante.pmd.constants.ErrorMessage;

import net.sourceforge.pmd.lang.java.ast.ASTArgumentList;
import net.sourceforge.pmd.lang.java.ast.ASTClassOrInterfaceDeclaration;
import net.sourceforge.pmd.lang.java.ast.ASTLiteral;
import net.sourceforge.pmd.lang.java.ast.ASTLocalVariableDeclaration;
import net.sourceforge.pmd.lang.java.ast.ASTMethodDeclaration;
import net.sourceforge.pmd.lang.java.ast.ASTMethodDeclarator;
import net.sourceforge.pmd.lang.java.ast.ASTName;
import net.sourceforge.pmd.lang.java.ast.ASTPrimaryExpression;
import net.sourceforge.pmd.lang.java.ast.ASTPrimaryPrefix;
import net.sourceforge.pmd.lang.java.ast.ASTPrimarySuffix;
import net.sourceforge.pmd.lang.java.ast.ASTVariableDeclaratorId;
import net.sourceforge.pmd.lang.java.ast.ASTVariableInitializer;
import net.sourceforge.pmd.lang.java.rule.AbstractJavaRule;

/** 
 * @author nisnegi
 * modified on :28-May-2018
 * * modified on :06-June-2018
 */
public class RecordNameRule extends AbstractJavaRule
{

    public Object visit(ASTMethodDeclaration node, Object data)
    {
        boolean flag = false;


        ASTClassOrInterfaceDeclaration cls = node.getFirstParentOfType(ASTClassOrInterfaceDeclaration.class);
        String recordName = "";
        List<ASTMethodDeclaration> methods = cls.findDescendantsOfType(ASTMethodDeclaration.class);
        List<ASTPrimaryExpression> exprs = cls.findDescendantsOfType(ASTPrimaryExpression.class);
        ASTPrimaryExpression expression = null;
        if (!exprs.isEmpty())
        {
            for (ASTPrimaryExpression expr : exprs)
            {

                ASTPrimaryPrefix prefix = expr.getFirstDescendantOfType(ASTPrimaryPrefix.class);
                ASTPrimarySuffix suffix = expr.getFirstDescendantOfType(ASTPrimarySuffix.class);
                if (prefix != null && suffix != null)
                {
                    if (prefix.getFirstChildOfType(ASTName.class) != (null))
                    {
                        if (prefix.getFirstDescendantOfType(ASTName.class).getImage().equals("com.recordStatement"))
                        {
                            if (suffix.getFirstDescendantOfType(ASTArgumentList.class) != null)
                            {
                                if (null != suffix.getFirstDescendantOfType(ASTArgumentList.class).getFirstDescendantOfType(ASTPrimaryPrefix.class))
                                {
                                    ASTPrimaryPrefix arguement = suffix.getFirstDescendantOfType(ASTArgumentList.class).getFirstDescendantOfType(ASTPrimaryPrefix.class);
                                    if (null != arguement.getFirstDescendantOfType(ASTName.class))
                                    {
                                        recordName = arguement.getFirstDescendantOfType(ASTName.class).getImage();
                                    }
                                }
                            }
                        }


                    }
                }
                expression = expr;
            }

        for (ASTMethodDeclaration method : methods)
        {
            List<ASTLocalVariableDeclaration> localVariables = method.findDescendantsOfType(ASTLocalVariableDeclaration.class);
            for (ASTLocalVariableDeclaration localVariable : localVariables)
            {
                String variableName = localVariable.getFirstDescendantOfType(ASTVariableDeclaratorId.class).getImage();
                if ((!variableName.equalsIgnoreCase("")) && variableName.equalsIgnoreCase(recordName))
                {
                    ASTVariableInitializer varInit =  localVariable.getFirstDescendantOfType(ASTVariableInitializer.class);
                    if(null!=varInit){
                   if(null!= varInit.getFirstDescendantOfType(ASTLiteral.class)){
                    
                                    String val = varInit.getFirstDescendantOfType(ASTLiteral.class).getImage().toString();
                                String sub1 = val.substring(0, val.length() - 4);
                                String sub2 = val.substring(val.length() - 4, val.length() - 1);
                                String val2 = "\"" + "PS" + cls.getImage() + method.getFirstChildOfType(ASTMethodDeclarator.class).getImage();
                                if (sub1.equalsIgnoreCase(val2) && sub2.matches("[0-9][0-9][0-9]"))
                    {
                                    flag = false;
                    }
                                else
                                {
                                    flag = true;
                                }
                    }
                }
            }
                    if (flag)
                    {
                        addViolationWithMessage(data, expression, ErrorMessage.RECORD_NAME_RULE.toString());
                        break;
                    }
                }
            }
        }

        return data;
    }

}
