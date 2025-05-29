

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FriendCycle {


    public static void friendCycle(String[] employees, String[] friends) {

        Map<String, List<String>> adjacencyList = new HashMap<>();


        for (String emp : employees) {

            String[] empDetail = emp.split(", ");

            String empId = empDetail[0];

            adjacencyList.put(empId, new ArrayList<>());
        }


        for (String friend : friends) {

            String[] empl = friend.split(", ");
            String emp1 = empl[0];
            String emp2 = empl[1];

            adjacencyList.get(emp1).add(emp2);
            adjacencyList.get(emp2).add(emp1);
        }


        for (Map.Entry<String, List<String>> entry : adjacencyList.entrySet()) {

            String empId = entry.getKey();
            List<String> fr = entry.getValue();
            if (fr.isEmpty()) {
                System.out.println(empId + ": None");
            } else {

                System.out.println(empId + ": " + String.join(", ", fr));
            }
        }

    }


    public static void main(String[] args) {


        String[] employees = {"1, Bill, Engineer", "2, Joe, HR", "3, Sally, Engineer", "4, Richard, Business", "6, Tom, Engineer"};


        String[] friendships = {"1, 2", "1, 3", "3, 4"};


        friendCycle(employees, friendships);


    }
}