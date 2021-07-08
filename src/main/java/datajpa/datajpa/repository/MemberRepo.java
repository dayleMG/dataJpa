package datajpa.datajpa.repository;

import datajpa.datajpa.dto.MemberDto;
import datajpa.datajpa.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface MemberRepo extends JpaRepository<Member, Long> {

    //@Query 기능
    @Query("select m from Member m where m.username = :username and m.age = :age")
    List<Member> findUser(@Param("username") String username, @Param("age") int age);

    @Query("select m.username from Member m")
    List<String> findUsernameList();

    //Query 값 Dto로 조회하기
    @Query("select new datajpa.datajpa.dto.MemberDto(m.id, m.username, t.name)  from Member m join m.team t")
    List<MemberDto> findMemberDto();

    // 컬렉션 객체 in절
    @Query("select m from Member m where m.username in :names")
    List<Member> findNames(@Param("names") List<String> names);
}
