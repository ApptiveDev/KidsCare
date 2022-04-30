package team3.OneSubscribe.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team3.OneSubscribe.domain.Answer;
import team3.OneSubscribe.domain.Writing;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AnswerRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Answer answer){
        em.persist(answer);
    }

    public Answer findOne(Long id){
        return em.find(Answer.class, id);
    }

    public List<Answer> findAll(){
        return em.createQuery("select i from Item i", Answer.class)
                .getResultList();
    }


}