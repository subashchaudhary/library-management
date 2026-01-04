package dev.subashcodes.librarymangement.controller;


import dev.subashcodes.librarymangement.exception.LibraryMgmtException;
import dev.subashcodes.librarymangement.model.Member;
import dev.subashcodes.librarymangement.pojo.Response;
import dev.subashcodes.librarymangement.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping("/add")
    public Response addNewMember(@RequestBody Member member) {

        Response response = new Response();

        try{
         Member savedMember = memberService.addNewMember(member);
         response.setStatus("Success");
         response.setMessage("New member added successfully");
         response.setData(savedMember);

        }catch (LibraryMgmtException ex){
            String message =  "Failed to add new member: " + ex.getMessage();
            response.setMessage(message);
            response.setStatus("Failure");
        }


        return response;
    }

    @PutMapping("/update/{memberId}")
    public String updateMemberInfo(@PathVariable("memberId") String memberId, @RequestBody Member updatedMember) {


        return "Member info updated successfully for memberId: " + memberId;
    }


    @GetMapping("/{memberId}")
    public Member getMemberInfo(@PathVariable("memberId") String memberId) {

        try {
            return memberService.getMemberInfo(memberId);
        } catch (LibraryMgmtException e) {
            return  null;

        }

    }

    @GetMapping("/all")
    public List<Member> getAllMembers() {

        return null;
    }

    @DeleteMapping("/delete/{memberId}")
    public String deleteMember(@PathVariable("memberId") String memberId) {

        return "Member deleted successfully with memberId: " + memberId;
    }
}


