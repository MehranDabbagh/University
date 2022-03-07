package D.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Prof {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String type;
@OneToMany
private Set<Course> courseList;

}
