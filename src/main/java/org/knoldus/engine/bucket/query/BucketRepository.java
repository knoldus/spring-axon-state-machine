package org.knoldus.engine.bucket.query;

import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.Optional;
import java.util.function.Function;


@RepositoryRestResource(collectionResourceRel = "buckets", path = "buckets")
public interface BucketRepository extends MongoRepository<BucketEntity, String> {

    @Override
    <S extends BucketEntity> S save(S entity);

    @Override
    <S extends BucketEntity> Optional<S> findOne(Example<S> example);

    @Override
    <S extends BucketEntity> boolean exists(Example<S> example);

    @Override
    <S extends BucketEntity, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);
}