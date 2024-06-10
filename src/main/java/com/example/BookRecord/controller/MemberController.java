package com.example.BookRecord.controller;

import com.example.BookRecord.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.BookRecord.service.MemberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/login";
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "members/register";
    }

    @PostMapping("/register")
    public String register(
            @ModelAttribute MemberForm memberForm,
            RedirectAttributes redirectAttributes) {

        Member member = new Member();
        member.setMemberId(memberForm.getMemberId());
        member.setMemberPwd(memberForm.getMemberPwd());
        member.setName(memberForm.getName());
        member.setNickname(memberForm.getNickname());

        try {
            memberService.join(member, memberForm.getMemberPwd(), memberForm.getCheckPwd());
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/register";
        }

        redirectAttributes.addFlashAttribute("successMessage", "회원가입에 성공하였습니다.");
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "members/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute MemberForm form, HttpServletRequest request) {
        Optional<Member> memberOptional = memberService.login(form.getMemberId(), form.getMemberPwd());
        if (memberOptional.isEmpty()) {
            return "redirect:/login";
        }

        HttpSession session = request.getSession();
        session.setAttribute("loginMember", memberOptional.get());
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
