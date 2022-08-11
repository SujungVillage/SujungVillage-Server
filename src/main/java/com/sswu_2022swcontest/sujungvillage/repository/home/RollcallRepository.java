package com.sswu_2022swcontest.sujungvillage.repository.home;

import com.sswu_2022swcontest.sujungvillage.entity.home.Rollcall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface RollcallRepository extends JpaRepository<Rollcall, Long> {

    @Query(value = "select rollcall_id, imageurl, location, rollcall_time, rollcall.state, rollcall.user_id from rollcall " +
            "left join users on rollcall.user_id=users.user_id where rollcall.state = '대기' AND users.dormitory_id = ?1 ;"
            , nativeQuery = true)
    List<Rollcall> getWaitingRollcallsByDormitoryId(Integer dormitoryId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE rollcall SET state = ?2 WHERE rollcall_id = ?1 ;"
            , nativeQuery = true)
    Integer changeState(Long rollcallId, String state);

}
