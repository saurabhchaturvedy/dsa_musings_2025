package PostKarat11June.DataStructures.Voting.LevelUp1;

import java.util.*;

public class VotingSystem {


    public List<String> getResults(List<Ballot> ballots) {

        Map<String, Integer> candidateToPointsMap = new HashMap<>();


        for (Ballot ballot : ballots) {

            List<String> rankedCandidates = ballot.getRankedCandidates();


            for (int i = 0; i < rankedCandidates.size(); i++) {

                String candidate = rankedCandidates.get(i);

                int point = 0;
                if (i == 0) point = 3;
                else if (i == 1) point = 2;
                else if (i == 2) point = 1;

                candidateToPointsMap.put(candidate, candidateToPointsMap.getOrDefault(candidate, 0) + point);
            }
        }


        List<Map.Entry<String, Integer>> entries = new ArrayList<>(candidateToPointsMap.entrySet());
        entries.sort((a, b) -> b.getValue().compareTo(a.getValue()));


        List<String> result = new ArrayList<>();


        System.out.println(entries);

        for (Map.Entry<String, Integer> entry : entries) {

            result.add(entry.getKey());
        }

        return result;

    }


    public static void main(String[] args) {


        Ballot b1 = new Ballot(Arrays.asList("Alice", "Bob", "Charlie"));
        Ballot b2 = new Ballot(Arrays.asList("Bob", "Alice", "Charlie"));
        Ballot b3 = new Ballot(Arrays.asList("Charlie", "Alice", "Bob"));

        VotingSystem vs = new VotingSystem();
        List<String> results = vs.getResults(Arrays.asList(b1, b2, b3));

        System.out.println(results);

    }
}
