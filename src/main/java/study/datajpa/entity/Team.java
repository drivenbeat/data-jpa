package study.datajpa.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "name"}) // 연관관계는 무한 loop 가능
public class Team {

    @Id @GeneratedValue
    @Column(name = "team_id")
    private Long id;
    private String name;

    // member 의 입장과 반대
    @OneToMany(mappedBy = "team") // mapped by fk가 없는 쪽에 걸자
    private List<Member> members = new ArrayList<>();

    public Team(String name) {
        this.name = name;
    }
}
