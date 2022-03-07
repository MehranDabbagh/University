package D.Repository.Impl;

import D.Entities.Course;
import D.Entities.Employee;
import D.Repository.EmployeeRepository;

import javax.persistence.criteria.CriteriaBuilder;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class EmployeeRepositoryImpl extends GenericRepositoryImpl<Employee, Integer> {
}
