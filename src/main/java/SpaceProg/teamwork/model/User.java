package SpaceProg.teamwork.model;

import SpaceProg.teamwork.model.chat.ChatAbstract;
import SpaceProg.teamwork.payload.request.securityRequest.SingupRequest;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false, unique = true, updatable = false)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "login", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private Image avatar;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "users_colleagues",
            joinColumns = @JoinColumn(name = "user_id1"),
            inverseJoinColumns = @JoinColumn(name = "user_id2")
    )
    private Set<User> colleaguesFirst;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "users_colleagues",
            joinColumns = @JoinColumn(name = "user_id2"),
            inverseJoinColumns = @JoinColumn(name = "user_id1")
    )
    private Set<User> colleaguesSecond;

    @OneToMany(mappedBy = "recipient")
    private Set<ColleaguesInvite> colleaguesInvites;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "users_chats",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "chat_id")
    )
    private Set<ChatAbstract> chats;

    @OneToMany(mappedBy = "user")
    private Set<Post> posts;

    @OneToMany(mappedBy = "user")
    private List<Message> messages;


    public User(SingupRequest singupRequest) {
        this.setUsername(singupRequest.getUsername());
        this.setPassword(singupRequest.getPassword());
        this.setEmail(singupRequest.getEmail());
        this.setName(singupRequest.getName());
        this.setSurname(singupRequest.getSurname());
    }

}