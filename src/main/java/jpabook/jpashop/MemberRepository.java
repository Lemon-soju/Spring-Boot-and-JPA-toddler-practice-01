package jpabook.jpashop;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MemberRepository {

    @PersistenceContext // 스프링에서 자동으로 생성한 EntityManager를 주입받음
    private EntityManager em;

    public Long save(Member member){
        em.persist(member); // member DB에 넣기
        return member.getId();
    }

    public Member find(Long id){
        return em.find(Member.class, id);
    }
}
