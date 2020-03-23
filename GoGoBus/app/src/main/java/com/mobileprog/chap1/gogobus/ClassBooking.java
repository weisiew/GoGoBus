package com.mobileprog.chap1.gogobus;

public class ClassBooking {

    private String BookDate;
    private String DepartDate;
    private String bookingNum;
    private Integer time1, time2, time3, time4;

    public ClassBooking() {
    }

    public ClassBooking(String bookdate, String departdate, Integer time1, Integer time2, Integer time3, Integer time4, String bookingNumber) {
        this.BookDate = bookdate;
        this.DepartDate = departdate;
        this.bookingNum = bookingNum;
        this.time1 = time1;
        this.time2 = time2;
        this.time3 = time3;
        this.time4 = time4;
    }

    public String getBookDate() {
        return BookDate;
    }

    public void setCheckindate(String bookdate) {
        this.BookDate = bookdate;
    }

    public String getDepartDate() {
        return DepartDate;
    }

    public void setCheckoutdate(String departdate) {
        this.DepartDate = departdate;
    }

    public String getBookingNum() {
        return bookingNum;
    }

    public void setBookingNum(String bookingNum) {
        this.bookingNum = bookingNum;
    }

    public Integer getTime1() {
        return time1;
    }

    public void setTime1(Integer time1) {
        this.time1 = time1;
    }

    public Integer getTime2() {
        return time2;
    }

    public void setTime2(Integer time2) {
        this.time2 = time2;
    }

    public Integer getTime3() {
        return time3;
    }

    public void setTime3(Integer time3) {
        this.time3 = time3;
    }

    public Integer getTime4() {
        return time4;
    }

    public void setTime4(Integer time4) {
        this.time4 = time4;
    }
}