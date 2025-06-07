package PostKarat06June.PopularContent;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

public class AllOne {


    private static class Node {

        int count;
        LinkedHashSet<String> keys;
        Node prev, next;

        Node(int count) {
            this.count = count;
            this.keys = new LinkedHashSet<>();
        }
    }


    Map<String, Node> keyCountMap;
    Node head, tail;

    AllOne() {

        this.keyCountMap = new HashMap<>();
        head = new Node(0);
        tail = new Node(0);
        head.next = tail;
        tail.prev = head;
    }


    public Node addNodeBefore(Node prevNode, int count) {

        Node newNode = new Node(count);
        newNode.next = prevNode.next;
        newNode.prev = prevNode;
        prevNode.next.prev = newNode;
        prevNode.next = newNode;

        return newNode;
    }


    public void remove(Node node) {

        node.prev.next = node.next;
        node.next.prev = node.prev;
    }


    public void inc(String key) {

        if (!keyCountMap.containsKey(key)) {

            if (head.next == tail || head.next.count != 1) {

                addNodeBefore(head, 1);
            }

            head.next.keys.add(key);
            keyCountMap.put(key, head.next);
        } else {


            Node currNode = keyCountMap.get(key);
            int currCount = currNode.count;

            if (currNode.next == tail || currNode.next.count != currCount + 1) {

                addNodeBefore(currNode, currCount + 1);
            }

            currNode.next.keys.add(key);
            keyCountMap.put(key, currNode.next);

            currNode.keys.remove(key);

            if (currNode.keys.isEmpty()) {
                remove(currNode);
            }
        }
    }


    public void dec(String key) {

        if (!keyCountMap.containsKey(key)) return;


        Node currNode = keyCountMap.get(key);
        int currCount = currNode.count;


        currNode.keys.remove(key);

        if (currCount == 1) {

            keyCountMap.remove(key);
        } else {

            if (currNode.prev == head || currNode.prev.count != currCount - 1) {

                addNodeBefore(currNode.prev, currCount - 1);
            }

            currNode.prev.keys.add(key);
            keyCountMap.put(key, currNode.prev);
        }

        if (currNode.keys.isEmpty()) {

            remove(currNode);
        }
    }


    public String getMaxKey() {

        return tail.prev == head ? "" : tail.prev.keys.iterator().next();
    }


    public String getMinKey() {

        return head.next == tail ? "" : head.next.keys.iterator().next();
    }


    public static void main(String[] args) {


        AllOne allOne = new AllOne();

        allOne.inc("hello");
        allOne.inc("hello");
        System.out.println(allOne.getMaxKey());
        System.out.println(allOne.getMinKey());
        allOne.inc("leet");
        System.out.println(allOne.getMaxKey());
        System.out.println(allOne.getMinKey());


    }

}
