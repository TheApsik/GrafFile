
package net.example.cebulasoft.graffile;

/**
 * Połączenie GraphX i GraphB
 */
class FileGraphAdapter {
    private GraphX graphX;

    FileGraphAdapter(FilesConnectionInfo filesConnectionInfo) {
        this.graphX = new GraphX(new GraphB(filesConnectionInfo).getGraph());
    }

    void show() {
        this.graphX.Exec();
    }
}
