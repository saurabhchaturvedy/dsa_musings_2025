package PostKarat11June.DataStructures.Voting.LevelUp2;

import java.util.*;

public class VotingSystem {


    public List<String> getResults(List<Ballot> ballots, TieBreakingStrategy tieBreakingStrategy) {

        Map<String, CandidateInfo> candidateToInfoMap = new LinkedHashMap<>();
        int appearenceOrder = 0;

        for (Ballot ballot : ballots) {

            List<String> candidates = ballot.getRankedCandidates();
            for (int i = 0; i < candidates.size(); i++) {

                String candidate = candidates.get(i);
                int points = 0;
                if (i == 0) points = 3;
                else if (i == 1) points = 2;
                else if (i == 2) points = 1;

                if (!candidateToInfoMap.containsKey(candidate)) {

                    candidateToInfoMap.put(candidate, new CandidateInfo(0, appearenceOrder++));
                }

                CandidateInfo candidateInfo = candidateToInfoMap.get(candidate);

                candidateInfo.points += points;
                if (i == 0) {
                    candidateInfo.firstPlacedVotes++;
                }
            }
        }


        List<Map.Entry<String, CandidateInfo>> entries = new ArrayList<>(candidateToInfoMap.entrySet());

        entries.sort((a, b) -> {

            int pointCompare = Integer.compare(b.getValue().points, a.getValue().points);

            if (pointCompare != 0) {
                return pointCompare;
            }
            if (tieBreakingStrategy == TieBreakingStrategy.FIRST_TO_REACH) {
                return Integer.compare(a.getValue().firstAppearenceOrder, b.getValue().firstAppearenceOrder);
            } else if (tieBreakingStrategy == TieBreakingStrategy.FIRST_PLACE_VOTES) {

                return Integer.compare(b.getValue().firstPlacedVotes, a.getValue().firstPlacedVotes);
            }

            return 0;
        });

        System.out.println(entries);

        List<String> result = new ArrayList<>();
        for (Map.Entry<String, CandidateInfo> entry : entries) {

            result.add(entry.getKey());
        }

        return result;

    }


    public static void main(String[] args) {


        Ballot b1 = new Ballot(Arrays.asList("Alice", "Bob", "Charlie"));
        Ballot b2 = new Ballot(Arrays.asList("Bob", "Alice", "Charlie"));
        Ballot b3 = new Ballot(Arrays.asList("Charlie", "Alice", "Bob"));

        VotingSystem vs = new VotingSystem();

        System.out.println("TieBreaker: FIRST_OCCURRENCE");
        System.out.println(vs.getResults(Arrays.asList(b1, b2, b3), TieBreakingStrategy.FIRST_TO_REACH));

        System.out.println("TieBreaker: MOST_FIRST_PLACE");
        System.out.println(vs.getResults(Arrays.asList(b1, b2, b3), TieBreakingStrategy.FIRST_PLACE_VOTES));



    }
}
