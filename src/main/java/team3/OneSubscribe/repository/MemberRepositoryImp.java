package team3.OneSubscribe.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team3.OneSubscribe.domain.Member;
import team3.OneSubscribe.error.DuplicatedLoginIDExcpetion;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
    //일치하는게 없으면 null반환.
    public Member findByLoginId(String loginId){
        List<Member> li;
        try{
            li = em.createQuery("select m from Member m where m.loginId = :loginId", Member.class)
                .setParameter("loginId", loginId)
                    .getResultList();

        }catch (Exception e){
            return null;
        }finally{

        }
        int sz = li.size();
        if (li == null) {
            return null;
        } else if (sz >= 2) {
            throw new DuplicatedLoginIDExcpetion("시스템에 같은 이름의 id가 2개 이상 존재합니다");
        } else {
            return li.get(0);
        }
    }

    @Override
    public List<Member> findByPhoneNumber(String phoneNumber) {
        return null;
    }




}

