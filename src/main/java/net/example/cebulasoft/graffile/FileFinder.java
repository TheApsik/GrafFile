package net.example.cebulasoft.graffile;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
//public class FileFinder
public class FileFinder {
	public static List<String> getListOfFile(String nameDirectory, String extension) {
		File folder = new File(nameDirectory);
		List<String> result = new LinkedList<>();

		if (folder.exists()) {
			search(".*" + extension, folder, result);
		} else {
			throw new RuntimeException("Folder of this path doęs not exist.");
		}

		return result;
	}

	private static void search(String pattern, File folder, List<String> result) {
		for (File f : folder.listFiles()) {
		    if(f != null) {
                if (f.isDirectory()) {
                    search(pattern, f, result);
                }

                if (f.isFile()) {
                    if (f.getName().matches(pattern)) {
                        result.add(f.getAbsolutePath());
                    }
                }
            }

		}
	}

}