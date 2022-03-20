package team3.OneSubscribe.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

//@Entity
@Getter @Setter
public class AnswersFromMember {

    private Long id;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    private Expert expert;

    private Long likeNumber;

    private boolean feedback;
}
