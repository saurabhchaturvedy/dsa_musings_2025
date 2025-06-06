package PostKarat06June.TennisCourtAssignment.LevelUp3;

public class BookingRecord {


    int id;
    int startTime;
    int finishTime;

    public BookingRecord(int id, int startTime, int finishTime) {
        this.id = id;
        this.startTime = startTime;
        this.finishTime = finishTime;
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

    @Override
    public String toString() {
        return "BookingRecord{" +
                "id=" + id +
                ", startTime=" + startTime +
                ", finishTime=" + finishTime +
                '}';
    }
}
