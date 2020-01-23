package net.example.cebulasoft.graffile;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

public class FileInfo {
    private String path; // paczka
    private String name; // nazwa pliku razem z rozszerzeniem.
    private HashMap<String, Integer> connectionsToFiles; // przechowuje liste plików z których korzysta plik.

    public FileInfo(String path, String name) {
        this.path = path;
        this.name = name;
        this.connectionsToFiles = new HashMap<String, Integer>();
    }

    public String getPath() {
        return path;
    }

    public void addReference(String file){
        if(connectionsToFiles.containsKey(file)){
            connectionsToFiles.put(file, connectionsToFiles.get(file) + 1);
        }
        else{
            connectionsToFiles.put(file, 1);
        }
    }

    @Override
    public String toString() {
        return "FileInfo{" +
                "path='" + path + '\'' +
                ", name='" + name + '\'' +
                ", connectionsToFiles=" + connectionsToFiles +
                "}\n";
    }

    public Iterator<Map.Entry<String, Integer>> getIteratorToReferences(){
        return connectionsToFiles.entrySet().iterator();
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Override
    public int hashCode() {
        return Objects.hash(path, name);
    }
}

