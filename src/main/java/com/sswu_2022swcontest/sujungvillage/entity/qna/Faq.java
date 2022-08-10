package com.sswu_2022swcontest.sujungvillage.entity.qna;

import com.sswu_2022swcontest.sujungvillage.entity.Dormitory;
import com.sswu_2022swcontest.sujungvillage.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Faq {

    @Id
    @GeneratedValue
    @Column(name = "faq_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "WRITER_ID")
    private User writer;

    @ManyToOne
    @JoinColumn(name = "DORMITORY_ID")
    private Dormitory dormitory;

    private String question;

    private String answer;

    @CreatedDate
    private LocalDateTime regDate;

    @LastModifiedDate
    private LocalDateTime modDate;

}