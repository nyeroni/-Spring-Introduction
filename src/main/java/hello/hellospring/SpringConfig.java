package hello.hellospring;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    //@PersistenceContext
   /* private EntityManager em;
    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }*/
   /* private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource){
        this.dataSource = dataSource;
    }*/
    private final MemberRepository memberRepository;
    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository);
        //command + P => 넣어줘야할것 보여줌
    }
   /*
    @Bean
    public MemberRepository memberRepository(){

       // return new MemoryMemberRepository();
       // return new JdbcMemberRepository(dataSource);
      //  return new JdbcTemplateMemberRepository(dataSource);
        //return new JpaMemberRepository(em);

    }

    */
 /*   @Bean
    public TimeTraceAop timeTraceAop(){
        return new TimeTraceAop();
    }
    */

}
