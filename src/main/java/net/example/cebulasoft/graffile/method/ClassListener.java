package net.example.cebulasoft.graffile.method;

import net.example.cebulasoft.graffile.Java8BaseListener;
import net.example.cebulasoft.graffile.Java8Parser;

public class ClassListener extends Java8BaseListener {

    private ClassInfo parsedClass = new ClassInfo();

    @Override
    public void enterClassDeclaration(Java8Parser.ClassDeclarationContext ctx) {
        String className = ctx.normalClassDeclaration().Identifier().getText();
        parsedClass.setName(className);
    }

    @Override
    public void enterMethodDeclaration(Java8Parser.MethodDeclarationContext ctx) {
        parsedClass.addMethod(ctx.methodHeader().methodDeclarator().Identifier().getText());
    }

    public ClassInfo getParsedClass(){
        return parsedClass;
    }
}
