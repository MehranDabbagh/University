package D.Entities;


import D.Entities.Base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Course extends BaseEntity {
    private String name;
    @ManyToOne
    private Prof prof;
    private Integer year;
    private Integer term;
    private int unit;


}
