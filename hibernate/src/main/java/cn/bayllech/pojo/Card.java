package cn.bayllech.pojo;

import org.hibernate.annotations.*;

import javax.lang.model.element.Name;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_card")
public class Card {
    @Id
    @GenericGenerator(name = "FK",strategy = "foreign",parameters = {
            @org.hibernate.annotations.Parameter(name = "property",value = "person")
    })
    @GeneratedValue(generator = "FK")
    private Integer id;
    private String num;
    @OneToOne
    @PrimaryKeyJoinColumn
    private Person person;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}
