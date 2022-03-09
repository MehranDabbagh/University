package D.Entities;

import D.Entities.Base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class Person extends BaseEntity {
    private String firstname;
    private String lastname;
    private String username;
    private String password;
}
