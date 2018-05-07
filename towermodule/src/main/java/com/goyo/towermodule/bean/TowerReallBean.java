package com.goyo.towermodule.bean;

import java.util.List;

/**
 * Author: wangshuang
 * Time: 2017/9/5
 * Email:xiaoshuang990@sina.com
 */

public class TowerReallBean {


    /**
     * sn : 710069
     * card :
     * wind : 0
     * weight : 0
     * angle : 220.2
     * radius : 29
     * height : 0
     * torque : 0
     * fall : 2
     * alarmType : 00000000000000010000000000000000
     * sensorStatus :
     * rtimes :
     * angleX : 0
     * angleY : 0
     * safeTorque : 66.9
     * safeWeight : 2
     * windLevel : 0
     * limitStatus :
     * warnType :
     * powerStatu : 1
     * datas : [{"sn":"710068","angle":"0"},{"sn":"710067","angle":"180"},{"sn":"710070","angle":"108.1"},{"sn":"710069","angle":"220.2"},{"sn":"710066","angle":"335.5"},{"sn":"710065","angle":"0"}]
     */

    private String sn;
    private String card;
    private String wind;
    private String weight;
    private String angle;
    private String radius;
    private String height;
    private String torque;
    private String fall;
    private String alarmType;
    private String sensorStatus;
    private String rtimes;
    private String angleX;
    private String angleY;
    private String safeTorque;
    private String safeWeight;
    private String windLevel;
    private String limitStatus;
    private String warnType;
    private String powerStatu;
    private List<DatasBean> datas;

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getAngle() {
        return angle;
    }

    public void setAngle(String angle) {
        this.angle = angle;
    }

    public String getRadius() {
        return radius;
    }

    public void setRadius(String radius) {
        this.radius = radius;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getTorque() {
        return torque;
    }

    public void setTorque(String torque) {
        this.torque = torque;
    }

    public String getFall() {
        return fall;
    }

    public void setFall(String fall) {
        this.fall = fall;
    }

    public String getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(String alarmType) {
        this.alarmType = alarmType;
    }

    public String getSensorStatus() {
        return sensorStatus;
    }

    public void setSensorStatus(String sensorStatus) {
        this.sensorStatus = sensorStatus;
    }

    public String getRtimes() {
        return rtimes;
    }

    public void setRtimes(String rtimes) {
        this.rtimes = rtimes;
    }

    public String getAngleX() {
        return angleX;
    }

    public void setAngleX(String angleX) {
        this.angleX = angleX;
    }

    public String getAngleY() {
        return angleY;
    }

    public void setAngleY(String angleY) {
        this.angleY = angleY;
    }

    public String getSafeTorque() {
        return safeTorque;
    }

    public void setSafeTorque(String safeTorque) {
        this.safeTorque = safeTorque;
    }

    public String getSafeWeight() {
        return safeWeight;
    }

    public void setSafeWeight(String safeWeight) {
        this.safeWeight = safeWeight;
    }

    public String getWindLevel() {
        return windLevel;
    }

    public void setWindLevel(String windLevel) {
        this.windLevel = windLevel;
    }

    public String getLimitStatus() {
        return limitStatus;
    }

    public void setLimitStatus(String limitStatus) {
        this.limitStatus = limitStatus;
    }

    public String getWarnType() {
        return warnType;
    }

    public void setWarnType(String warnType) {
        this.warnType = warnType;
    }

    public String getPowerStatu() {
        return powerStatu;
    }

    public void setPowerStatu(String powerStatu) {
        this.powerStatu = powerStatu;
    }

    public List<DatasBean> getDatas() {
        return datas;
    }

    public void setDatas(List<DatasBean> datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        /**
         * sn : 710068
         * angle : 0
         */

        private String sn;
        private String angle;

        public String getSn() {
            return sn;
        }

        public void setSn(String sn) {
            this.sn = sn;
        }

        public String getAngle() {
            return angle;
        }

        public void setAngle(String angle) {
            this.angle = angle;
        }
    }
}
