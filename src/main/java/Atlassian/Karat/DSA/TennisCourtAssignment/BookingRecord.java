package DSA.TennisCourtAssignment;

public class BookingRecord {


    Integer id;
    Integer startTime;
    Integer finishTime;

    public BookingRecord(Integer id, Integer startTime, Integer finishTime) {
        this.id = id;
        this.startTime = startTime;
        this.finishTime = finishTime;
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
