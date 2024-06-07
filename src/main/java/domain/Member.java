package domain;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Member {
    @Id
    private Long id;

    private String memberId;
    private String name;
    private String memberPwd;
    private String nickname;
}
