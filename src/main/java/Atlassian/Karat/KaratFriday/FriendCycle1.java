package KaratFriday;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FriendCycle1 {


    public static void friendsCycle(String[] employees, String[] friendships) {


        Map<String, List<String>> adjacencyList = new HashMap<>();

        for (String employee : employees) {

            String[] employeeRecord = employee.split(", ");
            String empId = employeeRecord[0];
            adjacencyList.put(empId, new ArrayList<>());
        }

        for (String friendship : friendships) {

            String[] friendshipRecord = friendship.split(", ");
            String emp1 = friendshipRecord[0];
            String emp2 = friendshipRecord[1];

            adjacencyList.get(emp1).add(emp2);
            adjacencyList.get(emp2).add(emp1);
        }


        for (Map.Entry<String, List<String>> entry : adjacencyList.entrySet()) {

            String empId = entry.getKey();
            List<String> friends = entry.getValue();

            if (friends.isEmpty()) {

                System.out.println(empId + " : None");
            } else {

                System.out.println(empId + ":" + friends);
            }
        }
    }


    public static void main(String[] args) {


        String[] employees = {"1, Bill, Engineer", "2, Joe, HR", "3, Sally, Engineer", "4, Richard, Business", "6, Tom, Engineer"};


        String[] friendships = {"1, 2", "1, 3", "3, 4"};


        friendsCycle(employees, friendships);
    }
}
