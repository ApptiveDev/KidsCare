package team3.OneSubscribe.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team3.OneSubscribe.domain.Member;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImp implements MemberRepository {


    @PersistenceContext
    private EntityManager em;

    @Override
    public Long save(Member member) {
        em.persist(member);
        return member.getId(); // return 필요?
    }

    @Override
    public Member findOneById(Long id) {
        return em.find(Member.class, id);
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }


    @Override
    public List<Member> findByLoginId(String loginId){

        try{
            return em.createQuery("select m from Member m where m.loginId = :loginId", Member.class)
                .setParameter("loginId", loginId)
                    .getResultList();
        }catch (Exception e){
            return null;
        }finally{

        }
    };




}
