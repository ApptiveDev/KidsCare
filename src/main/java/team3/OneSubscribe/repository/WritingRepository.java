package team3.OneSubscribe.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import team3.OneSubscribe.domain.Answer;
import team3.OneSubscribe.domain.Member;
import team3.OneSubscribe.domain.Tag;
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

    public List<Writing> findByMember(Member member){
        return em.createQuery("select i from Writing i where i.member = :member", Writing.class)
                .setParameter("member", member)
                .getResultList();
    }

    public List<Writing> findListPaging(int startIndex, int pageSize){
        return em.createQuery("select i from Writing i", Writing.class)
                .setFirstResult(startIndex)
                .setMaxResults(pageSize)
                .getResultList();
    }

    public List<Writing> findListPagingForOwn(Member member, int startIndex, int pageSize){
        return em.createQuery("select i from Writing i where i.member = :member", Writing.class)
                .setParameter("member", member)
                .setFirstResult(startIndex)
                .setMaxResults(pageSize)
                .getResultList();
    }


    @Transactional
    public void deleteOne(Long id){
        em.createQuery("delete from Writing i where i.id = :id")
                .setParameter("id", id)
                .executeUpdate();
        return;
    }
}
