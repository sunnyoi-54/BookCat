package controller;

import domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import service.MemberService;

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
    public String registerForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "members/registerMemberForm";
    }

    @PostMapping("/register")
    public String register(
            @RequestParam String memberId,
            @RequestParam String memberPwd,
            @RequestParam String checkPwd,
            @RequestParam String name,
            @RequestParam String nickname,
            RedirectAttributes redirectAttributes) {

        Member member = new Member();
        member.setMemberId(memberId);
        member.setMemberPwd(memberPwd);
        member.setName(name);
        member.setNickname(nickname);

        try {
            memberService.join(member, memberPwd, checkPwd);
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/";
        }

        redirectAttributes.addFlashAttribute("successMessage", "회원가입에 성공하였습니다.");
        return "redirect:/";
    }

    @GetMapping("/home")
    public String loginForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "members/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute MemberForm form, HttpServletRequest request) {
        Optional<Member> memberOptional = memberService.login(form.getMemberId(), form.getMemberPwd());
        if (memberOptional.isEmpty()) {
            return "redirect:/home";
        }

        HttpSession session = request.getSession();
        session.setAttribute("loginMember", memberOptional.get());
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}