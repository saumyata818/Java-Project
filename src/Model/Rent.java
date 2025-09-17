package Model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Rent {
    private int ID;
    private User user;
    private Car car;
    private LocalDateTime dateTime = LocalDateTime.now();
    private int hours;
    private double total;
    private int status;

    // Display formatter
    private DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public int getID() { return this.ID; }
    public void setID(int ID) { this.ID = ID; }
    public User getUser() { return this.user; }
    public void setUser(User user) { this.user = user; }
    public Car getCar() { return this.car; }
    public void setCar(Car car) { this.car = car; }

    // Return formatted string for JTable
    public String getDateTime() {
        return this.dateTime.format(displayFormatter);
    }

    public LocalDateTime getLocalDateTime() {
        return this.dateTime;
    }

    // Correctly parse date-only DB value
    public void setDateTime(String dateTimeString) {
        try {
            LocalDate date = LocalDate.parse(dateTimeString);  // "yyyy-MM-dd"
            this.dateTime = date.atStartOfDay();               // convert to LocalDateTime 00:00
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getHours() { return this.hours; }
    public void setHours(int hours) { this.hours = hours; }
    public double getTotal() { return this.total; }
    public void setTotal(double total) { this.total = total; }
    public int getStatus() { return this.status; }
    public void setStatus(int status) { this.status = status; }

    public String getStatusToString() {
        long passedHours = ChronoUnit.HOURS.between(this.dateTime, LocalDateTime.now());
        if (this.getStatus() == 1) return "Returned";
        else if (passedHours < this.getHours()) return "Estimated";
        else return "Delayed";
    }

    public long getDelayedHours() {
        long passedHours = ChronoUnit.HOURS.between(this.dateTime, LocalDateTime.now());
        return passedHours - this.hours;
    }
}
