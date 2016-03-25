package data.daos;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.PersistenceConfig;
import config.TestsPersistenceConfig;
import data.entities.Training;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class})
public class TrainingDaoITest {

    @Autowired
    private DaosService daosService;

    @Autowired
    private RegisterDao registerDao;
    
    @Autowired
    private TrainingDao trainingDao;  
    
    @Autowired
    private CourtDao courtDao;
    
    @Test
    public void testFindById(){
    	List<Training> trainings = trainingDao.findAll(); 
    	Training t = trainings.get(1);
    	assertEquals(t, trainingDao.findById(t.getId()));
    }
    
    @Test
    public void testDeleteTraining(){
    	List<Training> allTrainings = trainingDao.findAll(); 
    	Training t = allTrainings.get(0);
    	trainingDao.deleteTraining(t.getId());
    	List<Training> totalTrainings = trainingDao.findAll();
    	assertEquals(allTrainings.size()-1, totalTrainings.size());
    }
}
