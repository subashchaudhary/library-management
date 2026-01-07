package dev.subashcodes.librarymangement.service;

import dev.subashcodes.librarymangement.exception.LibraryMgmtException;
import dev.subashcodes.librarymangement.model.Member;
import dev.subashcodes.librarymangement.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
public class MemberServiceImpl implements MemberService{

    @Autowired

    private MemberRepository memberRepository;


    @Override
    public Member addNewMember(Member member, String user, String secretCode) throws LibraryMgmtException {

        member.setMembershipDate(LocalDate.now());
        member.setActive(true);
        Member savedMember = memberRepository.save(member);

        if(savedMember == null){
            throw new LibraryMgmtException("Failed to add new member");
        }
        return savedMember;
    }

    @Override
    public Member updateMemberInfo(String memberId, Member updatedMember,  String user, String secretCode) throws LibraryMgmtException {

        //check if member exists
       Member memberExist =  getMemberInfo(memberId, user, secretCode);

       if(memberExist == null){
           throw new LibraryMgmtException("Member not found with id: " + memberId);
       }

      return memberRepository.save(updatedMember);
    }

    @Override
    public Member getMemberInfo(String memberId,  String user, String secretCode) throws LibraryMgmtException {

        return memberRepository.findById(memberId).orElse(null);
    }

    @Override
    public List<Member> getAllMembers( String user, String secretCode) throws LibraryMgmtException {
        return memberRepository.findAll();
    }

    @Override
    public void deleteMember(String memberId,  String user, String secretCode) throws LibraryMgmtException {

        memberRepository.deleteById(memberId);
    }
}
