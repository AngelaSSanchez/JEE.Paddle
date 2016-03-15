package data.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import data.entities.Register;

public interface RegisterDao extends JpaRepository<Register, Integer>{

}
