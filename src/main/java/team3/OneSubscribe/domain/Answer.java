package team3.OneSubscribe.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

//@Entity
@Getter @Setter
public class Answer {

    private Long count;

    private Long totalLikeNumber;

    private List<AnswersFromMember> answers = new ArrayList<>();
}
