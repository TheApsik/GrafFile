package net.example.cebulasoft.graffile;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

class FileInformer {

    private final FilesConnectionInfo filesConnectionInfo;

    FileInformer(FilesConnectionInfo filesConnectionInfo) {
        this.filesConnectionInfo = filesConnectionInfo;
    }

    /**
     * Scans all files to extract file names, packages and number of their occurrences in other files (counts only single occurrence in each file).
     * Omits occurrences of class X in file X.
     *
     * @param filesFullNames List of files (absolute paths).
     */
    void scanFiles(List<String> filesFullNames) {
        extractFileInfos(filesFullNames);
        findConnections(filesFullNames);
        System.out.println(filesConnectionInfo);
    }

    private void findConnections(List<String> filesFullNames) {
        for (String className : filesConnectionInfo.getKeySet()) {
            for (String fileFullName : filesFullNames) {
                if (sameClassAndFile(fileFullName, className)) {
                    System.out.println(fileFullName + " " + className);
                    continue;
                }

                try {
                    Scanner scanner = new Scanner(new File(fileFullName));
                    checkForOccurrencesInFile(className, scanner);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private boolean sameClassAndFile(String fileFullName, String className) {
        return extractName(fileFullName).equals(className);
    }

    private void extractFileInfos(List<String> filesFullNames) {
        for (String fileFullName : filesFullNames) {
            try {
                Scanner scanner = new Scanner(new File(fileFullName));
                while (scanner.hasNextLine()) {
                    for (String line : scanner.nextLine().split(";")) {
                        if (line.contains("package ")) {
                            String fileName = extractName(fileFullName);
                            filesConnectionInfo.put(fileName, FileInfo.of(extractPackagePath(line), fileName));
                        }
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void checkForOccurrencesInFile(String className, Scanner scanner) {
        while (scanner.hasNextLine()) {
            for (String line : scanner.nextLine().split(";")) {
                if (line.matches("(.*) " + className + " (.*)")) {
                    filesConnectionInfo.get(className).addReference();
                    return;
                }
            }
        }
    }

    private String extractName(String fileName) {
        String name = fileName.substring(fileName.lastIndexOf("\\")).replace("\\", "");
        return name.substring(0, name.lastIndexOf("."));
    }

    private static String extractPackagePath(String line) {
        return line.replace("package", "").replaceAll(" ", "");
    }
}
