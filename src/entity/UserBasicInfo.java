package entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserBasicInfo {
    private int id;
    private String pic;
    private String username;
    private String userpassword;
    private String useremail;
    private String usertel;
    private String name;
    private Integer sex;
    private String usernative;
    private String usernation;
    private String useradd;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "userpassword")
    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    @Basic
    @Column(name = "useremail")
    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    @Basic
    @Column(name = "usertel")
    public String getUsertel() {
        return usertel;
    }

    public void setUsertel(String usertel) {
        this.usertel = usertel;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "sex")
    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    @Basic
    @Column(name = "usernative")
    public String getUsernative() {
        return usernative;
    }

    public void setUsernative(String usernative) {
        this.usernative = usernative;
    }

    @Basic
    @Column(name = "usernation")
    public String getUsernation() {
        return usernation;
    }

    public void setUsernation(String usernation) {
        this.usernation = usernation;
    }

    @Basic
    @Column(name = "useradd")
    public String getUseradd() {
        return useradd;
    }

    public void setUseradd(String useradd) {
        this.useradd = useradd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserBasicInfo that = (UserBasicInfo) o;

        if (id != that.id) return false;
        if (pic != null ? !pic.equals(that.pic) : that.pic != null) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (userpassword != null ? !userpassword.equals(that.userpassword) : that.userpassword != null) return false;
        if (useremail != null ? !useremail.equals(that.useremail) : that.useremail != null) return false;
        if (usertel != null ? !usertel.equals(that.usertel) : that.usertel != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (sex != null ? !sex.equals(that.sex) : that.sex != null) return false;
        if (usernative != null ? !usernative.equals(that.usernative) : that.usernative != null) return false;
        if (usernation != null ? !usernation.equals(that.usernation) : that.usernation != null) return false;
        if (useradd != null ? !useradd.equals(that.useradd) : that.useradd != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (pic != null ? pic.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (userpassword != null ? userpassword.hashCode() : 0);
        result = 31 * result + (useremail != null ? useremail.hashCode() : 0);
        result = 31 * result + (usertel != null ? usertel.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (usernative != null ? usernative.hashCode() : 0);
        result = 31 * result + (usernation != null ? usernation.hashCode() : 0);
        result = 31 * result + (useradd != null ? useradd.hashCode() : 0);
        return result;
    }
}
