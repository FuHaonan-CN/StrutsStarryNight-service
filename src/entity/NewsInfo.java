package entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class NewsInfo {
    private int newsid;
    private String title;
    private Date newstime;
    private String editor;
    private String news;
    private int countdown;
    private String pic;

    @Id
    @Column(name = "newsid")
    public int getNewsid() {
        return newsid;
    }

    public void setNewsid(int newsid) {
        this.newsid = newsid;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
    @Column(name = "editor")
    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    @Basic
    @Column(name = "news")
    public String getNews() {
        return news;
    }

    public void setNews(String news) {
        this.news = news;
    }

    @Basic
    @Column(name = "countdown")
    public int getCountdown() {
        return countdown;
    }

    public void setCountdown(int countdown) {
        this.countdown = countdown;
    }

    @Basic
    @Column(name = "pic")
    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NewsInfo newsInfo = (NewsInfo) o;

        if (newsid != newsInfo.newsid) return false;
        if (countdown != newsInfo.countdown) return false;
        if (title != null ? !title.equals(newsInfo.title) : newsInfo.title != null) return false;
        if (newstime != null ? !newstime.equals(newsInfo.newstime) : newsInfo.newstime != null) return false;
        if (editor != null ? !editor.equals(newsInfo.editor) : newsInfo.editor != null) return false;
        if (news != null ? !news.equals(newsInfo.news) : newsInfo.news != null) return false;
        if (pic != null ? !pic.equals(newsInfo.pic) : newsInfo.pic != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = newsid;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (newstime != null ? newstime.hashCode() : 0);
        result = 31 * result + (editor != null ? editor.hashCode() : 0);
        result = 31 * result + (news != null ? news.hashCode() : 0);
        result = 31 * result + countdown;
        result = 31 * result + (pic != null ? pic.hashCode() : 0);
        return result;
    }
}
