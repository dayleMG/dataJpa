package datajpa.datajpa.entity;

import datajpa.datajpa.repository.MemberRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@SpringBootTest
@Transactional
@Rollback(value = false)
public class MemberTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    MemberRepo memberRepo;


    @Test
    public void JpaEventBaseEntity() throws Exception {
        //given
        Member member = new Member("member1", 10);
        memberRepo.save(member);
        Thread.sleep(100);
        member.setUsername("member2");

        em.flush();
        em.clear();

        //when
        Member findMember = memberRepo.findById(member.getId()).get();


        //then
        System.out.println("createdDate = " + findMember.getCreatedDate());
        System.out.println("lastModifiedDated = " + findMember.getLastModifiedDate());
        System.out.println("createdBy = " + findMember.getCreatedBy());
        System.out.println("lastModifiedBy = " + findMember.getLastModifiedBy());

    }
}
