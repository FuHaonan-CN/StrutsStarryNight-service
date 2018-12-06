package entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class ReportInfo {
    private int reportid;
    private String rightname;
    private Date newstime;
    private String info;
    private String pic;
    private int reportstate;

    @Id
    @Column(name = "reportid")
    public int getReportid() {
        return reportid;
    }

    public void setReportid(int reportid) {
        this.reportid = reportid;
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
    @Column(name = "newstime")
    public Date getNewstime() {
        return newstime;
    }

    public void setNewstime(Date newstime) {
        this.newstime = newstime;
    }

    @Basic
    @Column(name = "info")
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Basic
    @Column(name = "pic")
    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    @Basic
    @Column(name = "reportstate")
    public int getReportstate() {
        return reportstate;
    }

    public void setReportstate(int reportstate) {
        this.reportstate = reportstate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReportInfo that = (ReportInfo) o;

        if (reportid != that.reportid) return false;
        if (reportstate != that.reportstate) return false;
        if (rightname != null ? !rightname.equals(that.rightname) : that.rightname != null) return false;
        if (newstime != null ? !newstime.equals(that.newstime) : that.newstime != null) return false;
        if (info != null ? !info.equals(that.info) : that.info != null) return false;
        if (pic != null ? !pic.equals(that.pic) : that.pic != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = reportid;
        result = 31 * result + (rightname != null ? rightname.hashCode() : 0);
        result = 31 * result + (newstime != null ? newstime.hashCode() : 0);
        result = 31 * result + (info != null ? info.hashCode() : 0);
        result = 31 * result + (pic != null ? pic.hashCode() : 0);
        result = 31 * result + reportstate;
        return result;
    }
}
