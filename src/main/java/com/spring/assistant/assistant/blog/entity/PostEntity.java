package com.spring.assistant.assistant.blog.entity;

import com.spring.assistant.assistant.todo.shared.enums.PostStatusType;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@Table
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "post_id")
    private Long id;

    @Column(name = "title", nullable = false)
    @Length(min = 5, message = "*Your title must have at least 5 characters")
    @NotEmpty(message = "*Please provide title")
    private String title;

    @Size(max = 5000)
    private String body;

    @Column(nullable = false)
    private String category;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;

    private String userId;

    private String commentId;

    @Enumerated(EnumType.STRING)
    private PostStatusType postStatusType;

    private String badgeOne;

    private String badgeTwo;

    private String badgeThree;

    private String badgeFour;

    private String badgeFive;


}
