package PostKarat06June.EmployeeDirectory.LevelUp2;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class GroupHierarchyManager {


    Map<String, Group> groupMap = new ConcurrentHashMap<>();
    ReadWriteLock lock = new ReentrantReadWriteLock();


    public Group getOrCreateGroup(String name) {

        return groupMap.computeIfAbsent(name, Group::new);
    }


    public void addGroup(String name) {
        lock.writeLock().lock();
        try {

            groupMap.putIfAbsent(name, new Group(name));
        } finally {
            lock.writeLock().unlock();
        }

    }


    public void removeGroup(String name) {

        lock.writeLock().lock();
        try {


            Group group = groupMap.get(name);

            if (group != null) {

                for (Group parent : group.getParents()) {

                    parent.children.remove(group);
                }

                for (Group child : new ArrayList<>(group.children)) {

                    child.removeParent(group);
                }
            }
        } finally {
            lock.writeLock().unlock();
        }
    }


    public void addParent(String childName, String parentName) {

        lock.writeLock().lock();
        try {

            Group child = getOrCreateGroup(childName);
            Group parent = getOrCreateGroup(parentName);

            child.addParent(parent);
        } finally {
            lock.writeLock().unlock();
        }
    }


    public void removeParent(String childName, String parentName) {

        lock.writeLock().lock();
        try {

            Group child = getOrCreateGroup(childName);
            Group parent = getOrCreateGroup(parentName);
            if (child != null && parent != null) {
                child.removeParent(parent);
            }
        } finally {
            lock.writeLock().unlock();
        }
    }


    public Group getCommonGroupForEmployees(List<Employee> employees) {

        lock.readLock().lock();
        try {


            if (employees == null || employees.isEmpty()) return null;

            Map<Group, Integer> groupReachCount = new HashMap<>();
            Map<Group, Integer> groupDepth = new HashMap<>();

            for (Employee employee : employees) {

                Set<Group> visited = new HashSet<>();
                Queue<Group> queue = new LinkedList<>(employee.getGroups());


                while (!queue.isEmpty()) {

                    int size = queue.size();
                    int level = 0;

                    while (size-- > 0) {

                        Group current = queue.poll();

                        if (!visited.contains(current)) continue;

                        visited.add(current);
                        groupReachCount.put(current, groupReachCount.getOrDefault(current, 0) + 1);
                        groupDepth.putIfAbsent(current, level);

                        queue.addAll(current.getParents());
                    }

                    level++;
                }
            }

            Group currentGroup = null;
            int maxDepth = -1;

            for (Map.Entry<Group, Integer> entry : groupReachCount.entrySet()) {

                Group group = entry.getKey();
                Integer count = entry.getValue();


                if (count == employees.size()) {

                    int depth = groupDepth.getOrDefault(group, 0);

                    if (depth > maxDepth) {

                        maxDepth = depth;
                        currentGroup = group;
                    }


                }


            }

            return currentGroup;
        } finally {
            lock.readLock().unlock();
        }


    }


    public static void main(String[] args) {



            GroupHierarchyManager manager = new GroupHierarchyManager();

            manager.addGroup("Engineering");
            manager.addGroup("Frontend");
            manager.addGroup("Backend");
            manager.addGroup("Infra");
            manager.addGroup("Security");

            manager.addParent("Frontend", "Engineering");
            manager.addParent("Backend", "Engineering");
            manager.addParent("Security", "Engineering");
            manager.addParent("Infra", "Frontend");
            manager.addParent("Infra", "Backend");

            Group frontend = manager.getOrCreateGroup("Frontend");
            Group backend = manager.getOrCreateGroup("Backend");
            Group infra = manager.getOrCreateGroup("Infra");

            Employee e1 = new Employee("e1", Arrays.asList(frontend));
            Employee e2 = new Employee("e2", Arrays.asList(backend));
            Employee e3 = new Employee("e3", Arrays.asList(infra));

            Group lca = manager.getCommonGroupForEmployees(Arrays.asList(e1, e2, e3));

            if (lca != null) {
                System.out.println("Closest common group: " + lca.getName());
            } else {
                System.out.println("No common group found.");
            }
        }
    }



