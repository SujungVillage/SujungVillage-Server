package com.sswu_2022swcontest.sujungvillage.entity.home;

import com.sswu_2022swcontest.sujungvillage.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LongTermExeat {

    @Id
    @GeneratedValue
    @Column(name = "long_term_exeat_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    private String destination;

    private String reason;

    private String emergencyPhoneNumber;

    private LocalDate startDate;

    private LocalDate endDate;

}
