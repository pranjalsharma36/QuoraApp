package com.QuoraApp.QuoraApp.repository;

import com.QuoraApp.QuoraApp.model.Question;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Repository
public interface QuestionRepository extends ReactiveMongoRepository<Question, String> {
    // Additional custom query methods can be defined here if needed
//    Flux<Question> findByAuthorId(String authorId);
    // note : in list - you store all your records at once in the list data structure and return it
    // however in flux - you return the records one by one as they are available - like a stream
    // flux and mono acts like publisher

//    Mono<Long> countByAuthorId(String authorId);

    @Query("{}")
    Flux<Question> findAllBy(Pageable pageable);
}
