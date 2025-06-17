package DSA.FileCollection;

import java.util.*;

public class Collection {


    String name;
    List<File> files;
    Set<Collection> collections;
    Integer cachedSize;


    Collection(String name) {

        this.name = name;
        this.files = new ArrayList<>();
        this.collections = new HashSet<>();
    }


    public void addFile(File file) {

        this.files.add(file);
        this.cachedSize = null;
    }


    public void addChildCollection(Collection collection) {
        this.collections.add(collection);
        this.cachedSize = null;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Collection that = (Collection) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
