package team3.OneSubscribe.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Writing {

    @Id @GeneratedValue
    @Column(name = "writing_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private Long count;

    @OneToMany(mappedBy = "writing")
    private List<WritingContent> writings = new ArrayList<>();
}
