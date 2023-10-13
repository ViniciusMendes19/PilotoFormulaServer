package com.example.PilotoFormula.model.repository;
import com.example.PilotoFormula.model.entities.PilotoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PilotoRepository extends JpaRepository<PilotoEntity, Long> {
}
