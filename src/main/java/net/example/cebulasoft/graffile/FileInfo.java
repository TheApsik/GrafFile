package net.example.cebulasoft.graffile;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@EqualsAndHashCode
@ToString
@RequiredArgsConstructor(staticName = "of")
@Getter
class FileInfo {
    /**
     * Package
     */
    private final String classPackage;

    private final String path;

    /**
     * File name with extension
     */
    private final String name;
    /**
     * Number of references.
     */
    private Map<String, Integer> references = new HashMap<>();

    String getName() {
        return name;
    }

    void addReference(String file) {
        if (references.containsKey(file)) {
            references.put(file, references.get(file) + 1);
        } else {
            references.put(file, 1);
        }
    }

    public Iterator<Integer> getIteratorToReferences() {
        return references.values().iterator();
    }
}

