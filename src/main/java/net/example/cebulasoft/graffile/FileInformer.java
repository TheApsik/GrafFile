       package net.example.cebulasoft.graffile;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class FileInformer {
    // filesNames {FileInfo.java, FileInformer.java, FileFinder.java, etc...}
    public static String getValueOfElement(String element, String text){
        int startElement = text.indexOf(element);
        if (startElement != -1) {
            String beforeElement = text.substring(startElement + element.length()).replaceAll("[; ]", "");
            //System.out.print(beforeElement);
            //System.out.println(" " + beforeElement.matches("[A-Za-z. ]+"));
            if (!beforeElement.equals("")){
                // System.out.println(beforeElement.matches("[A-Za-z]"));
            }
        }
        return null;
    }

    // Line like: int[] a = new int[5]; String b = "; hi \t what's up!;"; // add two variables;
    // split to array: ['int[] a = new int[5];', 'String b = "; hi \t what's up!;";']
    public static String[] splitLines(String line){
        ArrayList<String> lines = new ArrayList<>(1);
        StringBuilder builder = new StringBuilder();

        boolean isText = false;
        boolean isSpecialChar = false;
        boolean isWhiteChar = false;

        char c;

        for (int i = 0; i < line.length(); i++) {
            c = line.charAt(i);

            if(c == ' ' || c == '\t') {
                if(isWhiteChar) // only one white character should be added;
                    continue;
                isWhiteChar = true;
                if(i == 0) // if line start with white character it should be ignore
                    continue;
                c = ' '; // white character always should be space;
            }else {
                isWhiteChar = false;

                switch (c) {
                    case '\'':
                    case '"':
                        if (isSpecialChar)
                            isSpecialChar = false;
                        else
                            isText = !isText;
                        break;
                    case '\\': // char: '\'
                        if (isText) {                        //if char is inside text it can be special char that can be \", \' or \; character
                            isSpecialChar = !isSpecialChar; //don't want to split it
                        }
                        break;
                    case ';':
                        builder.append(';');
                        if (!isText) {
                            lines.add(builder.toString());
                            builder.setLength(0); //clear builder
                        }
                        continue;
                }
            }
            builder.append(c);
        }
        if(builder.length() != 0)
            lines.add(builder.toString());
        return lines.toArray(new String[0]);
    }

    public static void getInfo(List<String> filesNames, FilesConnectionInfo connections){
        for (String fileName: filesNames) {
            File file= new File(fileName);
            try {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()){
                    String sc = scanner.nextLine();
                    if(fileName.contains("FileInformer")){
                        String[] liness = splitLines(sc);
                        //System.out.print(liness.length == 0);
                        System.out.println(Arrays.toString(liness));
                    }
                    String[] lines = splitLines(sc);
                    if(lines.length != 0){
                        for (String line : lines) {
                            if (line.contains("package")) {
                                String pack = line.replace("package", "").replaceAll(" ", ""); // Wyłusujemy nazwę pakietu
                                //System.out.println(fileName);
                                getValueOfElement("package", line);
                                int lastPointer = fileName.lastIndexOf(".");
                                String name = fileName.substring(0, lastPointer);
                                connections.put(name, new FileInfo(pack, fileName));
                            }
                        }
                    }
                }
            }catch (FileNotFoundException e){
                e.printStackTrace();
            }
        }
        for (String fileName: filesNames) {
            File file= new File(fileName);
            try {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()){
                    String[] lines=scanner.nextLine().split(";");
                    for (String line: lines) {
                        for (String className: connections.keySet()){
                           // System.out.println(className);
                            if(line.contains(className)){
                                connections.get(className).addReference(fileName); //TODO: Naprawic polaczenie miedzy plikami(jedno dla wszystkich)
                            }
                        }
                    }
                }
            }catch (FileNotFoundException e){
                e.printStackTrace();
            }
        }
    }
}
