package team3.OneSubscribe.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

//@Entity
@Getter @Setter
public class Writing {

    private Long count;

    private List<WritingContent> writings = new ArrayList<>();
}
