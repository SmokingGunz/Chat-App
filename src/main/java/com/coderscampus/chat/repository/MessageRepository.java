package com.coderscampus.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coderscampus.chat.domain.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
	

}
