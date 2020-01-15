package net.example.cebulasoft.graffile.method;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

public class MethodDependencyResolver {

    public Object extractMethods(String filePath) {
        File file = new File(filePath);
        try {
            String fileContent = FileUtils.readFileToString(file, String.valueOf(StandardCharsets.UTF_8));
            net.example.cebulasoft.graffile.Java8Lexer java8Lexer = new net.example.cebulasoft.graffile.Java8Lexer(CharStreams.fromString(fileContent));
            CommonTokenStream tokens = new CommonTokenStream(java8Lexer);
            net.example.cebulasoft.graffile.Java8Parser parser = new net.example.cebulasoft.graffile.Java8Parser(tokens);

            ParseTreeWalker walker = new ParseTreeWalker();
            ClassListener classListener = new ClassListener();
            walker.walk(classListener, parser.compilationUnit());
            System.out.println(classListener.getParsedClass());

        } catch (IOException e) {

        }
        return "s";
    }

    public void resolveDependencies(List<String> files) {
        files.stream().map(this::extractMethods).collect(Collectors.toList());
    }
}
