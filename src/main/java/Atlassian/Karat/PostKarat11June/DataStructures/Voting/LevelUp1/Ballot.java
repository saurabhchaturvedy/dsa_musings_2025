package PostKarat11June.DataStructures.Voting.LevelUp1;

import java.util.List;

public class Ballot {


    List<String> rankedCandidates;


    Ballot(List<String> rankedCandidates) {

        this.rankedCandidates = rankedCandidates;
    }


    public List<String> getRankedCandidates() {
        return rankedCandidates;
    }
}
