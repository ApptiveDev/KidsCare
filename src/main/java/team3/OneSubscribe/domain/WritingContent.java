package team3.OneSubscribe.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//@Entity
@Getter @Setter
public class WritingContent {

    private Long id;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    private String context; // 형식을 어떤걸로 저장해야 할지 모르겠음.

    private List<Tag> tag;

    //picture은 나중에 구현

    private List<AnswersToTheWriting> answers = new ArrayList<>();
}
