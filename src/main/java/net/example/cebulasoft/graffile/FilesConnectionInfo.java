package net.example.cebulasoft.graffile;

import lombok.ToString;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Zamiana funkcjonalności klas File[s]ConnectionInfo <=> FileInfo. ???
 * Class wrapping java.util.HashMap.
 */
//TODO: Consider usage of this class.
@ToString
class FilesConnectionInfo {

    private final Map<String, FileInfo> fileInfoMap = new HashMap<>();

    void put(String key, FileInfo info) {
        fileInfoMap.putIfAbsent(key, info);
    }

    FileInfo get(String className) {
        return fileInfoMap.get(className);
    }

    Set<String> getKeySet() {
        return fileInfoMap.keySet();
    }
}
