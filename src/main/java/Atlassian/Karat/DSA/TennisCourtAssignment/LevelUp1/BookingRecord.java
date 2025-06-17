package DSA.TennisCourtAssignment.LevelUp1;

public class BookingRecord {


    Integer id;
    Integer startTime;
    Integer finishTime;

    public BookingRecord(Integer id, Integer finishTime, Integer startTime) {
        this.id = id;
        this.finishTime = finishTime;
        this.startTime = startTime;
    }

    public Integer getId() {
        return id;
    }

    public Integer getFinishTime() {
        return finishTime;
    }

    public Integer getStartTime() {
        return startTime;
    }
}
