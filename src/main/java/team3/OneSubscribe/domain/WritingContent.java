package team3.OneSubscribe.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class WritingContent {

    @Id @GeneratedValue
    @Column(name = "writing_content_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "writing_id")
    private Writing writing;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    private String context; // 형식을 어떤걸로 저장해야 할지 모르겠음.

    @OneToMany(mappedBy = "writingContent") // mappedBy는 JAVA Code에서 실제로 사용하는 변수명으로, camelCase로 작성한다.
    private List<Tag> tags; // enum을 list로 가져올 수 없어서, Tag 거쳐서 이동

    //private Picture picture;
    //picture은 나중에 구현

    private ArrayList answerMemberId = new ArrayList();
}
