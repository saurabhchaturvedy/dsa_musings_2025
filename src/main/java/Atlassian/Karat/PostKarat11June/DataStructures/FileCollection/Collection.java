package PostKarat11June.DataStructures.FileCollection;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Collection {


    String name;
    List<File> files;
    Set<Collection> collections;
    Integer cachedSize;

    Collection(String name) {

        this.name = name;
        this.files = new ArrayList<>();
        this.collections = new HashSet<>();
        this.cachedSize = 0;
    }


    public void addFile(File file) {

        this.files.add(file);
        this.cachedSize = null;
    }


    public void addChildCollection(Collection collection) {

        this.collections.add(collection);
        cachedSize = null;
    }

    public String getName() {
        return name;
    }

    public List<File> getFiles() {
        return files;
    }

    public Set<Collection> getCollections() {
        return collections;
    }

    public int getCachedSize() {
        return cachedSize;
    }

    public Integer getTotalSize() {

        if (cachedSize != null) {
            return cachedSize;
        }

        int totalSize = 0;

        for (File file : files) {

            totalSize += file.getSize();
        }

        for (Collection collection : collections) {

            totalSize += collection.getTotalSize();
        }

        cachedSize = totalSize;
        return totalSize;
    }
}
