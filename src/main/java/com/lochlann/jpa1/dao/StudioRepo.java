package com.lochlann.jpa1.dao;

import com.lochlann.jpa1.entities.Studio;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StudioRepo extends JpaRepository<Studio, Integer> {
    Optional<Studio> findByStudioName(String studioName);
    Optional<Studio> findByStudioId(int studioId);
    List<Studio> findAllByOrderByYearFounded();
    List<Studio> findByStudioNameContaining(String snippet);

    //JPQL - Java Persistence Query Language
    @Query("select s from Studio s order by s.studioName")
    List<Studio> findAllStudiosAlphabetically();

    @Modifying
//    @Modifying(clearAutomatically = true)  // forget the persistence context and make a new call
    @Transactional
    @Query("update Studio s set s.studioName = :newName where s.studioId = :studioId")
    void changeName(@Param("studioId") int studioId, @Param("newName") String newName);


}
