package team3.OneSubscribe.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

//@Entity
@Getter @Setter
public class Member {

    private Long id;

    private String loginId; // 형식 확인 필요

    private String loginPassword; // 형식 확인 필요

    private String nickName;

    private String eMail;

    private String group;

    private Expert expert;

    private Writing writings;

    private Answer answers;

}
