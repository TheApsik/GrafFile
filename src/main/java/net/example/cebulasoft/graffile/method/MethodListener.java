package net.example.cebulasoft.graffile.method;

import net.example.cebulasoft.graffile.Java8BaseListener;
import net.example.cebulasoft.graffile.Java8Parser;

import java.util.ArrayList;
import java.util.List;

public class MethodListener extends Java8BaseListener {

    private List<MethodInfo> methods = new ArrayList<>();

    @Override
    public void enterMethodDeclaration(Java8Parser.MethodDeclarationContext ctx) {
        String methodName = ctx.getText();
        methods.add(new MethodInfo(methodName));
    }

    public List<MethodInfo> getMethods() {
        return methods;
    }
}
