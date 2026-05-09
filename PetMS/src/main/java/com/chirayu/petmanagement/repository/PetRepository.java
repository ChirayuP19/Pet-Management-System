package com.chirayu.petmanagement.repository;


import com.chirayu.petmanagement.entity.Pet;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Integer> {

    @Query("""
            SELECT
                    CASE
                        WHEN TYPE(p) = DomesticPet THEN 'DOMESTIC'
                        WHEN TYPE(p) = WildPet THEN 'WILD'
                    END,
                    p.gender,
                    p.type,
                    COUNT(p)
                FROM Pet p
                GROUP BY
                    CASE
                        WHEN TYPE(p) = DomesticPet THEN 'DOMESTIC'
                        WHEN TYPE(p) = WildPet THEN 'WILD'
                    END,
                    p.gender,
                    p.type
            """)
    List<Object[]> getStatistics();

    boolean existsById(Integer id);
}
