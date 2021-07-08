package datajpa.datajpa;

import datajpa.datajpa.dto.MemberDto;
import datajpa.datajpa.entity.Member;
import datajpa.datajpa.entity.Team;
import datajpa.datajpa.repository.MemberRepo;
import datajpa.datajpa.repository.TeamRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class DataJpaApplicationTests {

	@Autowired
	MemberRepo memberRepo;

	@Autowired
	TeamRepo teamRepoRepo;

	@Test
	public void testQuery() {
		Member m1 = new Member("AAA",10);
		Member m2 = new Member("BBB", 10);
		memberRepo.save(m1);
		memberRepo.save(m2);

		List<Member> result = memberRepo.findUser("AAA", 10);
		assertThat(result.get(0)).isEqualTo(m1);
	}

	@Test
	public void findMemberDto() {
		Team team = new Team("teamA");
		teamRepoRepo.save(team);

		Member m1 = new Member("AAA", 10, team);
		memberRepo.save(m1);

		List<MemberDto> memberDto = memberRepo.findMemberDto();

		for(MemberDto dto: memberDto) {
			System.out.println("dto = " + dto);
		}
	}

	@Test
	public void findByNames() {
		Member m1 = new Member("AAA",10);
		Member m2 = new Member("BBB", 10);
		memberRepo.save(m1);
		memberRepo.save(m2);

		List<Member> result = memberRepo.findNames(Arrays.asList("AAA", "BBB"));
		for (Member member: result) {
			System.out.println("result = " + member);
		}

	}


}
