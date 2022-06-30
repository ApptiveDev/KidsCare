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

    @ManyToOne
    @JoinColumn(name = "writing_id")
    private Writing writing;

    private String context;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    @Enumerated(EnumType.STRING)
    private Expert expert;

    private int feedback = 0; // boolean으로 하면 get이 안되어서 일단은 int

    private Long likeNumber;

}
