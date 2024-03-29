package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{

    /*
        확장 o, 변경 o -> OCP 위반
        인터페이스 의존 o, 구현 클래스 의존 o -> DIP 위반
     */
//    private final MemberRepository memberRepository = new MemoryMemberRepository();

    /*
        생성자 주입
        인터페이스 의존 o, 구현클래스 의존 x
     */
    private final MemberRepository memberRepository;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
