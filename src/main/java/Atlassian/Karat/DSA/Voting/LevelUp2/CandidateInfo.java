package DSA.Voting.LevelUp2;

public class CandidateInfo {


    int points;
    int firstAppearenceOrder;
    int firstPlacedVotes;

    public CandidateInfo(int points, int firstAppearenceOrder) {
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
