package entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Admin {
    private int id;
    private Integer privilege;
    private String adminname;
    private String adminpassword;
    private String pic;
    private String remarks;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "privilege")
    public Integer getPrivilege() {
        return privilege;
    }

    public void setPrivilege(Integer privilege) {
        this.privilege = privilege;
    }

    @Basic
    @Column(name = "adminname")
    public String getAdminname() {
        return adminname;
    }

    public void setAdminname(String adminname) {
        this.adminname = adminname;
    }

    @Basic
    @Column(name = "adminpassword")
    public String getAdminpassword() {
        return adminpassword;
    }

    public void setAdminpassword(String adminpassword) {
        this.adminpassword = adminpassword;
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
    @Column(name = "remarks")
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Admin admin = (Admin) o;

        if (id != admin.id) return false;
        if (privilege != null ? !privilege.equals(admin.privilege) : admin.privilege != null) return false;
        if (adminname != null ? !adminname.equals(admin.adminname) : admin.adminname != null) return false;
        if (adminpassword != null ? !adminpassword.equals(admin.adminpassword) : admin.adminpassword != null)
            return false;
        if (pic != null ? !pic.equals(admin.pic) : admin.pic != null) return false;
        if (remarks != null ? !remarks.equals(admin.remarks) : admin.remarks != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (privilege != null ? privilege.hashCode() : 0);
        result = 31 * result + (adminname != null ? adminname.hashCode() : 0);
        result = 31 * result + (adminpassword != null ? adminpassword.hashCode() : 0);
        result = 31 * result + (pic != null ? pic.hashCode() : 0);
        result = 31 * result + (remarks != null ? remarks.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", privilege=" + privilege +
                ", adminname='" + adminname + '\'' +
                ", adminpassword='" + adminpassword + '\'' +
                ", pic='" + pic + '\'' +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
