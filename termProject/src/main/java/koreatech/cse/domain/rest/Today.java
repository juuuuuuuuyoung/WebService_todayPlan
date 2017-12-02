package koreatech.cse.domain.rest;

import java.util.Date;

public class Today {
    private int id;
    private String appKey;
    private Date createTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getAppKey() {
        return appKey;
    }
    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Today{" +
                "id=" + id +
                ", appKey='" + appKey + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
