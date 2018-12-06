package entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class PostComment {
    private int commentid;
    private String rightname;
    private String floor;
    private String comment;
    private Date commenttime;
    private int commentstate;

    @Id
    @Column(name = "commentid")
    public int getCommentid() {
        return commentid;
    }

    public void setCommentid(int commentid) {
        this.commentid = commentid;
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
    @Column(name = "floor")
    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    @Basic
    @Column(name = "comment")
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Basic
    @Column(name = "commenttime")
    public Date getCommenttime() {
        return commenttime;
    }

    public void setCommenttime(Date commenttime) {
        this.commenttime = commenttime;
    }

    @Basic
    @Column(name = "commentstate")
    public int getCommentstate() {
        return commentstate;
    }

    public void setCommentstate(int commentstate) {
        this.commentstate = commentstate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PostComment that = (PostComment) o;

        if (commentid != that.commentid) return false;
        if (commentstate != that.commentstate) return false;
        if (rightname != null ? !rightname.equals(that.rightname) : that.rightname != null) return false;
        if (floor != null ? !floor.equals(that.floor) : that.floor != null) return false;
        if (comment != null ? !comment.equals(that.comment) : that.comment != null) return false;
        if (commenttime != null ? !commenttime.equals(that.commenttime) : that.commenttime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = commentid;
        result = 31 * result + (rightname != null ? rightname.hashCode() : 0);
        result = 31 * result + (floor != null ? floor.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (commenttime != null ? commenttime.hashCode() : 0);
        result = 31 * result + commentstate;
        return result;
    }
}
