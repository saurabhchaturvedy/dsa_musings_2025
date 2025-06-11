package PostKarat11June.DataStructures.FileCollection;

import java.util.*;

public class FileCollectionSystem {


    Map<String, File> files;
    Map<String, Collection> collections;
    TreeSet<Collection> sortedCollections;
    int totalSize = 0;


    FileCollectionSystem() {

        this.files = new HashMap<>();
        this.collections = new HashMap<>();
        this.totalSize = 0;
        this.sortedCollections = new TreeSet<>((a, b) -> {


            int sizeCompare = b.getTotalSize() - a.getTotalSize();
            if (sizeCompare != 0) {
                return sizeCompare;
            }

            return a.getName().compareTo(b.getName());
        });
    }


    public void addFile(String name, Integer size, List<String> collections) {


        File file = new File(name, size);
        this.files.put(name, file);
        totalSize += file.getSize();

        if(collections!=null) {

            for (String col : collections) {

                Collection collectionToAddTo = this.collections.computeIfAbsent(col, Collection::new);
                collectionToAddTo.addFile(file);
                resetSortedCollections(collectionToAddTo);
            }
        }
    }


    public void addChildToParent(String childName, String parentName) {

        Collection child = collections.computeIfAbsent(childName, Collection::new);
        Collection parent = collections.computeIfAbsent(parentName, Collection::new);

        parent.addChildCollection(child);
        resetSortedCollections(parent);

    }


    public void resetSortedCollections(Collection collection) {


        sortedCollections.remove(collection);
        collection.getTotalSize();
        sortedCollections.add(collection);

    }


    public List<Collection> topKCollections(int k) {

        List<Collection> topkList = new ArrayList<>();

        for (Collection collection : sortedCollections) {

            if (topkList.size() == k) break;

                topkList.add(collection);

        }

        return topkList;
    }

    public static void main(String[] args) {




        FileCollectionSystem fileCollectionSystem = new FileCollectionSystem();



        fileCollectionSystem.addFile("file1.txt", 100, null);
        fileCollectionSystem.addFile("file2.txt", 200, Arrays.asList("c1"));
        fileCollectionSystem.addFile("file3.txt", 200, Arrays.asList("c1"));
        fileCollectionSystem.addFile("file4.txt", 300, Arrays.asList("c2", "c3"));
        fileCollectionSystem.addFile("file5.txt", 10, null);
        fileCollectionSystem.addFile("file6.txt", 400, Arrays.asList("c4"));
        fileCollectionSystem.addFile("file7.txt", 250, Arrays.asList("c5"));


        fileCollectionSystem.addChildToParent("c2", "c1");
        fileCollectionSystem.addChildToParent("c4", "c5");
        fileCollectionSystem.addChildToParent("c5", "c2");

        List<Collection> collectionList = fileCollectionSystem.topKCollections(3);

        for (Collection collection : collectionList) {

            System.out.println(collection.name + " " + collection.getTotalSize());
        }



    }


}
