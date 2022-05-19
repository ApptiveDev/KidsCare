package team3.OneSubscribe.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Answer {

    @Id @GeneratedValue
    @Column(name = "answer_id")
    private Long id;

    private String nickName;

//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "member_id")
//    private Member member;

    @ManyToOne
    @JoinColumn(name = "writing_id")
    private Writing writing;

    private String context;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    @Enumerated(EnumType.STRING)
    private Expert expert;

    private boolean feedback = false;

    private Long likeNumber;

//    private Long totalLikeNumber;

//    @OneToMany(mappedBy = "answer")
//    private List<AnswerContent> answersContents = new ArrayList<>();
}
