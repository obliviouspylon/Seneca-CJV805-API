package com.example.CJV805_API.listings;

import com.example.CJV805_API.property.PropertyType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.expression.spel.ast.OpInc;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ListingsRepository extends MongoRepository<Listings,String> {

    @Query(value ="{id: { $gte: 0 }}",count=true)           //SQL Equivalent : select count(*) from book
    Integer getMaxID();

    @Query(value ="{id: ?0}")           //SQL Equivalent : select count(*) from book
    Listings getListingByID(Integer id);

    @Query(value ="{bestseller: true}")
    List<Listings> getBestSellers();

    @Query(value ="{'title':{'$regex':'?0','$options':'i'},type: ?1}")
    Optional<List<Listings>> getListingByTitleType(String title, String type);
}
