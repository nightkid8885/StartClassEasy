package com.cmms.codetech.startclasseasy.model;

/**
 * Created by daryl on 24/10/2015.
 */
public class CourseDate {

    String courseDate;
    Long rowID;

    public CourseDate(String courseDate, Long rowID) {
        super();
        this.courseDate = courseDate;
        this.rowID = rowID;
    }

    public String getCourseDate() {

        return courseDate;
    }

    public void setCourseDate(String courseDate) {

        this.courseDate = courseDate;
    }

    public Long getRowID() {
        return rowID;
    }

    public void setRowID(Long rowID) {
        this.rowID = rowID;
    }
}
