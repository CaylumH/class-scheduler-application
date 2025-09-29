package Server_22356363_22344977;

import Client_22356363_22344977.Booking;

import java.util.ArrayList;

public class BookingController {
    private static ArrayList<Booking> schedule = new ArrayList<>();
    public static synchronized String addClass(String code, String day, String time, String room) {
        if (schedule.isEmpty()) {
            schedule.add(new Booking(code, day, time, room));
            return "Success : Class Added";
        }
        Booking booking = new Booking(code, day, time, room);
        for (int i = 0; i < schedule.size(); i++) {
            if ((booking.getCode().equals(schedule.get(i).getCode())) && (booking.getDay().equals(schedule.get(i).getDay())) && (booking.getTime().equals(schedule.get(i).getTime())) && (booking.getRoom().equals(schedule.get(i).getRoom()))) {
                //if the day of the booking is equal to the day of any bookings in schedule AND if the time of the booking is equal to the time of any bookings in schedule AND if the room of the booking is equal to the room of any bookings in schedule
                return "Failed : Failed to Add Class";
            }

        }
        schedule.add(new Booking(code, day, time, room));
        return "Success : Class Added";
    }

    public static synchronized String removeClass(String code, String day, String time, String room) {
        Booking bookingToRemove = null;
        for (Booking booking : schedule) {
            if (booking.getCode().equals(code) && booking.getDay().equals(day) && booking.getTime().equals(time) && booking.getRoom().equals(room)) {
                bookingToRemove = booking;
                break;
            }
        }
        if (bookingToRemove != null) {
            schedule.remove(bookingToRemove);
            return "Success : Class Removed: " + bookingToRemove;

        } else {

            return "Failure : Selected Class Not Found";
        }
    }

    public static synchronized String displaySchedule(String code) {
        String timetable = "";
        if (schedule.isEmpty()) {

            return "Failure : No Classes Scheduled";
        }
        for (int i = 0; i < schedule.size(); i++) {
            Booking booking = schedule.get(i);
            if (booking.getCode().equals(code)) {
                timetable = timetable + booking.toString() + "/";
            }
        }
        return "Success : Schedule Displayed : " + timetable;
    }

    public static synchronized ArrayList<Booking> returnSchedule(){
return schedule;

    }

}
