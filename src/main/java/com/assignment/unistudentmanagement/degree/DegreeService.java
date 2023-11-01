package com.assignment.unistudentmanagement.degree;

import com.assignment.unistudentmanagement.student.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DegreeService {

    @Autowired
    private DegreeRepository repository;

    public long getDegreeCount() {
        return repository.count();
    }

    public List<Degree> viewAll(){
        return (List<Degree>) repository.findAll();
    }

    public void saveDegree(Degree degree){
        repository.save(degree);
    }

    public Degree get(Integer degreeid) throws DegreeNotFoundException{
        Optional<Degree> result = repository.findById(degreeid);
        if(result.isPresent()){
            return result.get();
        }
        throw new DegreeNotFoundException("Degree not found under given Degree ID" + degreeid);
    }

    public void deleteDegree(Integer degreeid) throws DegreeNotFoundException{
        Long count = repository.countByDegreeid(degreeid);
        if (count == null || count == 0){
            throw new DegreeNotFoundException("Degree not found under given Degree ID" + degreeid);
        }
        repository.deleteById(degreeid);
    }
}
