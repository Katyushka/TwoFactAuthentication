package com.towFactAuth.repository;

/**
 * Created by user on 22.05.16.
 */
import com.towFactAuth.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findByLogin(String login);

    User findOne(String login);

    User findById(Long id);

}