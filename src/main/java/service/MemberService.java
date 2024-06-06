package service;

import domain.Member;
import org.springframework.transaction.annotation.Transactional;
import repository.MemberRepository;

import java.util.List;
import java.util.Optional;

@Transactional // 데이터 저장 또는 수정 시 필요
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Long join(Member member, String password, String confirmPassword) {
        validateDuplicateMember(member); // 중복 회원 검증
        if (checkPassword(password, confirmPassword)) {
            member.setMemberPwd(password);
            memberRepository.save(member);
            return member.getId();
        } else {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    }

    private void validateDuplicateMember(Member member) {
        // 회원 중복 확인
        memberRepository.findByMemberId(member.getMemberId())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    public Optional<Member> login(String memberId, String memberPwd) {
        // 회원 이름으로 찾고 비밀번호가 일치하는지 확인
        return memberRepository.findByMemberId(memberId)
                .filter(member -> member.getMemberPwd().equals(memberPwd));
    }

    private boolean checkPassword(String password, String confirmPassword) {
        // 비밀번호와 비밀번호 확인이 일치하는지 검증
        return password != null && password.equals(confirmPassword);
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
