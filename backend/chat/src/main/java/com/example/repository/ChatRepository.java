package com.example.repository;

import com.example.domain.Message;
import org.springframework.data.repository.CrudRepository;

public interface ChatRepository extends CrudRepository<Message, String> {

}
