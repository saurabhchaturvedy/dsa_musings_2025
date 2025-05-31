package PreInterview;

import java.util.*;

public class TaskByLevel {


    public static List<List<String>> taskByLevel(String[][] dependencies) {


        Map<String, List<String>> adjacencyList = new HashMap<>();
        Map<String, Integer> indegree = new HashMap<>();


        for (String[] dep : dependencies) {


            String from = dep[0];
            String to = dep[1];

            adjacencyList.computeIfAbsent(from, k -> new ArrayList<>()).add(to);
            indegree.put(to, indegree.getOrDefault(to, 0) + 1);
            indegree.putIfAbsent(from, 0);
        }


        Queue<String> queue = new LinkedList<>();

        List<List<String>> result = new ArrayList<>();


        for (String task : indegree.keySet()) {

            if (indegree.get(task) == 0) {

                queue.offer(task);
            }
        }


        while (!queue.isEmpty()) {

            int size = queue.size();
            List<String> level = new ArrayList<>();


            for (int i = 0; i < size; i++) {


                String currentTask = queue.poll();

                level.add(currentTask);


                for (String neighbour : adjacencyList.getOrDefault(currentTask, new ArrayList<>())) {
                    indegree.put(neighbour, indegree.get(neighbour) - 1);

                    if (indegree.get(neighbour) == 0) {

                        queue.offer(neighbour);
                    }

                }
            }


            result.add(level);
        }

        return result;

    }


    public static void main(String[] args) {





        String[][] input = {
                {"cook", "eat"},
                {"study", "eat"},
                {"sleep", "study"}
        };

        List<List<String>> output = taskByLevel(input);

        for (List<String> level : output) {
            System.out.println(level);
        }

    }

    }

