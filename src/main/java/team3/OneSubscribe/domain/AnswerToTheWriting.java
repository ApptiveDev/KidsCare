package team3.OneSubscribe.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class AnswerToTheWriting {

    @Id @GeneratedValue
    @Column(name = "answer_to_the_writing_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "writing_content_id")
    private WritingContent writingContent;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    @Enumerated(EnumType.STRING)
    private Expert expert;

    private Long likeNumber;

    private boolean feedback;
}
