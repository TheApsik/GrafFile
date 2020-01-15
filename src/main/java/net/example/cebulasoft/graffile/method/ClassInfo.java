package net.example.cebulasoft.graffile.method;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ClassInfo {

    private String className;

    private String fullPath;

    private List<MethodInfo> methods = new ArrayList<>();

    public void addMethod(String text) {
        this.methods.add(new MethodInfo(text));
    }

    public void setName(String className) {
        this.className = className;
    }
}
