package org.tutorials.wproject2.test.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.tutorials.wproject2.test.model.Group;

@Repository
public interface  GroupRepository extends CrudRepository<Group, Long> {



}
