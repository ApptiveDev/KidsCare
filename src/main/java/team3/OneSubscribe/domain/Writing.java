package team3.OneSubscribe.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import team3.OneSubscribe.DTO.WritingDTO;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Writing {
    public Writing(WritingDTO p) {
        id = p.getId();
        title = p.getTitle();
        context = p.getContext();
    }

    @Id @GeneratedValue
    @Column(name = "writing_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "nickName")
    private Member member;

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
