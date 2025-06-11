package PostKarat11June.DataStructures.Voting.LevelUp2;

public class CandidateInfo {


    Integer points;
    Integer firstAppearenceOrder;
    Integer firstPlacedVotes;

    public CandidateInfo(Integer points, Integer firstAppearenceOrder) {
        this.points = points;
        this.firstAppearenceOrder = firstAppearenceOrder;
        this.firstPlacedVotes = 0;
    }

    @Override
    public String toString() {
        return "CandidateInfo{" +
                "points=" + points +
                ", firstAppearenceOrder=" + firstAppearenceOrder +
                ", firstPlacedVotes=" + firstPlacedVotes +
                '}';
    }
}


