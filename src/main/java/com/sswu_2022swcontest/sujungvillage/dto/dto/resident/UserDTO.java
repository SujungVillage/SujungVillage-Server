package com.sswu_2022swcontest.sujungvillage.dto.dto.resident;

import com.sswu_2022swcontest.sujungvillage.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserDTO {

    private String userId;
    private String name;
    private String dormitoryName;
    private String detailedAddress;

    public static UserDTO entityToDTO(User e){
        return new UserDTO(
                e.getId(),
                e.getName(),
                e.getDormitory().getDormitoryName(),
                e.getDetailedAddress()
        );
    }

}
