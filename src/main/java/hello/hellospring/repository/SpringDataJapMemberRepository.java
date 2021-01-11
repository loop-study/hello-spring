package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJapMemberRepository extends JpaRepository<Member, Long>, MemberRepository  {

    // 아니 이게 끝??
    // 스스로 구현체 만들어서 제공해줌 ㄷㄷㄷㄷ
    @Override
    Optional<Member> findByName(String name);
}
