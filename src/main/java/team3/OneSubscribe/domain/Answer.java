package team3.OneSubscribe.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

//@Entity
@Getter @Setter
public class Answer {

    private Long count;

    private List<AnswerContent> answers = new ArrayList<>();
}
