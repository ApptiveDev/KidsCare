package team3.OneSubscribe.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import team3.OneSubscribe.domain.Tag;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class TagRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void save(Tag tag){
        em.persist(tag);
    }

    public List<Tag> findAll(){
        return em.createQuery("select i from Writing i", Tag.class)
                .getResultList();
    }
}
