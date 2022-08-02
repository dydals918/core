package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.*;

public class PrototypeTest {

    /*
        프로토타입 빈
        스프링 컨테이너에서 빈을 조회시 생성 및 초기화 메서드 실행
        스프링 컨테이너가 생성과 초기화만 관여. 관리 X
        따라서 destroy 메서드와 같은 종료 콜백이 실행되지 않는다.
     */
    @Test
    @DisplayName("프로토타입 빈 테스트")
    void prototypeBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBeanConfig.class);
        System.out.println("find prototypeBeanFind1");
        PrototypeBeanConfig prototypeBean1 = ac.getBean(PrototypeBeanConfig.class);
        System.out.println("find prototypeBeanFind2");
        PrototypeBeanConfig prototypeBean2 = ac.getBean(PrototypeBeanConfig.class);

        System.out.println("prototypeBean1 = " + prototypeBean1);
        System.out.println("prototypeBean2 = " + prototypeBean2);
        assertThat(prototypeBean1).isNotSameAs(prototypeBean2);

        prototypeBean1.destroy();
        prototypeBean2.destroy();
        ac.close();
    }

    @Scope("prototype")
    static class PrototypeBeanConfig {

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBeanConfig.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBeanConfig.destroy");
        }
    }

}
