package dev.subashcodes.librarymangement.service;

import dev.subashcodes.librarymangement.exception.LibraryMgmtException;
import dev.subashcodes.librarymangement.model.Member;

import java.util.List;

public interface MemberService {


    public Member addNewMember(Member member, String user, String secretCode)  throws LibraryMgmtException;

    public Member updateMemberInfo(String memberId, Member updatedMember, String user, String secretCode) throws LibraryMgmtException;

    public Member getMemberInfo(String memberId, String user, String secretCode) throws LibraryMgmtException;

    public List<Member> getAllMembers(String user, String secretCode) throws LibraryMgmtException;

    public void deleteMember(String memberId, String user, String secretCode) throws LibraryMgmtException;



}
