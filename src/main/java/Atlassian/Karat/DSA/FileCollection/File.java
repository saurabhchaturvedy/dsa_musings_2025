package DSA.FileCollection;

public class File {


    String name;
    Integer size;


    public File(String name, Integer size) {
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public Integer getSize() {
        return size;
    }
}
