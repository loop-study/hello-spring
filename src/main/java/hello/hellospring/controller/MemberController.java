package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    //DI 방식

    // 1. 세퍼인젝션 방식.
    // 단점 누군가 호출할 때 public 으로 열려있어야하는데. 노출도 되고 중간에 잘못 바뀔 수 있다.
    // 원래 한번 세팅하면 바뀔 일이 없음.
    private MemberService memberService;

    @Autowired
    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }

//    @Autowired private final MemberService memberService;

    // 2. 필드 주
    //    @Autowired private MemberService memberService; // 필드 주입. 안좋은 방식. -> 테스트할 때 스프링컨테이너가 없기에 마음대로 의존을 바꿀 수 없다. ㄷ

    // 3. 생성자 주입이라고 함.
//    @Autowired
//    public MemberController(MemberService memberService) {
//        this.memberService = memberService;
//    }


    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
