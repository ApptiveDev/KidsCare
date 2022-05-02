package team3.OneSubscribe.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Tag {


    @Id @GeneratedValue
    @Column(name = "tag_id")
    private Long id;

//    @ManyToOne
//    @JoinColumn(name = "writing_content_id")
//    private WritingContent writingContent;

    @ManyToOne
    @JoinColumn(name = "writing_id")
    private Writing writing;

    @Enumerated(EnumType.STRING)
    private DiseaseName diseaseName;

}
