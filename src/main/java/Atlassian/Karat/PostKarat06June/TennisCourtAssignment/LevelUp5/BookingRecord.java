package PostKarat06June.TennisCourtAssignment.LevelUp5;

public class BookingRecord {


    int startTime;
    int endTime;

    public BookingRecord(int startTime, int endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }


    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }
}
