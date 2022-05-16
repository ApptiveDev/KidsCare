package team3.OneSubscribe.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import team3.OneSubscribe.domain.Answer;
import team3.OneSubscribe.domain.Writing;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class WritingRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public long save(Writing writing){
        em.persist(writing);
        return writing.getId();
    }

    public Writing findOneById(Long id){
        return em.find(Writing.class, id);
    }

    public List<Writing> findAll(){
        return em.createQuery("select i from Writing i", Writing.class)
                .getResultList();
    }




}
