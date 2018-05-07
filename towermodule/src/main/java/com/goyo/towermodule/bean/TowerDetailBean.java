package com.goyo.towermodule.bean;

import java.util.List;

/**
 * Author: wangshuang
 * Time: 2017/8/31
 * Email:xiaoshuang990@sina.com
 */

public class TowerDetailBean {


    /**
     * code : 1
     * msg :
     * data : {"firstTowerMap":{"armFor":50,"armBak":15,"rootHeight":50,"headHeight":8,"limitWeight":8,"limitWind":null,"weight":0,"height":29.1,"radius":53.7,"torquePercent":0,"angle":315.6,"wind":0,"hitAlarm":"正常","angleAlarm":"正常"},"radius":65.2,"list":[{"id":7329,"craneNo":"710068","angle":315.6,"name":"710068","x":"618","y":"168"},{"id":7330,"craneNo":"710067","angle":120.8,"name":"710067","x":"276","y":"343"},{"id":7331,"craneNo":"710070","angle":184.7,"name":"710070","x":"1113","y":"214"},{"id":7332,"craneNo":"710069","angle":359.8,"name":"710069","x":"1208","y":"556"},{"id":7333,"craneNo":"710066","angle":105,"name":"710066","x":"657","y":"565"},{"id":7334,"craneNo":"710065","angle":312.6,"name":"710065","x":"500","y":"600"}]}
     */

    private String code;
    private String msg;
    private DataBean data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * firstTowerMap : {"armFor":50,"armBak":15,"rootHeight":50,"headHeight":8,"limitWeight":8,"limitWind":null,"weight":0,"height":29.1,"radius":53.7,"torquePercent":0,"angle":315.6,"wind":0,"hitAlarm":"正常","angleAlarm":"正常"}
         * radius : 65.2
         * list : [{"id":7329,"craneNo":"710068","angle":315.6,"name":"710068","x":"618","y":"168"},{"id":7330,"craneNo":"710067","angle":120.8,"name":"710067","x":"276","y":"343"},{"id":7331,"craneNo":"710070","angle":184.7,"name":"710070","x":"1113","y":"214"},{"id":7332,"craneNo":"710069","angle":359.8,"name":"710069","x":"1208","y":"556"},{"id":7333,"craneNo":"710066","angle":105,"name":"710066","x":"657","y":"565"},{"id":7334,"craneNo":"710065","angle":312.6,"name":"710065","x":"500","y":"600"}]
         */

        private FirstTowerMapBean firstTowerMap;
        private PhototPathBean phototPath;
        private String radius;
        private List<ListBean> list;

        public FirstTowerMapBean getFirstTowerMap() {
            return firstTowerMap;
        }

        public PhototPathBean getPhototPath() {
            return phototPath;
        }

        public void setPhototPath(PhototPathBean phototPath) {
            this.phototPath = phototPath;
        }

        public void setFirstTowerMap(FirstTowerMapBean firstTowerMap) {
            this.firstTowerMap = firstTowerMap;
        }

        public String getRadius() {
            return radius;
        }

