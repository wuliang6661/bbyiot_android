package com.baibeiyun.bbyiot.model.Response;

import java.util.List;

public class AnalysisHistoryResponse {


    private List<Long> xTimes;
    /**
     * attributeName : 震动变送器（震动速度）
     * statistics : [{"attributeid":20,"averageValue":"0.07","createTime":1560355218000,"eid":11,"id":61469,"maxValue":"0.08","minValue":"0.07","mode":1,"type":0},{"attributeid":20,"averageValue":"0.07","createTime":1560358817000,"eid":11,"id":61488,"maxValue":"0.08","minValue":"0.07","mode":1,"type":0},{"attributeid":20,"averageValue":"0.07","createTime":1560362418000,"eid":11,"id":61507,"maxValue":"0.08","minValue":"0.07","mode":1,"type":0},{"attributeid":20,"averageValue":"0.07","createTime":1560366017000,"eid":11,"id":61526,"maxValue":"0.08","minValue":"0.07","mode":1,"type":0},{"attributeid":20,"averageValue":"0.07","createTime":1560369618000,"eid":11,"id":61545,"maxValue":"0.08","minValue":"0.07","mode":1,"type":0},{"attributeid":20,"averageValue":"0.07","createTime":1560373220000,"eid":11,"id":61564,"maxValue":"0.08","minValue":"0.07","mode":1,"type":0},{"attributeid":20,"averageValue":"0.07","createTime":1560376817000,"eid":11,"id":61583,"maxValue":"0.08","minValue":"0.07","mode":1,"type":0},{"attributeid":20,"averageValue":"0.07","createTime":1560380417000,"eid":11,"id":61602,"maxValue":"0.08","minValue":"0.07","mode":1,"type":0},{"attributeid":20,"averageValue":"0.07","createTime":1560384019000,"eid":11,"id":61621,"maxValue":"0.08","minValue":"0.07","mode":1,"type":0},{"attributeid":20,"averageValue":"0.07","createTime":1560387624000,"eid":11,"id":61640,"maxValue":"0.08","minValue":"0.07","mode":1,"type":0},{"attributeid":20,"averageValue":"0.07","createTime":1560391219000,"eid":11,"id":61659,"maxValue":"0.08","minValue":"0.07","mode":1,"type":0},{"attributeid":20,"averageValue":"0.07","createTime":1560394827000,"eid":11,"id":61678,"maxValue":"0.08","minValue":"0.06","mode":1,"type":0},{"attributeid":20,"averageValue":"0.07","createTime":1560398418000,"eid":11,"id":61697,"maxValue":"0.08","minValue":"0.06","mode":1,"type":0},{"attributeid":20,"averageValue":"0.07","createTime":1560402020000,"eid":11,"id":61716,"maxValue":"0.08","minValue":"0.06","mode":1,"type":0},{"attributeid":20,"averageValue":"0.07","createTime":1560405607000,"eid":11,"id":61773,"maxValue":"0.08","minValue":"0.06","mode":1,"type":0},{"attributeid":20,"averageValue":"0.07","createTime":1560409205000,"eid":11,"id":61906,"maxValue":"0.08","minValue":"0.06","mode":1,"type":0},{"attributeid":20,"averageValue":"0.07","createTime":1560412805000,"eid":11,"id":62020,"maxValue":"0.08","minValue":"0.06","mode":1,"type":0},{"attributeid":20,"averageValue":"0.07","createTime":1560416407000,"eid":11,"id":62153,"maxValue":"0.08","minValue":"0.06","mode":1,"type":0},{"attributeid":20,"averageValue":"0.07","createTime":1560420007000,"eid":11,"id":62286,"maxValue":"0.08","minValue":"0.07","mode":1,"type":0}]
     * symbol : mm/s
     */

    private List<YDatasBean> yDatas;

    public List<Long> getXTimes() {
        return xTimes;
    }

    public void setXTimes(List<Long> xTimes) {
        this.xTimes = xTimes;
    }

    public List<YDatasBean> getYDatas() {
        return yDatas;
    }

    public void setYDatas(List<YDatasBean> yDatas) {
        this.yDatas = yDatas;
    }

    public static class YDatasBean {
        private String attributeName;
        private String symbol;
        /**
         * attributeid : 20
         * averageValue : 0.07
         * createTime : 1560355218000
         * eid : 11
         * id : 61469
         * maxValue : 0.08
         * minValue : 0.07
         * mode : 1
         * type : 0
         */

        private List<StatisticsBean> statistics;

        public String getAttributeName() {
            return attributeName;
        }

        public void setAttributeName(String attributeName) {
            this.attributeName = attributeName;
        }

        public String getSymbol() {
            return symbol;
        }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }

        public List<StatisticsBean> getStatistics() {
            return statistics;
        }

        public void setStatistics(List<StatisticsBean> statistics) {
            this.statistics = statistics;
        }

        public static class StatisticsBean {
            private int attributeid;
            private String averageValue;
            private long createTime;
            private int eid;
            private int id;
            private String maxValue;
            private String minValue;
            private int mode;
            private int type;

            public int getAttributeid() {
                return attributeid;
            }

            public void setAttributeid(int attributeid) {
                this.attributeid = attributeid;
            }

            public String getAverageValue() {
                return averageValue;
            }

            public void setAverageValue(String averageValue) {
                this.averageValue = averageValue;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public int getEid() {
                return eid;
            }

            public void setEid(int eid) {
                this.eid = eid;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getMaxValue() {
                return maxValue;
            }

            public void setMaxValue(String maxValue) {
                this.maxValue = maxValue;
            }

            public String getMinValue() {
                return minValue;
            }

            public void setMinValue(String minValue) {
                this.minValue = minValue;
            }

            public int getMode() {
                return mode;
            }

            public void setMode(int mode) {
                this.mode = mode;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }
        }
    }
}
