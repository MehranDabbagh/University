package D.Service.Impl;

import D.Entities.Prof;
import D.MyConnection.SessionFactorySingleton;
import D.Repository.Impl.ProfRepositoryImpl;
import D.Service.ProfService;
import lombok.var;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ProfServiceImpl implements ProfService {
    private ProfRepositoryImpl profRepository;
    protected SessionFactory sessionFactory = SessionFactorySingleton.getInstance();

    public ProfServiceImpl() {
        profRepository = new ProfRepositoryImpl();
    }


    @Override
    public Integer login(Prof prof) {
        try (var session = sessionFactory.openSession()) {
            var transaction = sessionFactory.getCurrentSession().getTransaction();
            try {
                transaction.begin();
                List<Prof> profs = profRepository.findAll();
                List<Prof> profs1 = profs.stream().filter(x -> Objects.equals(x.getUsername(), prof.getUsername()) && Objects.equals(x.getPassword(), prof.getPassword())).collect(Collectors.toList());
                transaction.commit();
                if (profs1.size() > 0) {
                    return profs1.get(0).getId();
                }
                return 0;
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }

    }

    @Override
    public Integer create(Prof prof) {

        try (var session = sessionFactory.openSession()) {
            var transaction = sessionFactory.getCurrentSession().getTransaction();
            try {
                transaction.begin();
                Integer id = profRepository.save(prof).getId();
                transaction.commit();
                return id;
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }
    }

    @Override
    public Prof findById(Integer id) {
        try (var session = sessionFactory.openSession()) {
            var transaction = sessionFactory.getCurrentSession().getTransaction();

            try {
                transaction.begin();
                Prof prof = profRepository.findById(id);
                transaction.commit();
                return prof;
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }
    }

    @Override
    public List<Prof> findAll() {

        try (var session = sessionFactory.openSession()) {
            var transaction = sessionFactory.getCurrentSession().getTransaction();

            try {
                transaction.begin();
                List<Prof> profs = profRepository.findAll();
                transaction.commit();
                return profs;
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }
    }

    @Override
    public void Update(Prof prof) {
        try (var session = sessionFactory.openSession()) {
            var transaction = sessionFactory.getCurrentSession().getTransaction();
            try {
                transaction.begin();
                profRepository.update(prof);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }
    }

    @Override
    public void Delete(Integer id) {
        try (var session = sessionFactory.openSession()) {
            var transaction = sessionFactory.getCurrentSession().getTransaction();
            try {
                transaction.begin();
                profRepository.deleteById(id);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }
    }
}
