package data.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import data.entities.Register;

public interface RegisterDao extends JpaRepository<Register, Integer>{
	
	@Query(value = "select count(1) from register where training_id = ?", nativeQuery = true)
	public int findTrainingIsComplete(int id);
}
