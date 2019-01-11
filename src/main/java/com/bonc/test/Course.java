package com.bonc.test;

import java.io.Serializable;

/**
 * @Author : xuelc
 * @Description:
 * @Date : Created in 14:41 2018/12/25.
 * @Modified By:
 */
public class Course implements Serializable {

    private int cId;
    private String  cName;
    private Double  cScore;

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public Double getcScore() {
        return cScore;
    }

    public void setcScore(Double cScore) {
        this.cScore = cScore;
    }
}
