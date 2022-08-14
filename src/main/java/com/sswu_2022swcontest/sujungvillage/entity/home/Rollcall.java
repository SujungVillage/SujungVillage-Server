package com.sswu_2022swcontest.sujungvillage.entity.home;

import com.sswu_2022swcontest.sujungvillage.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Rollcall {

    @Id
    @GeneratedValue
    @Column(name = "rollcall_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @Column(length = 20000)
    private String imageURL;        // 앱에서 s3서버에 업로한 url

    private String location;        // 점호당시 위치

    @CreatedDate
    private LocalDateTime rollcallTime;

    private String state;           // 대기, 승인, 반려
}
