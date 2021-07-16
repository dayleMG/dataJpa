package datajpa.datajpa.controller;

import datajpa.datajpa.dto.MemberDto;
import datajpa.datajpa.entity.Member;
import datajpa.datajpa.repository.MemberRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepo memberRepo;

    @GetMapping("/members")
    public Page<MemberDto> members(@PageableDefault(size = 5) Pageable pageable) {
        Page<Member> result = memberRepo.findAll(pageable);
        Page<MemberDto> map = result.map(m -> new MemberDto(m.getId(), m.getUsername(), null));
        return map;

    }

}
