package com.cmms.codetech.startclasseasy.model;

/**
 * Created by daryl on 26/10/2015.
 */
public class Course {

    String courseName;
    String courseTrainer;
    String courseStatus;
    Long rowID;

    public Course(String courseName, String courseTrainer, String courseStatus, Long rowID) {
        super();
        this.courseName = courseName;
        this.courseTrainer = courseTrainer;
        this.courseStatus = courseStatus;
        this.rowID = rowID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseStatus() {
        return courseStatus;
    }

    public void setCourseStatus(String courseStatus) {
        this.courseStatus = courseStatus;
    }

    public String getCourseTrainer() {
        return courseTrainer;
    }

    public void setCourseTrainer(String courseTrainer) {
        this.courseTrainer = courseTrainer;
    }

    public Long getRowID() {
        return rowID;
    }

    public void setRowID(Long rowID) {
        this.rowID = rowID;
    }
}
