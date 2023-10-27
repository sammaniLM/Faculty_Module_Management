package com.assignment.unistudentmanagement.module;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ModuleService {

    @Autowired
    private ModuleRepository repository;

   /* public long getModuleCount(){
        return repository.count();
    }*/

    public List<Module> viewAll(){
        return (List<Module>) repository.findAll();
    }

    public void saveModule(Module module){
        repository.save(module);
    }

    public Module get(Integer moduleid) throws ModuleNotFoundException{
        Optional<Module> result = repository.findById(moduleid);
        if(result.isPresent()){
            return result.get();
        }
        throw new ModuleNotFoundException("Module not found under given Module ID" + moduleid);
    }

    public void deleteModule(Integer moduleid) throws ModuleNotFoundException{
        Long count = repository.countByModuleid(moduleid);
        if (count == null || count == 0){
            throw new ModuleNotFoundException("Module not found under given Module ID" + moduleid);
        }
        repository.deleteById(moduleid);
    }
}
