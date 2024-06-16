package dev.patika.easyvet.dao;

import dev.patika.easyvet.entities.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IReportRepo extends JpaRepository<Report,Long> {

}
