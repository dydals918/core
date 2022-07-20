package hello.core.member;

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
}
