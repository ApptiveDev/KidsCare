package team3.OneSubscribe.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter @Setter
@EqualsAndHashCode
@ToString
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String loginId; // 형식 확인 필요

    private String loginPassword; // 형식 확인 필요

    private String name;

    private String nickName;

    private String eMail;

    private String team; // group으로 하면 데이터베이스 예약어라서 안됨.

    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Expert expert;

    @OneToOne(mappedBy = "member", fetch = FetchType.LAZY)
    private Writing writings;

    @OneToOne(mappedBy = "member", fetch = FetchType.LAZY)
    private Answer answers;

}
