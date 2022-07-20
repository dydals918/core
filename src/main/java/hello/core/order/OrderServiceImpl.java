package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FIxDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    /*
        확장 o, 변경 o -> OCP 위반
        인터페이스 의존 o, 구현 클래스 의존 o -> DIP 위반
     */
//    private static MemberRepository memberRepository = new MemoryMemberRepository();
//    private static DiscountPolicy discountPolicy = new FIxDiscountPolicy();
//    private static DiscountPolicy discountPolicy = new RateDiscountPolicy();

    /*
        생성자 주입
        인터페이스 의존 o, 구현클래스 의존 x
     */
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {

        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
