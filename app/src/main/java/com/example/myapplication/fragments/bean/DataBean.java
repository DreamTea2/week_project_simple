package com.example.myapplication.fragments.bean;

/**
 * Create By shaodong on 2021/8/3 15:50
 */
public class DataBean {

    private int itemType;
    private TitleBean titleBean;
    private HotBean hotBean;
    private SubBean subBean;
    private OpenBean openBean;
    private TeachBean teachBean;

    public DataBean(int itemType, TitleBean titleBean) {
        this.itemType = itemType;
        this.titleBean = titleBean;
    }

    public DataBean(int itemType, HotBean hotBean) {
        this.itemType = itemType;
        this.hotBean = hotBean;
    }

    public DataBean(int itemType, SubBean subBean) {
        this.itemType = itemType;
        this.subBean = subBean;
    }

    public DataBean(int itemType, OpenBean openBean) {
        this.itemType = itemType;
        this.openBean = openBean;
    }

    public DataBean(int itemType, TeachBean teachBean) {
        this.itemType = itemType;
        this.teachBean = teachBean;
    }

    public static class TitleBean {
        private String titleName;

        public TitleBean(String titleName) {
            this.titleName = titleName;
        }

        public String getTitleName() {
            return titleName;
        }

        public void setTitleName(String titleName) {
            this.titleName = titleName;
        }
    }

    public static class HotBean {
        private String titleName;

        public HotBean(String titleName) {
            this.titleName = titleName;
        }

        public String getTitleName() {
            return titleName;
        }

        public void setTitleName(String titleName) {
            this.titleName = titleName;
        }
    }

    public static class SubBean {
        private String titleName;

        public SubBean(String titleName) {
            this.titleName = titleName;
        }

        public String getTitleName() {
            return titleName;
        }

        public void setTitleName(String titleName) {
            this.titleName = titleName;
        }
    }

    public static class OpenBean {
        private String titleName;

        public OpenBean(String titleName) {
            this.titleName = titleName;
        }

        public String getTitleName() {
            return titleName;
        }

        public void setTitleName(String titleName) {
            this.titleName = titleName;
        }
    }

    public static class TeachBean {
        private String titleName;

        public TeachBean(String titleName) {
            this.titleName = titleName;
        }

        public String getTitleName() {
            return titleName;
        }

        public void setTitleName(String titleName) {
            this.titleName = titleName;
        }
    }


    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public TitleBean getTitleBean() {
        return titleBean;
    }

    public void setTitleBean(TitleBean titleBean) {
        this.titleBean = titleBean;
    }

    public HotBean getHotBean() {
        return hotBean;
    }

    public void setHotBean(HotBean hotBean) {
        this.hotBean = hotBean;
    }

    public SubBean getSubBean() {
        return subBean;
    }

    public void setSubBean(SubBean subBean) {
        this.subBean = subBean;
    }

    public OpenBean getOpenBean() {
        return openBean;
    }

    public void setOpenBean(OpenBean openBean) {
        this.openBean = openBean;
    }

    public TeachBean getTeachBean() {
        return teachBean;
    }

    public void setTeachBean(TeachBean teachBean) {
        this.teachBean = teachBean;
    }
}
