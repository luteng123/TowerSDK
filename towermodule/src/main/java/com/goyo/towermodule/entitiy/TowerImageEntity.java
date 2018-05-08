package com.goyo.towermodule.entitiy;

import java.util.List;

/**
 * Created by JarvisLau on 2018/5/8.
 * Description :
 */

public class TowerImageEntity {


    /**
     * code : 1
     * msg :
     * data : {"addressList":[{"id":7870,"craneNo":"800100","angle":138.6,"name":"1#","equipmentTime":null,"x":650,"y":526,"armFor":56,"onlineTime":"离线"}],"systemTime":"2018-05-08 11:00:43","listToday":[{"countArm":0,"countHistory":0,"name":"1#","craneNoName":"800100","conName":"--","sort":1,"onlineTime":"离线"}],"phototPath":{"length":"800","width":"500","URL":"https://img.igongdi.cn/1510137844822.png","onLine":0,"offLine":1}}
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
         * addressList : [{"id":7870,"craneNo":"800100","angle":138.6,"name":"1#","equipmentTime":null,"x":650,"y":526,"armFor":56,"onlineTime":"离线"}]
         * systemTime : 2018-05-08 11:00:43
         * listToday : [{"countArm":0,"countHistory":0,"name":"1#","craneNoName":"800100","conName":"--","sort":1,"onlineTime":"离线"}]
         * phototPath : {"length":"800","width":"500","URL":"https://img.igongdi.cn/1510137844822.png","onLine":0,"offLine":1}
         */

        private String systemTime;
        private PhototPathBean phototPath;
        private List<AddressListBean> addressList;
        private List<ListTodayBean> listToday;

        public String getSystemTime() {
            return systemTime;
        }

        public void setSystemTime(String systemTime) {
            this.systemTime = systemTime;
        }

        public PhototPathBean getPhototPath() {
            return phototPath;
        }

        public void setPhototPath(PhototPathBean phototPath) {
            this.phototPath = phototPath;
        }

        public List<AddressListBean> getAddressList() {
            return addressList;
        }

        public void setAddressList(List<AddressListBean> addressList) {
            this.addressList = addressList;
        }

        public List<ListTodayBean> getListToday() {
            return listToday;
        }

        public void setListToday(List<ListTodayBean> listToday) {
            this.listToday = listToday;
        }

        public static class PhototPathBean {
            /**
             * length : 800
             * width : 500
             * URL : https://img.igongdi.cn/1510137844822.png
             * onLine : 0
             * offLine : 1
             */

            private String length;
            private String width;
            private String URL;
            private int onLine;
            private int offLine;

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

            public int getOnLine() {
                return onLine;
            }

            public void setOnLine(int onLine) {
                this.onLine = onLine;
            }

            public int getOffLine() {
                return offLine;
            }

            public void setOffLine(int offLine) {
                this.offLine = offLine;
            }
        }

        public static class AddressListBean {
            /**
             * id : 7870
             * craneNo : 800100
             * angle : 138.6
             * name : 1#
             * equipmentTime : null
             * x : 650.0
             * y : 526.0
             * armFor : 56.0
             * onlineTime : 离线
             */

            private int id;
            private String craneNo;
            private double angle;
            private String name;
            private Object equipmentTime;
            private double x;
            private double y;
            private double armFor;
            private String onlineTime;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getCraneNo() {
                return craneNo;
            }

            public void setCraneNo(String craneNo) {
                this.craneNo = craneNo;
            }

            public double getAngle() {
                return angle;
            }

            public void setAngle(double angle) {
                this.angle = angle;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public Object getEquipmentTime() {
                return equipmentTime;
            }

            public void setEquipmentTime(Object equipmentTime) {
                this.equipmentTime = equipmentTime;
            }

            public double getX() {
                return x;
            }

            public void setX(double x) {
                this.x = x;
            }

            public double getY() {
                return y;
            }

            public void setY(double y) {
                this.y = y;
            }

            public double getArmFor() {
                return armFor;
            }

            public void setArmFor(double armFor) {
                this.armFor = armFor;
            }

            public String getOnlineTime() {
                return onlineTime;
            }

            public void setOnlineTime(String onlineTime) {
                this.onlineTime = onlineTime;
            }
        }

        public static class ListTodayBean {
            /**
             * countArm : 0
             * countHistory : 0
             * name : 1#
             * craneNoName : 800100
             * conName : --
             * sort : 1
             * onlineTime : 离线
             */

            private int countArm;
            private int countHistory;
            private String name;
            private String craneNoName;
            private String conName;
            private int sort;
            private String onlineTime;

            public int getCountArm() {
                return countArm;
            }

            public void setCountArm(int countArm) {
                this.countArm = countArm;
            }

            public int getCountHistory() {
                return countHistory;
            }

            public void setCountHistory(int countHistory) {
                this.countHistory = countHistory;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getCraneNoName() {
                return craneNoName;
            }

            public void setCraneNoName(String craneNoName) {
                this.craneNoName = craneNoName;
            }

            public String getConName() {
                return conName;
            }

            public void setConName(String conName) {
                this.conName = conName;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public String getOnlineTime() {
                return onlineTime;
            }

            public void setOnlineTime(String onlineTime) {
                this.onlineTime = onlineTime;
            }
        }
    }
}
