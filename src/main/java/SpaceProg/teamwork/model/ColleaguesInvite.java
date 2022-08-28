package SpaceProg.teamwork.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "colleagues_invite")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class ColleaguesInvite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "invite_id")
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "user_id2")
    @JsonIgnore
    User recipient;

    @ManyToOne()
    @JoinColumn(name = "user_id1")
    @JsonIgnore
    User sender;

    public ColleaguesInvite(User sender, User recipient) {
        this.sender = sender;
        this.recipient = recipient;
    }
}