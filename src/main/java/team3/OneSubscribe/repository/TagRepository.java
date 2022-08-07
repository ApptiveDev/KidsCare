package team3.OneSubscribe.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import team3.OneSubscribe.domain.Tag;
import team3.OneSubscribe.domain.Writing;

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

    @Transactional
    public void saveAll(List<Tag> tags){
        for(int i = 0; i < tags.size(); i++){
            em.persist(tags.get(i));
        }
    }

    public List<Tag> findListByWriting(Writing writing){
        return em.createQuery("select i from Tag i where i.writing = :writing", Tag.class)
                .setParameter("writing", writing)
                .getResultList();
    }

    public List<Tag> findAll(){
        return em.createQuery("select i from Tag i", Tag.class)
                .getResultList();
    }
    @Transactional
    public void deleteMany(Writing writing){
        em.createQuery("delete from Tag i where i.writing = : writing")
                .setParameter("writing", writing)
                .executeUpdate();
    }
}
