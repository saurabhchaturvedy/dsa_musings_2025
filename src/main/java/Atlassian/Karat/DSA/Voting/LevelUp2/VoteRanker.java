package DSA.Voting.LevelUp2;

import java.util.*;

public class VoteRanker {


    public List<String> getRankedCandidates(List<Ballot> ballots, TieBreakingStrategy tieBreakingStrategy) {


        Map<String, CandidateInfo> candidateInfoMap = new HashMap<>();
        int firstAppearenceOrder = 0;


        for (Ballot ballot : ballots) {

            List<String> candidates = ballot.getRankedCandidates();


            for (int i = 0; i < candidates.size(); i++) {


                String name = candidates.get(i);
                int points = 0;
                if (i == 0) points = 3;
                if (i == 1) points = 2;
                if (i == 2) points = 1;

                if (!candidateInfoMap.containsKey(name)) {

                    candidateInfoMap.put(name, new CandidateInfo(0, ++firstAppearenceOrder));
                }


                CandidateInfo candidateInfo = candidateInfoMap.get(name);

                candidateInfo.points += points;

                if (i == 0) {

                    candidateInfo.firstPlacedVotes++;
                }
            }
        }


        ArrayList<Map.Entry<String, CandidateInfo>> entries = new ArrayList<>(candidateInfoMap.entrySet());


        entries.sort((a, b) -> {


            int pointsCompare = Integer.compare(b.getValue().points, a.getValue().points);
            if (pointsCompare != 0) {

                return pointsCompare;
            }

            if (tieBreakingStrategy == TieBreakingStrategy.FIRST_TO_REACH) {

                return Integer.compare(a.getValue().firstAppearenceOrder, b.getValue().firstAppearenceOrder);
            } else if (tieBreakingStrategy == TieBreakingStrategy.FIRST_PLACE_VOTES) {

                return Integer.compare(b.getValue().firstPlacedVotes, a.getValue().firstPlacedVotes);
            }

            return 0;
        });


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

        VoteRanker vs = new VoteRanker();

        System.out.println("TieBreaker: FIRST_OCCURRENCE");
        System.out.println(vs.getRankedCandidates(Arrays.asList(b1, b2, b3), TieBreakingStrategy.FIRST_TO_REACH));

        System.out.println("TieBreaker: MOST_FIRST_PLACE");
        System.out.println(vs.getRankedCandidates(Arrays.asList(b1, b2, b3), TieBreakingStrategy.FIRST_PLACE_VOTES));
    }
}
