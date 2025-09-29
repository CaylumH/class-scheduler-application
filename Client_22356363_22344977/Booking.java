package Client_22356363_22344977;

public class Booking {
    private String code;
    private String day;
    private String time;
    private String room;

    public Booking(String code, String day, String time, String room) {
        this.code = code;
        this.day = day;
        this.time = time;
        this.room = room;
    }

    public Booking(String code, String day) {
        this.day = day;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getRoom() {
        return this.room;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Booking : " +
                "code='" + code + '\'' +
                ", day='" + day + '\'' +
                ", time='" + time + '\'' +
                ", room='" + room + '\'';
    }
}
