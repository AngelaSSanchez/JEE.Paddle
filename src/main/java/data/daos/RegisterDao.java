package data.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import data.entities.Register;
import data.entities.Training;

public interface RegisterDao extends JpaRepository<Register, Integer>, RegisterExtended{
	
	@Query(value = "select count(1) from register where training_id = ?", nativeQuery = true)
	public int findTrainingIsComplete(int id);
	
	@Query(value = "select * from register where user_id = ?1 and training_id =?2", nativeQuery = true)
	Register findByUserAndTraining(int userId, int trainingId);
	
	List<Register> findByTraining(Training trainingd);
}
