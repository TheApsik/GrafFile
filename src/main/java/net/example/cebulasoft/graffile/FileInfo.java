package net.example.cebulasoft.graffile;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@RequiredArgsConstructor(staticName = "of")
class FileInfo {
    /**
     * Package
     */
    private final String path;

    /**
     * File name with extension
     */
    private final String name;
    /**
     * Number of references.
     */
    private int references;

    String getName() {
        return name;
    }

    void addReference() {
        this.references++;
    }

}

