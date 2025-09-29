package Client_22356363_22344977;
public class TimeDayFormat {

    public static int getRow(String time) {
        int row = 0;
        switch (time) {
            case " time='9:00'":
                row = 1;
                break;
            case " time='10:00'":
                row = 2;
                break;
            case " time='11:00'":
                row = 3;
                break;
            case " time='12:00'":
                row = 4;
                break;
            case " time='13:00'":
                row = 5;
                break;
            case " time='14:00'":
                row = 6;
                break;
            case " time='15:00'":
                row = 7;
                break;
            case " time='16:00'":
                row = 8;
                break;
            case " time='17:00'":
                row = 9;
                break;
            case " time='18:00'":
                row = 10;
                break;
        }
        return row;
    }
    public static int getColumn(String day) {
        int column = 0;
        switch (day) {
            case " day='Mon'":
                column = 1;
                break;
            case " day='Tues'":
                column = 2;
                break;
            case " day='Wed'":
                column = 3;
                break;
            case " day='Thur'":
                column = 4;
                break;
            case " day='Fri'":
                column = 5;
                break;
        }
        return column;
    }
}
