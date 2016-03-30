package data.daos;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.PersistenceConfig;
import config.TestsPersistenceConfig;
import data.entities.Register;
import data.entities.Training;
import data.entities.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class})
public class RegisterDaoITest {

    @Autowired
    private DaosService daosService;

    @Autowired
    private RegisterDao registerDao;
    
    @Autowired
    private UserDao userDao;
    
    @Autowired
    private TrainingDao trainingDao;
    
    @Test
    public void testFindTrainingIsComplete() {
    	List<Training> trainings = trainingDao.findAll();
    	for (Training t : trainings){
    		assertEquals(4,registerDao.findTrainingIsComplete(t.getId()));
    	}
    }
    
    @Test
    public void testFindByUserAndTraining() {
    	List<Training> trainings = trainingDao.findAll();
    	User u = (User) daosService.getMap().get("u3");
    	User user = userDao.findByUsernameOrEmail(u.getUsername());
    	Training t = trainings.get(1);
    	Register r = registerDao.findByUserAndTraining(u, t);
    	assertEquals(r.toString(),registerDao.findByUserAndTraining(user, t).toString());
    }
}
