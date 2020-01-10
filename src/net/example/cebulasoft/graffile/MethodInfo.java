package net.example.cebulasoft.graffile;

import java.util.Objects;

public class MethodInfo {
    public static final PRIVATE =0;
    public static final PROTECTED =1;
    public static final PUBLIC =2;

    private String name; // nazwa metody
    private String type;
    private String[] arguments;
    private int range;

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getRange() {
        return range;
    }

    public String[] getArguments() {
        return arguments;
    }

    public MethodInfo(String name, String type, int range, String[] arguments) {
        this.name = name;
        this.type = type;
        if (range > -1 && range < 3) {
            this.range = range;
        } else {
            throw new java.lang.RuntimeException("Thease argument is out of range.")
        }
        this.arguments = arguments;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;

        MethodInfo that = (MethodInfo) object;

//        if (!name.equals(that.name)) return false; // musi być !
//        if (!java.util.Arrays.equals(arguments, that.arguments)) return false;

        return name.equals(that.name) && java.util.Arrays.equals(arguments, that.arguments);
    }
}
