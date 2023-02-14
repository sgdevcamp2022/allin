package com.example.repository;

import com.example.domain.User;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
  List<User> findAllByIsDone(boolean isDone);
}
