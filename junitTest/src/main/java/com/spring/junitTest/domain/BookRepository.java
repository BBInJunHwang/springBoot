package com.spring.junitTest.domain;

import org.springframework.data.jpa.repository.JpaRepository;

// jpaRepository extends �ϸ� @Component ��������

public interface BookRepository extends JpaRepository<Book, Long> {

}
