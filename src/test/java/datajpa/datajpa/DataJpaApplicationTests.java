package datajpa.datajpa;

import datajpa.datajpa.dto.MemberDto;
import datajpa.datajpa.entity.Member;
import datajpa.datajpa.entity.Team;
import datajpa.datajpa.repository.MemberRepo;
import datajpa.datajpa.repository.TeamRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.security.sasl.SaslServer;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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

	@Test
	public void returnTypeTest() {
		Member m1 = new Member("AAA", 10);
		Member m2 = new Member("BBB", 10);
		memberRepo.save(m1);
		memberRepo.save(m2);

		List<Member> findListUserName = memberRepo.findListByUsername("AAA");
		Member findMemberUserName = memberRepo.findMemberByUsername("AAA");
		Optional<Member> findOptionalUserName = memberRepo.findOptionalByUsername("AAA");

		for(Member member: findListUserName) {
			System.out.println("result 1 " +member);
		}
		System.out.println("result 2 " +findMemberUserName);

		System.out.println("result 3 " + findOptionalUserName.get());

	}

	@Test
	public void pageTest() {

		//given
		memberRepo.save(new Member("member1", 10));
		memberRepo.save(new Member("member2", 10));
		memberRepo.save(new Member("member3", 10));
		memberRepo.save(new Member("member4", 10));
		memberRepo.save(new Member("member5", 10));
		memberRepo.save(new Member("member6", 10));

		int age = 10;

		PageRequest pageRequest = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "username"));

		//when
		Page<Member> pageResult = memberRepo.findByAge(age, pageRequest);
		// Slice로 변경 가능 repository에서 반환 타입도 Slice로 바꿔줘야한다, 모바일 앱에서 리스트 페이지에서 더보기 기능 사용 가능
		//Slice<Member> sliceResult = memberRepo.findByAge(age, pageRequest);

		Page<MemberDto> toMap = pageResult.map(m -> new MemberDto(m.getId(), m.getUsername(), null));

		//then

		//1
		List<Member> content = pageResult.getContent();

		for(Member member: content) {
			System.out.println("member = " + member);
		}

		long totalElements = pageResult.getTotalElements();
		System.out.println("totalElements = " + totalElements);

		//2
		// 첫 페이지 내용 사이즈
		assertThat(pageResult.getContent().size()).isEqualTo(3);
		// 전체 결과 값
		assertThat(pageResult.getTotalElements()).isEqualTo(6);
		// 몇번째 페이지인지
		assertThat(pageResult.getNumber()).isEqualTo(0);
		// 전체 페이징 수
		assertThat(pageResult.getTotalPages()).isEqualTo(2);
		// 첫번째 페이지 인지(페이지는 0 부터 시작)
		assertThat(pageResult.isFirst()).isTrue();
		// 다음 페이지가 있는지 여부
		assertThat(pageResult.hasNext()).isTrue();

	}

}
