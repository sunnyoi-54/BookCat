package controller;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberForm {
    private String memberId;
    private String name;
    private String memberPwd;
    private String nickname;
    private String email;
}
