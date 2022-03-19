package team3.OneSubscribe.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.time.LocalDateTime;

//@Entity
@Getter @Setter
public class AnswerContent {

    private Long id;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    private Expert expert;

    private Long likeNumber;

    private boolean feedback;
}
