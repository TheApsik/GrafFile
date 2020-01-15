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
        findConnections();
    }

    private void extractFileInfos(List<String> filesFullNames) {
        for (String fileFullName : filesFullNames) {
            try {
                Scanner scanner = new Scanner(new File(fileFullName));

                while (scanner.hasNextLine()) {
                    for (String line : scanner.nextLine().split(";")) {
                        if (line.contains("package ")) {
                            String fileName = extractName(fileFullName);
                            filesConnectionInfo.put(fileName, FileInfo.of(extractPackagePath(line, fileName), fileFullName, fileName));
                        }
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void findConnections() {
        for (FileInfo fileInfo : filesConnectionInfo.getValues()) {
            for (String className : filesConnectionInfo.getKeySet()) {
                if (!className.equals(fileInfo.getName())) {
                    checkForOccurrencesInFile(className, fileInfo);
                }
            }
        }
    }

    private void checkForOccurrencesInFile(String className, FileInfo fileInfo) {
        try {
            Scanner scanner = new Scanner(new File(fileInfo.getPath()));
            while (scanner.hasNextLine()) {
                for (String line : scanner.nextLine().split(";")) {
                    if (line.matches("(.*) " + className + "(.*)")) {
                        filesConnectionInfo.get(fileInfo.getName()).addReference(className);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private String extractName(String fileName) {
        String name = fileName.substring(fileName.lastIndexOf("\\")).replace("\\", "");
        return name.substring(0, name.lastIndexOf("."));
    }

    private static String extractPackagePath(String line, String fileName) {
        return line.replace("package", "").replaceAll(" ", "").concat("." + fileName);
    }
}
