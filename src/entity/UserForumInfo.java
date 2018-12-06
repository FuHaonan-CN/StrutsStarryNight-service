package entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserForumInfo {
    private int id;
    private String username;
    private String postid;
    private String mycollect;
    private String myfabulous;
    private String mycomment;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "postid")
    public String getPostid() {
        return postid;
    }

    public void setPostid(String postid) {
        this.postid = postid;
    }

    @Basic
    @Column(name = "mycollect")
    public String getMycollect() {
        return mycollect;
    }

    public void setMycollect(String mycollect) {
        this.mycollect = mycollect;
    }

    @Basic
    @Column(name = "myfabulous")
    public String getMyfabulous() {
        return myfabulous;
    }

    public void setMyfabulous(String myfabulous) {
        this.myfabulous = myfabulous;
    }

    @Basic
    @Column(name = "mycomment")
    public String getMycomment() {
        return mycomment;
    }

    public void setMycomment(String mycomment) {
        this.mycomment = mycomment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserForumInfo that = (UserForumInfo) o;

        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (postid != null ? !postid.equals(that.postid) : that.postid != null) return false;
        if (mycollect != null ? !mycollect.equals(that.mycollect) : that.mycollect != null) return false;
        if (myfabulous != null ? !myfabulous.equals(that.myfabulous) : that.myfabulous != null) return false;
        if (mycomment != null ? !mycomment.equals(that.mycomment) : that.mycomment != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (postid != null ? postid.hashCode() : 0);
        result = 31 * result + (mycollect != null ? mycollect.hashCode() : 0);
        result = 31 * result + (myfabulous != null ? myfabulous.hashCode() : 0);
        result = 31 * result + (mycomment != null ? mycomment.hashCode() : 0);
        return result;
    }
}
