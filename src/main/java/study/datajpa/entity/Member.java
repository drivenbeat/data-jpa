package study.datajpa.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "user", "age"}) // 연관관계는 무한 loop 가능
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String username;
    private int age;

    // 멤버의 입장에서 보면 한 팀에 멤버가 다수니까 멤버가 N : 팀 1
    @ManyToOne(fetch = FetchType.LAZY) // 무조건 LAZY 설정 - 지연로딩으로 처리
    @JoinColumn(name = "team_id") // fk name
    private Team team;

    public Member(String username) {
        this.username = username;
    }

    public Member(String username, int age, Team team) {
        this.username = username;
        this.age = age;
        if(team != null) {
            changeTeam(team);
        }
    }


    // 연관관계
    public void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }

}
