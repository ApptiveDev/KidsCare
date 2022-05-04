package team3.OneSubscribe.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Writing {

    @Id @GeneratedValue
    @Column(name = "writing_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "member_id")
    @JoinColumn(name = "nickName")
    private Member member;

//    private Long count;

//    @OneToMany(mappedBy = "writing")
//    private List<WritingContent> writingContents = new ArrayList<>();

    @OneToMany(mappedBy = "writing")
    private List<Tag> tags = new ArrayList<>();

    @OneToMany(mappedBy = "writing")
    private List<Answer> answers = new ArrayList<>();

    private String title;

    private String context;

    @CreationTimestamp
    private LocalDateTime createDate;

    @CreationTimestamp
    private LocalDateTime updateDate;

}
