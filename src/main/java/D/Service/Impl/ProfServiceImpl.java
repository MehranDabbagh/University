package D.Service.Impl;

import D.Entities.Employee;
import D.Entities.Prof;
import D.Repository.Impl.ProfRepositoryImpl;
import D.Service.ProfService;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ProfServiceImpl implements ProfService {
    private ProfRepositoryImpl profRepository;

    public ProfServiceImpl() {
        profRepository=new ProfRepositoryImpl();
    }


    @Override
    public Integer login(Prof prof) {
        List<Prof> profs =  profRepository.findAll();
        List<Prof> profs1=      profs.stream().filter(x-> Objects.equals(x.getUsername(), prof.getUsername()) && Objects.equals(x.getPassword(), prof.getPassword())).collect(Collectors.toList());
        if(profs1.size()>0){
            return profs1.get(0).getId();
        }
        return 0;
    }

    @Override
    public Integer create(Prof prof) {
        return profRepository.create(prof);
    }

    @Override
    public Prof findById(Integer id) {
        return profRepository.findById(id);
    }

    @Override
    public List<Prof> findAll() {
        return profRepository.findAll();
    }

    @Override
    public void Update(Prof prof) {
profRepository.Update(prof);
    }

    @Override
    public void Delete(Integer id) {
profRepository.Delete(id);
    }
}
