package com.example.repository;

import com.example.domain.Message;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface ChatRepository extends CrudRepository<Message, String> {
  List<Message> findAllByTopicId(String topicId, Pageable pageable);
}