        public void setRadius(String radius) {
            this.radius = radius;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class FirstTowerMapBean {
            /**
             * armFor : 50
             * armBak : 15
             * rootHeight : 50
             * headHeight : 8
             * limitWeight : 8
             * limitWind : null
             * weight : 0
             * height : 29.1
             * radius : 53.7
             * torquePercent : 0
             * angle : 315.6
             * wind : 0
             * hitAlarm : 正常
             * angleAlarm : 正常
             */

            private String armFor;
            private String armBak;
            private String rootHeight;
            private String headHeight;
            private String limitWeight;
            private String limitWind;
            private String weight;
            private String height;
            private String radius;
            private String torquePercent;
            private String angle;
            private String wind;
            private String hitAlarm;
            private String angleAlarm;
            private String videoId;
            private String stream;
            private String url;
            private String isStreaming;
            private String PublishTime;


            public String getArmFor() {
                return armFor;
            }

            public void setArmFor(String armFor) {
                this.armFor = armFor;
            }

            public String getArmBak() {
                return armBak;
            }

            public void setArmBak(String armBak) {
                this.armBak = armBak;
            }

            public String getRootHeight() {
                return rootHeight;
            }

            public void setRootHeight(String rootHeight) {
                this.rootHeight = rootHeight;
            }

            public String getHeadHeight() {
                return headHeight;
            }

            public void setHeadHeight(String headHeight) {
                this.headHeight = headHeight;
            }

            public String getLimitWeight() {
                return limitWeight;
            }

            public void setLimitWeight(String limitWeight) {
                this.limitWeight = limitWeight;
            }

            public String getLimitWind() {
                return limitWind;
            }

            public void setLimitWind(String limitWind) {
                this.limitWind = limitWind;
            }

            public String getWeight() {
                return weight;
            }

            public void setWeight(String weight) {
                this.weight = weight;
            }

            public String getHeight() {
                return height;
            }

            public void setHeight(String height) {
                this.height = height;
            }

            public String getRadius() {
                return radius;
            }

            public void setRadius(String radius) {
                this.radius = radius;
            }

            public String getTorquePercent() {
                return torquePercent;
            }

            public void setTorquePercent(String torquePercent) {
                this.torquePercent = torquePercent;
            }

            public String getAngle() {
                return angle;
            }

            public void setAngle(String angle) {
                this.angle = angle;
            }

            public String getWind() {
                return wind;
            }

            public void setWind(String wind) {
                this.wind = wind;
            }

            public String getHitAlarm() {
                return hitAlarm;
            }

            public void setHitAlarm(String hitAlarm) {
                this.hitAlarm = hitAlarm;
            }

            public String getAngleAlarm() {
                return angleAlarm;
            }

            public void setAngleAlarm(String angleAlarm) {
                this.angleAlarm = angleAlarm;
            }

            public String getVideoId() {
                return videoId;
            }

            public void setVideoId(String videoId) {
                this.videoId = videoId;
            }

            public String getStream() {
                return stream;
            }

            public void setStream(String stream) {
                this.stream = stream;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getIsStreaming() {
                return isStreaming;
            }

            public void setIsStreaming(String isStreaming) {
                this.isStreaming = isStreaming;
            }

            public String getPublishTime() {
                return PublishTime;
            }

            public void setPublishTime(String publishTime) {
                PublishTime = publishTime;
            }
        }
        public static  class PhototPathBean{
            private String length;
            private String width;
            private String URL;

            public String getLength() {
                return length;
            }

            public void setLength(String length) {
                this.length = length;
            }

            public String getWidth() {
                return width;
            }

            public void setWidth(String width) {
                this.width = width;
            }

            public String getURL() {
                return URL;
            }

            public void setURL(String URL) {
                this.URL = URL;
            }
        }

        public static class ListBean {
            /**
             * id : 7329
             * craneNo : 710068
             * angle : 315.6
             * name : 710068
             * x : 618
             * y : 168
             */

            private String id;
            private String craneNo;
            private String angle;
            private String name;
            private String x;
            private String y;
            private String  armFor;
            private String  onlineTime;

            public String getArmFor() {
                return armFor;
            }

            public void setArmFor(String armFor) {
                this.armFor = armFor;
            }

            public String getOnlineTime() {
                return onlineTime;
            }

            public void setOnlineTime(String onlineTime) {
                this.onlineTime = onlineTime;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getCraneNo() {
                return craneNo;
            }

            public void setCraneNo(String craneNo) {
                this.craneNo = craneNo;
            }

            public String getAngle() {
                return angle;
            }

            public void setAngle(String angle) {
                this.angle = angle;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getX() {
                return x;
            }

            public void setX(String x) {
                this.x = x;
            }

            public String getY() {
                return y;
            }

            public void setY(String y) {
                this.y = y;
            }
        }
    }
}
