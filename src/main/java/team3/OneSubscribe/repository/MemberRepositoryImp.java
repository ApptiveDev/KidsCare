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
    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }


    @Override
    public Member findByLoginId(String loginId){

        return em.createQuery("select m from Member m where m.loginId = :loginId", Member.class)
                .setParameter("loginId", loginId)
                .getSingleResult();
        // 어짜피 loginId를 중복을 허용안할거라서 검증할 필요없음.
        // 만약에 아이디 비번을 찾기 위해서 이것을 쓰는거라면, 그에 맞는거를 다시 만들기.
//        try{
//            return em.createQuery("select m from Member m where m.loginId = :loginId", Member.class)
//                .setParameter("loginId", loginId)
//                    .getSingleResult();
//        }catch (Exception e){
//            throw null;
//        }finally{
//
//        }
    };




}
