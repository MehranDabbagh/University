package D.Entities;

import D.Entities.Base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.criteria.CriteriaBuilder;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseStudent extends BaseEntity {
    private Integer course;
    private Integer student;
    private Integer score;

}
