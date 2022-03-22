package team3.OneSubscribe.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Answer {

    @Id @GeneratedValue
    @Column(name = "answer_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private Long count;

    private Long totalLikeNumber;

    @OneToMany(mappedBy = "answer")
    private List<AnswerFromMember> answers = new ArrayList<>();
}
