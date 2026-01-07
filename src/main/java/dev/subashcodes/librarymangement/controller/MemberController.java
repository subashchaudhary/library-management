package dev.subashcodes.librarymangement.controller;


import dev.subashcodes.librarymangement.exception.LibraryMgmtException;
import dev.subashcodes.librarymangement.model.Member;
import dev.subashcodes.librarymangement.pojo.Response;
import dev.subashcodes.librarymangement.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping("/add")
    public Response addNewMember(@RequestBody Member member, @RequestHeader HttpHeaders httpHeaders) {

        String user = httpHeaders.getFirst("User");
        String secretCode = httpHeaders.getFirst("SecretCode");
        Response response = new Response();

        try{
         Member savedMember = memberService.addNewMember(member, user, secretCode);
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
    public Response updateMemberInfo(@PathVariable("memberId") String memberId, @RequestBody Member updatedMember, @RequestHeader HttpHeaders httpHeaders) {

        String user = httpHeaders.getFirst("User");
        String secretCode = httpHeaders.getFirst("SecretCode");
        Response response = new Response();
        try {
            Member updatedInfo = memberService.updateMemberInfo(memberId, updatedMember, user, secretCode);
            response.setStatus("Success");
            response.setMessage( "Member info updated successfully for memberId: " + memberId);
            response.setData(updatedInfo);
        } catch (LibraryMgmtException e) {
            String errorMessage =  "Failed to update member info: " + e.getMessage();
            response.setStatus("Failure");
            response.setMessage(errorMessage);
        }

        return response;

    }


    @GetMapping("/{memberId}")
    public Response getMemberInfo(@PathVariable("memberId") String memberId, @RequestHeader HttpHeaders httpHeaders) {
        String user = httpHeaders.getFirst("User");
        String secretCode = httpHeaders.getFirst("SecretCode");
        Response response = new Response();
        try {
            Member memberObj =  memberService.getMemberInfo(memberId, user, secretCode);
            response.setMessage("Member Found Successfully");
            response.setStatus("Success");
            response.setData(memberObj);
        } catch (LibraryMgmtException e) {

           String errorMessage =  e.getMessage();
           response.setMessage(errorMessage);
           response.setStatus("Failure");

        }

        return response;

    }

    @GetMapping("/all")
    public Response getAllMembers(@RequestHeader HttpHeaders httpHeaders) {
        String user = httpHeaders.getFirst("User");
        String secretCode = httpHeaders.getFirst("SecretCode");
        Response response = new Response();
        try {
            List<Member> memberList =  memberService.getAllMembers(user, secretCode);
            response.setStatus("Success");
            response.setMessage("Members fetched successfully");
            response.setData(memberList);
        }catch (LibraryMgmtException ex){
            String errorMessage = ex.getMessage();
            response.setStatus("Failure");
            response.setMessage("No members found: " + errorMessage);
        }

        return response;
    }

    @DeleteMapping("/delete/{memberId}")
    public Response deleteMember(@PathVariable("memberId") String memberId, @RequestHeader HttpHeaders httpHeaders) {
        String user = httpHeaders.getFirst("User");
        String secretCode = httpHeaders.getFirst("SecretCode");
        Response response = new Response();
        try {
            memberService.deleteMember(memberId, user, secretCode);
            response.setStatus("Success");
            response.setMessage("Member deleted successfully with memberId: " + memberId);
        }catch (LibraryMgmtException ex) {
            String errorMessage = ex.getMessage();
            response.setStatus("Failure");
            response.setMessage("Failed to delete member: " + errorMessage);
        }
        return response;
    }
}


