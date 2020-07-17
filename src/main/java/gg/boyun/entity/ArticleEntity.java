package gg.boyun.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "article")
@Data
@DynamicInsert
@DynamicUpdate
@JsonPropertyOrder({"content", "title"})
public class ArticleEntity {
    @Id // idx를 말함 pk
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx", columnDefinition = "int(11)")
    private Integer idx;

    @Column(name = "title", columnDefinition = "varchar(250)")
    private String title;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

}
