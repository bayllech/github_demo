package cn.bayllech.pojo;

public class Topic {
    private Integer id;
    private String title;
    private Integer contentid;
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

    public Integer getContentid() {
        return contentid;
    }

    public void setContentid(Integer contentid) {
        this.contentid = contentid;
    }

    public TitleContent getTitleContent() {
        return titleContent;
    }

    public void setTitleContent(TitleContent titleContent) {
        this.titleContent = titleContent;
    }
}
