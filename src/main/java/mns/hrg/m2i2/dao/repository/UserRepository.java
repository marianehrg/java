package mns.hrg.m2i2.dao.repository;

import mns.hrg.m2i2.dao.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findByPsoeudo(String psoeudo);
}
