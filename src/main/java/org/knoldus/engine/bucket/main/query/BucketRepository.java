package org.knoldus.engine.bucket.main.query;

import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;
import java.util.function.Function;


@RepositoryRestResource(collectionResourceRel = "buckets", path = "buckets")
public interface BucketRepository extends MongoRepository<BucketEntity, String> {

    @Override
    <S extends BucketEntity> S save(S entity);

    @Override
    Optional<BucketEntity> findById(String s);
}