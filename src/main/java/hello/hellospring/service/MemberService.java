package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
//@Service
//@Component
//command +  [ => 이전으로 돌아가기
@Transactional
public class MemberService {
//command + shift + T => Test 만들기
    private MemberRepository memberRepository;
    //@Autowired
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }
    //command +  B => 이동
    /**
     * 회원가입
     */

    public Long join(Member member){
        // 같은 이름이 있는 중복 회원 X
        /**Optional<Member> result = memberRepository.findByName(member.getName());
        //단축기 command + option + V => Optioanl 자동 생성
        result.ifPresent(m->{
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });**/
        //null일 가능성이 있으면 Optional으로 한 번 감싸준다
        //그냥 꺼내고 싶으면 get으로 해주면 됨
        //Member member1 = result.get(); 권장하지는 않는 방법

        //orElseGet도 많이 사용
        //result.orElseGet();
        //값이 있으면 꺼내고 없으면 메소드를 실행해 default값을 꺼냄

        //Optional을 쓸 때 그냥 반환하는 것은 좋지 않음
        //Optional<Member> result를 그냥 지워버리고 바로 .ifPresent가 들어감

    //    long start = System.currentTimeMillis();

       // try{
            validateDuplicateMember(member);//중복회원 검증
            //=> 메소드로 뽑는 것이 좋음
            //=> 단축키는 control + T

            memberRepository.save(member);//통과하면 저장
            return member.getId();
        // }finally{
       //     long finish = System.currentTimeMillis();
         ///   long timeMs = finish - start;
       //     System.out.println("join = " + timeMs +"ms");
       // }
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m->{
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers(){
      //  long start = System.currentTimeMillis();
        //try{
            return memberRepository.findAll();

        //}finally{
          //  long finish = System.currentTimeMillis();
            //long timeMs = finish - start;
            //System.out.println("findMembers = "+ timeMs + "ms");
        //}
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
