package net.example.cebulasoft.graffile;

import java.util.List;

public class FileController {

    private final String baseDirectory;
    private final FileFinder fileFinder;
    private final FileInformer fileInformer;

    private FileController(String baseDirectory, FileFinder fileFinder, FileInformer fileInformer) {
        this.baseDirectory = baseDirectory;
        this.fileFinder = fileFinder;
        this.fileInformer = fileInformer;
    }

    public static void main(String... args) {
        FilesConnectionInfo filesConnectionInfo = new FilesConnectionInfo();
        FileController fc = new FileController(args[0], new FileFinder(), new FileInformer(filesConnectionInfo));
        fc.scan();
    }

    /**
     * Scans project from path given in argument, finds all classes and number of occurrences.
     */
    private void scan() {
        List<String> files = fileFinder.getListOfFiles(this.baseDirectory);
        fileInformer.scanFiles(files);

        // FileGrafAdapter graf = new FileGrafAdapter(filesCollection); // make graf
        // graf.show(); // show graf
    }

}

