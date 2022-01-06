package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) // 데이터 변경할 때는 꼭 Transactional annotation 안에서 이루어져야 한다.
// readOnly -> 성능 최적화
@RequiredArgsConstructor // final 있는 필드만 가지고 생성자 만듬
public class MemberService { // Ctrl + Shift + T -> Test 만들기

    private final MemberRepository memberRepository; // 변경할 일이 없으므로 final 설정



    /**
    * 회원 가입
     */
    @Transactional // 우선적으로 적용됨 default -> readOnly = false
    public Long join(Member member){
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        // EXCEPTION
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    /**
     * 회원 전체 조회
      */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }

}
