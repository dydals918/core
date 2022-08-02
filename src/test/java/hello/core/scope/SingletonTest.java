package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.*;

public class SingletonTest {

    @Test
    @DisplayName("싱글톤 빈 테스트")
    void singletonBeanFind() {

        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletonBeanConfig.class);

        SingletonBeanConfig singletonBeanConfig1 = ac.getBean(SingletonBeanConfig.class);
        SingletonBeanConfig singletonBeanConfig2 = ac.getBean(SingletonBeanConfig.class);
        System.out.println("singletonBeanConfig1 = " + singletonBeanConfig1);
        System.out.println("singletonBeanConfig2 = " + singletonBeanConfig2);
        assertThat(singletonBeanConfig1).isSameAs(singletonBeanConfig2);
        ac.close();
    }

    @Scope("singleton")
    static class SingletonBeanConfig {

        @PostConstruct
        public void init() {
            System.out.println("SingletonBeanConfig.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("SingletonBeanConfig.destroy");
        }
    }
}
