package entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class PostInfo {
    private int postid;
    private String rightname;
    private String posttitle;
    private Date posttime;
    private String postinfo;
    private String pic;
    private int postcollected;
    private int postfabulous;
    private int poststate;

    @Id
    @Column(name = "postid")
    public int getPostid() {
        return postid;
    }

    public void setPostid(int postid) {
        this.postid = postid;
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
    @Column(name = "posttitle")
    public String getPosttitle() {
        return posttitle;
    }

    public void setPosttitle(String posttitle) {
        this.posttitle = posttitle;
    }

    @Basic
    @Column(name = "posttime")
    public Date getPosttime() {
        return posttime;
    }

    public void setPosttime(Date posttime) {
        this.posttime = posttime;
    }

    @Basic
    @Column(name = "postinfo")
    public String getPostinfo() {
        return postinfo;
    }

    public void setPostinfo(String postinfo) {
        this.postinfo = postinfo;
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
    @Column(name = "postcollected")
    public int getPostcollected() {
        return postcollected;
    }

    public void setPostcollected(int postcollected) {
        this.postcollected = postcollected;
    }

    @Basic
    @Column(name = "postfabulous")
    public int getPostfabulous() {
        return postfabulous;
    }

    public void setPostfabulous(int postfabulous) {
        this.postfabulous = postfabulous;
    }

    @Basic
    @Column(name = "poststate")
    public int getPoststate() {
        return poststate;
    }

    public void setPoststate(int poststate) {
        this.poststate = poststate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PostInfo postinfo1 = (PostInfo) o;

        if (postid != postinfo1.postid) return false;
        if (postcollected != postinfo1.postcollected) return false;
        if (postfabulous != postinfo1.postfabulous) return false;
        if (poststate != postinfo1.poststate) return false;
        if (rightname != null ? !rightname.equals(postinfo1.rightname) : postinfo1.rightname != null) return false;
        if (posttitle != null ? !posttitle.equals(postinfo1.posttitle) : postinfo1.posttitle != null) return false;
        if (posttime != null ? !posttime.equals(postinfo1.posttime) : postinfo1.posttime != null) return false;
        if (postinfo != null ? !postinfo.equals(postinfo1.postinfo) : postinfo1.postinfo != null) return false;
        if (pic != null ? !pic.equals(postinfo1.pic) : postinfo1.pic != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = postid;
        result = 31 * result + (rightname != null ? rightname.hashCode() : 0);
        result = 31 * result + (posttitle != null ? posttitle.hashCode() : 0);
        result = 31 * result + (posttime != null ? posttime.hashCode() : 0);
        result = 31 * result + (postinfo != null ? postinfo.hashCode() : 0);
        result = 31 * result + (pic != null ? pic.hashCode() : 0);
        result = 31 * result + postcollected;
        result = 31 * result + postfabulous;
        result = 31 * result + poststate;
        return result;
    }
}
