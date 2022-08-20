package SpaceProg.teamwork.model;

import SpaceProg.teamwork.payload.request.createRequest.CreatePostRequest;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true )
@Table(name = "posts")
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id", nullable = false, unique = true, updatable = false)
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "title")
    private String header;

    @Column(name = "text")
    private String text;

    @Column(name = "post_time")
    private Date sendingTime;

    @Column(name = "fixed")
    private boolean fixed;

    public Post(CreatePostRequest newPost, User user) {
        this.user = user;
        this.header = newPost.getHeader();
        this.text = newPost.getText();
        this.sendingTime = new Date();

        this.fixed = false;
    }

}