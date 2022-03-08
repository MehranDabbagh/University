package D.Repository.Impl;

import D.Entities.Course;
import D.Entities.Employee;
import D.Entities.Prof;
import D.Repository.ProfRepository;
import lombok.var;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ProfRepositoryImpl extends GenericRepositoryImpl<Prof,Integer> implements ProfRepository{
    @Override
    public List<Prof> findAll() {
        Session session = sessionFactory.openSession();
        Query q = session.createQuery("from Prof ");
        List<Prof> profs = (List<Prof>) q.getResultList();

        return profs;
    }

    @Override
    public Prof findById(Integer id) {
        try (var session = sessionFactory.openSession()) {
            return session.find(Prof.class, id);
        }
    }

}
