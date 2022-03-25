package team3.OneSubscribe.repository;

import org.springframework.stereotype.Repository;
import team3.OneSubscribe.domain.Member;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MemberRepositoryImp implements MemberRepository {


    @PersistenceContext
    private EntityManager em;

    public Long save(Member member) {
        em.persist(member);
        return member.getId();
    }

    public Member find(Long id) {
        return em.find(Member.class, id);
    }

    @Override
    public int loginCheck(String loginId, String loginPw) {
        return 0;
    }

    @Override
    public Member findByLoginId(String loginId) {
        return null;
    }

    @Override
    public String matchNamenEmailNPhoneNum(String name, String email, String phoneNum) {
        return "zhdhfhd33";
    }
}
