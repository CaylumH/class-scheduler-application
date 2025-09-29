package Server_22356363_22344977;

import Client_22356363_22344977.Booking;

import java.util.ArrayList;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class EarlyLectures_ForkJoin extends RecursiveTask<String> {     //Ref: AR, forkjoincomparison, CS4076
    static final int SEQUENTIAL_THRESHOLD = 5;

    int low;
    int high;
    ArrayList<Booking> schedule;
    String day;
    String time;

    EarlyLectures_ForkJoin(ArrayList<Booking> schedule, int low, int high, String day, String time) {
        this.schedule = schedule;
        this.low = low;
        this.high = high;
        this.day = day;
        this.time = time;
    }

    protected String compute() {
        if (high - low <= SEQUENTIAL_THRESHOLD) {
            for (int i = low; i < high; i++) {
                Booking booking = schedule.get(i);
                if (booking.getDay().equals(day) && !booking.getTime().equals("9:00")) {
                    for (int j = 0; j < schedule.size(); j++) {
                        Booking booked = schedule.get(j);
                        if (booked.getDay().equals(day) && booked.getTime().equals("9:00") && booked.getRoom().equals(booking.getRoom())) {
                            return "Failure : Scheduling Conflict";
                        }
                    }
                    booking.setTime("9:00");
                    return "Success : Early Lecture Rescheduled";
                }
            }
            return "Failure : Booking Not Found";
        } else {
            int mid = low + (high - low) / 2;
            EarlyLectures_ForkJoin left = new EarlyLectures_ForkJoin(schedule, low, mid, day, time);
            EarlyLectures_ForkJoin right = new EarlyLectures_ForkJoin(schedule, mid, high, day, time);

            left.fork();
            String rightResult = right.compute();
            String leftResult = left.join();
            return leftResult + rightResult;

        }
    }
    public static synchronized String earlyLecture(ArrayList<Booking> schedule, String code, String day, String time, String room) {
        return ForkJoinPool.commonPool().invoke(new EarlyLectures_ForkJoin(schedule, 0, schedule.size(), day, time));
    }
}



