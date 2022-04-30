package team3.OneSubscribe.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team3.OneSubscribe.domain.Writing;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class WritingRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Writing writing){
        em.persist(writing);
    }

    public Writing findOne(Long id){
        return em.find(Writing.class, id);
    }

    public List<Writing> findAll(){
        return em.createQuery("select i from Item i", Writing.class)
                .getResultList();
    }
}