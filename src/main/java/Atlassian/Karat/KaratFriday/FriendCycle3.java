package KaratFriday;

import java.util.*;

public class FriendCycle3 {


    public static void friendCycle3(String[] employees, String[] friendships) {


        Set<String> allEmployees = new HashSet<>();
        Map<String, List<String>> adjacencyList = new HashMap<>();


        for (String employee : employees) {

            String[] employeeRecord = employee.split(", ");
            String empId = employeeRecord[0];
            adjacencyList.put(empId, new ArrayList<>());
            allEmployees.add(empId);
        }


        for (String friendship : friendships) {

            String[] friendshipRecord = friendship.split(", ");

            String emp1 = friendshipRecord[0];
            String emp2 = friendshipRecord[1];

            adjacencyList.get(emp1).add(emp2);
            adjacencyList.get(emp2).add(emp1);
        }

        Set<String> visited = new HashSet<>();
        String startEmployee = allEmployees.iterator().next();

        dfs(startEmployee, adjacencyList, visited);

        if (visited.size() == allEmployees.size()) {

            System.out.println(" all friends are in same cycle");
        } else {

            System.out.println(" all friends are not in same cycle");
        }
    }

    private static void dfs(String startEmployee, Map<String, List<String>> adjacencyList, Set<String> visited) {

        if (visited.contains(startEmployee)) return;
        visited.add(startEmployee);

        for (String neighbour : adjacencyList.get(startEmployee)) {

            dfs(neighbour, adjacencyList, visited);
        }
    }


    public static void main(String[] args) {


        String[] employees = {
                "1, Bill, Engineer",
                "2, Joe, HR",
                "3, Sally, Engineer",
                "4, Richard, Business",
                "6, Tom, Engineer"
        };

        String[] friendships = {
                "1, 2",
                "1, 3",
                "3, 4"
                // Note: "6" has no friends — isolated
        };

        friendCycle3(employees, friendships);
    }
}

