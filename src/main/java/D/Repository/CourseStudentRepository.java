package D.Repository;

import D.Entities.Base.BaseEntity;
import D.Entities.Course;
import D.Entities.CourseStudent;
import D.Repository.Base.BaseRepository;
import D.Repository.Impl.GenericRepositoryImpl;

public interface CourseStudentRepository extends BaseRepository<CourseStudent,Integer> {
}
