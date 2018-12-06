package entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SmsReminderInfo {
    private int smsid;
    private String rightname;
    private String userphone;
    private String eventinfo;
    private String time;
    private int state;

    @Id
    @Column(name = "smsid")
    public int getSmsid() {
        return smsid;
    }

    public void setSmsid(int smsid) {
        this.smsid = smsid;
    }

    @Basic
    @Column(name = "rightname")
    public String getRightname() {
        return rightname;
    }

    public void setRightname(String rightname) {
        this.rightname = rightname;
    }

    @Basic
    @Column(name = "userphone")
    public String getUserphone() {
        return userphone;
    }

    public void setUserphone(String userphone) {
        this.userphone = userphone;
    }

    @Basic
    @Column(name = "eventinfo")
    public String getEventinfo() {
        return eventinfo;
    }

    public void setEventinfo(String eventinfo) {
        this.eventinfo = eventinfo;
    }

    @Basic
    @Column(name = "time")
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Basic
    @Column(name = "state")
    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SmsReminderInfo that = (SmsReminderInfo) o;

        if (smsid != that.smsid) return false;
        if (state != that.state) return false;
        if (rightname != null ? !rightname.equals(that.rightname) : that.rightname != null) return false;
        if (userphone != null ? !userphone.equals(that.userphone) : that.userphone != null) return false;
        if (eventinfo != null ? !eventinfo.equals(that.eventinfo) : that.eventinfo != null) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = smsid;
        result = 31 * result + (rightname != null ? rightname.hashCode() : 0);
        result = 31 * result + (userphone != null ? userphone.hashCode() : 0);
        result = 31 * result + (eventinfo != null ? eventinfo.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + state;
        return result;
    }
}
