package com.sswu_2022swcontest.sujungvillage.entity.home;

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
public class Announcement {

    @Id
    @GeneratedValue
    @Column(name = "announcement_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "WRITER_ID")
    private User writer;

    private String title;

    private String content;

    @ManyToOne
    @JoinColumn(name = "DORMITORY_ID")
    private Dormitory dormitory;

    @CreatedDate
    private LocalDateTime regDate;

    @LastModifiedDate
    private LocalDateTime modDate;

}
