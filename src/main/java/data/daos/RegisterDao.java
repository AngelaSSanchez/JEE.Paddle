package data.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import data.entities.Register;
import data.entities.Training;
import data.entities.User;

public interface RegisterDao extends JpaRepository<Register, Integer>, RegisterExtended{
	
	@Query(value = "select count(1) from register where training_id = ?", nativeQuery = true)
	public int findTrainingIsComplete(int id);
	
	Register findByUserAndTraining(User user, Training training);
	
	List<Register> findByTraining(Training trainingd);
}
