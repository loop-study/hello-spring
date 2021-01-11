package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {

    // JPA 는 EntityManager로 돌아간다
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        // 쿼리 짜는걸 jpql 이라고 한다. jdbc 와 무슨 차이인가? 별차이 없어 보임.
        // 엔티티명을 제대로 명시해야 에러 안남. member 에러발생. Member 동작.
        // 더 발전된 기능을 사용하면 안짜도 됨
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList()
                .stream()
                .findAny();
    }

    @Override
    public List<Member> findAll() { // m.* or * 이거나 컬럼 표시인데... 단순하고 최고.
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}
