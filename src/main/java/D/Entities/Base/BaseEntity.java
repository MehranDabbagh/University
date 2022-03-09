package D.Entities.Base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer Id;

}
