package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
// @RequiredArgsConstructor final 키워드의 필드를 모아 생성자를 자동으로 만들어 준다. 최근 의존성 주입 주 사용법
public class OrderServiceImpl implements OrderService {

    /*
        확장 o, 변경 o -> OCP 위반
        인터페이스 의존 o, 구현 클래스 의존 o -> DIP 위반
     */
//    private static MemberRepository memberRepository = new MemoryMemberRepository();
//    private static DiscountPolicy discountPolicy = new FIxDiscountPolicy();
//    private static DiscountPolicy discountPolicy = new RateDiscountPolicy();

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    /*
        수정자 주입
        단위 테스트 시 문제가 발생
     */
//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }
//    @Autowired
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        this.discountPolicy = discountPolicy;

//    }
    /*
        생성자 주입
        인터페이스 의존 o, 구현클래스 의존 x
        순수 자바 단위 테스트 시 필요 구현 객체를 확인하기 좋다
        final 키워드 사용 가능 ( 초기값 이나 생성자 주입 값 이외에 변경 불가능 )
     */
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository,@MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {

        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
