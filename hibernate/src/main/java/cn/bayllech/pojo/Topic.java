package cn.bayllech.pojo;

import javax.persistence.*;

@Entity
@Table(name = "t_topic")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contentid")
    private TitleContent titleContent;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public TitleContent getTitleContent() {
        return titleContent;
    }

    public void setTitleContent(TitleContent titleContent) {
        this.titleContent = titleContent;
    }
}
