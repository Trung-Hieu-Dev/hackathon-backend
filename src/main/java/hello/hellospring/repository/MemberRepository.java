package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;


public interface MemberRepository {
    // registration
    Member save(Member member);
    
    // Retrieval Id
    Optional<Member> findById(Long id);
    
    // Retrieval name
    Optional<Member> findByName(String name);
    
    // Retrieval All
    List<Member> findAll();
    
}
