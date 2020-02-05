package info.semicolen.resturentapplicationtask.Models;

public class Tables {

    String TableName;
    int TotalSeats;
    int TotalSeatsFree;
    int NoOfBookings;
    boolean Reserved;

    public Tables(String tableName, int totalSeats, int totalSeatsFree, int noOfBookings, boolean reserved) {
        TableName = tableName;
        TotalSeats = totalSeats;
        TotalSeatsFree = totalSeatsFree;
        NoOfBookings = noOfBookings;
        Reserved = reserved;
    }

    public int getTotalSeatsFree() {
        return TotalSeatsFree;
    }

    public void setTotalSeatsFree(int totalSeatsFree) {
        TotalSeatsFree = totalSeatsFree;
    }

    public String getTableName() {
        return TableName;
    }

    public void setTableName(String tableName) {
        TableName = tableName;
    }

    public int getTotalSeats() {
        return TotalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        TotalSeats = totalSeats;
    }

    public int getNoOfBookings() {
        return NoOfBookings;
    }

    public void setNoOfBookings(int noOfBookings) {
        NoOfBookings = noOfBookings;
    }

    public boolean isReserved() {
        return Reserved;
    }

    public void setReserved(boolean reserved) {
        Reserved = reserved;
    }
}
