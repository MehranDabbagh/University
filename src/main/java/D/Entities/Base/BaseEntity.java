package D.Entities.Base;

import D.Entities.Course;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;


}
