package PostKarat06June.TennisCourtAssignment.LevelUp1;

public class BookingRecord {


    int id;

    int startTime;
    int finishTime;

    public BookingRecord(int finishTime, int startTime, int id) {
        this.finishTime = finishTime;
        this.startTime = startTime;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(int finishTime) {
        this.finishTime = finishTime;
    }
}
