package DSA.Voting;

import java.util.*;

public class VoteRanker {


    List<String> getRankedCandidates(List<Ballot> ballots) {


        Map<String, Integer> candidateToPointsMap = new HashMap<>();


        for (Ballot ballot : ballots) {


            List<String> candidates = ballot.getRankedCandidates();


            for (int i = 0; i < candidates.size(); i++) {

                String candidateName = candidates.get(i);
                int points = 0;

                if (i == 0) points = 3;
                if (i == 1) points = 2;
                if (i == 2) points = 1;

                candidateToPointsMap.put(candidateName, candidateToPointsMap.getOrDefault(candidateName, 0) + points);

            }
        }

        List<Map.Entry<String, Integer>> entries = new ArrayList<>(candidateToPointsMap.entrySet());
        entries.sort((a, b) -> b.getValue().compareTo(a.getValue()));


        List<String> result = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : entries) {

            result.add(entry.getKey());
        }

        return result;
    }


    public static void main(String[] args) {

        Ballot b1 = new Ballot(Arrays.asList("Alice", "Bob", "Charlie"));
        Ballot b2 = new Ballot(Arrays.asList("Bob", "Alice", "Charlie"));
        Ballot b3 = new Ballot(Arrays.asList("Charlie", "Alice", "Bob"));

        VoteRanker vs = new VoteRanker();
        List<String> results = vs.getRankedCandidates(Arrays.asList(b1, b2, b3));

        System.out.println(results);
    }
}
