package dev.subashcodes.librarymangement.service;

import dev.subashcodes.librarymangement.exception.LibraryMgmtException;
import dev.subashcodes.librarymangement.model.Member;

import java.util.List;

public interface MemberService {


    public Member addNewMember(Member member)  throws LibraryMgmtException;

    public Member updateMemberInfo(String memberId, Member updatedMember) throws LibraryMgmtException;

    public Member getMemberInfo(String memberId) throws LibraryMgmtException;

    public List<Member> getAllMembers() throws LibraryMgmtException;

    public void deleteMember(String memberId) throws LibraryMgmtException;



}
