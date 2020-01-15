package net.example.cebulasoft.graffile;

import net.example.cebulasoft.graffile.method.MethodDependencyResolver;

import java.util.List;

public class FileController {

    private final String baseDirectory;
    private final FileFinder fileFinder;
    private final FileInformer fileInformer;
    private final FilesConnectionInfo filesConnectionInfo;
    private final MethodDependencyResolver methodDependencyResolver;


    private FileController(String baseDirectory, FileFinder fileFinder, FileInformer fileInformer, FilesConnectionInfo filesConnectionInfo, MethodDependencyResolver methodDependencyResolver) {
        this.baseDirectory = baseDirectory;
        this.fileFinder = fileFinder;
        this.fileInformer = fileInformer;
        this.filesConnectionInfo = filesConnectionInfo;
        this.methodDependencyResolver = methodDependencyResolver;
    }

    public static void main(String... args) {
        FilesConnectionInfo filesConnectionInfo = new FilesConnectionInfo();
        FileController fc = new FileController("C:\\Users\\kakk\\IdeaProjects\\wimiip\\GrafFileTest", new FileFinder(), new FileInformer(filesConnectionInfo), filesConnectionInfo, new MethodDependencyResolver());
        fc.scan();
    }

    /**
     * Scans project from path given in argument, finds all classes and number of occurrences.
     */
    private void scan() {
        List<String> files = fileFinder.getListOfFiles(this.baseDirectory);
        fileInformer.scanFiles(files);

        methodDependencyResolver.resolveDependencies(files);

        FileGraphAdapter graph = new FileGraphAdapter(filesConnectionInfo);
        graph.show();
    }

}

