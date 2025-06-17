package DSA.Voting.LevelUp1;

import java.util.List;

public class Ballot {


    List<String> rankedCandidates;

    public Ballot(List<String> rankedCandidates) {
        this.rankedCandidates = rankedCandidates;
    }


    public List<String> getRankedCandidates() {
        return rankedCandidates;
    }
}
