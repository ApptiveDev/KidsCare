package team3.OneSubscribe.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

//@Entity
@Getter @Setter
public class AnswerContent {

    @Id @GeneratedValue
    @Column(name = "answer_content_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "answer_id")
    private Answer answer;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    private String context; // 형식을 어떤걸로 저장해야 할지 모르겠음.

    @Enumerated(EnumType.STRING)
    private Expert expert;

    private Long likeNumber;

    private boolean feedback;

    //조회를 위한 정보

    private Long questioner;

    private Long writingContentNumber;
}
