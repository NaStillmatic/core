package hello.core.service;

import hello.core.member.Member;
import hello.core.repository.MemberRepository;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService {

    final private MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }


    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
