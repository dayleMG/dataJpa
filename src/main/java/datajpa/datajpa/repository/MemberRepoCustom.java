package datajpa.datajpa.repository;

import datajpa.datajpa.entity.Member;

import java.util.List;

public interface MemberRepoCustom {
    List<Member> findMemberCustom();
}
