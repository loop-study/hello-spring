package hello.hellospring;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 자바로 스프링 빈 등록방법. @Service, @Repository, @Autowired 없이 진행.
 */
@Configuration
public class SpringConfig {

    /*
    // jdbc 방식
    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }*/

    /*
    // jpa 엔티티매니저 방식
    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }
*/

    private final MemberRepository memberRepository;

    // @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

//    @Bean
//    public MemberRepository memberRepository() {
//       return new MemoryMemberRepository();
//        return new JdbcMemberRepository(dataSource);
//        return new JdbcTempleteMemberRepository(dataSource);
//        return new JpaMemberRepository(em);
//
//    }

//    @Bean // 컴포넌트 스캔으로 주석처리
//    public TimeTraceAop timeTraceAop() {
//        return new TimeTraceAop();
//    }

}
