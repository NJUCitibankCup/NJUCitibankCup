package nju.citicup.data.test;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TestRepository extends CrudRepository<TestEntity, Long> {

    List<TestEntity> findByLastName(String lastName);
}
