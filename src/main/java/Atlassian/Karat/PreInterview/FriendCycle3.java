package PreInterview;

import java.util.*;

public class FriendCycle3 {


    public static void friendCycle3(String[] employees, String[] friendships) {


        Set<String> allEmployees = new HashSet<>();
        Map<String, List<String>> adjacencyList = new HashMap<>();


        for (String employee : employees) {


            String[] empRecord = employee.split(", ");

            String empId = empRecord[0];

            adjacencyList.put(empId, new ArrayList<>());
            allEmployees.add(empId);
        }


        for (String friendship : friendships) {


            String[] record = friendship.split(", ");

            String emp1 = record[0];
            String emp2 = record[1];

            adjacencyList.get(emp1).add(emp2);
            adjacencyList.get(emp2).add(emp1);
        }


        String startEmployee = allEmployees.iterator().next();
        Set<String> visited = new HashSet<>();

        dfs(startEmployee, adjacencyList, visited);


        if(visited.size()==allEmployees.size())
        {

            System.out.println(" friends are in a cycle");
        }
        else {

            System.out.println(" friends are not in a cycle");
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


        // same cycle input

        String[] employees2 = {
                "1, Bill, Engineer",
                "2, Joe, HR",
                "3, Sally, Engineer",
                "4, Richard, Business",
                "5, Alice, HR"
        };

        String[] friendships2 = {
                "1, 2",  // 1 connected to 2
                "2, 3",  // 2 connected to 3
                "3, 4",  // 3 connected to 4
                "4, 5"   // 4 connected to 5
        };

        friendCycle3(employees2, friendships2);


    }
}
