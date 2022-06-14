package az.spring.hibernate.dao;

import az.spring.hibernate.model.Employee;

import java.util.List;
import java.util.Optional;

public interface CrudDAO<Entity,ID> {

    void save(Entity entity);

    void delete(ID id);

    void update(Entity entity);

    Optional<Entity> findById(ID id);

    List<Entity> findAll();

}
