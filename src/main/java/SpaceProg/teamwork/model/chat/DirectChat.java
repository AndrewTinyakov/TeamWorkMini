package SpaceProg.teamwork.model.chat;

import SpaceProg.teamwork.model.User;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@NoArgsConstructor
@Entity
@DiscriminatorValue("DC")
public class DirectChat extends ChatAbstract {

    @OneToOne
    @JoinColumn(name = "user_id_to")
    private User directTo;

}
