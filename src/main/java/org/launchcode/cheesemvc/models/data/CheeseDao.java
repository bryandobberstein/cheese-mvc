package org.launchcode.cheesemvc.models.data;

import org.launchcode.cheesemvc.models.Cheese;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by max on 5/19/17.
 */
@Repository
@Transactional
public interface CheeseDao extends CrudRepository<Cheese, Integer> {
}
