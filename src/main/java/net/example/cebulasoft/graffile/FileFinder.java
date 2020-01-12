package net.example.cebulasoft.graffile;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

class FileFinder {
    private static final String JAVA_FILE_REGEX = "(.*).java";

    /**
     * Gets list of all files in project.
     *
     * @param baseDirectory Path to project directory.
     * @return List of files in project.
     */
    List<String> getListOfFiles(String baseDirectory) {
        List<String> result = new LinkedList<>();
        search(JAVA_FILE_REGEX, new File(baseDirectory), result);
        return result;
    }

    private void search(String pattern, File folder, List<String> result) {
        for (File f : Objects.requireNonNull(folder.listFiles())) {
            if (f.isDirectory()) {
                search(pattern, f, result);
            } else if (f.isFile()) {
                if (f.getName().matches(pattern)) {
                    result.add(f.getAbsolutePath());
                }
            }
        }
    }
}