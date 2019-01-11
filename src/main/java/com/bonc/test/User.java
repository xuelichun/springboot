package com.bonc.test;

import java.io.Serializable;
import java.util.List;

/**
 * @Author : xuelc
 * @Description:
 * @Date : Created in 10:44 2018/12/15.
 * @Modified By:
 */
public class User implements Serializable{

    private int id;

    private List<Course> courseList;

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
