package business.api;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import business.api.exceptions.AllreadyExistTrainingException;
import business.api.exceptions.InvalidCourtReserveException;
import business.api.exceptions.InvalidDeletingPlayerFromTrainingException;
import business.api.exceptions.InvalidTrainingDeletingException;
import business.api.exceptions.NotFoundTrainingIdException;
import business.controllers.ReserveController;
import business.controllers.TrainingController;
import business.wrapper.AvailableTraining;

@RestController
@RequestMapping(Uris.SERVLET_MAP + Uris.TRAININGS)
public class TrainingResource {

    private ReserveController reserveController;

    private TrainingController trainingController;
    
    private static final int WEEK = 7;
    
    @Autowired
    public void setTrainingController(TrainingController trainingController) {
        this.trainingController = trainingController;
    }
    
    @RequestMapping(value = Uris.ID, method = RequestMethod.DELETE)
    public void deleteTraining(@PathVariable int id) throws InvalidTrainingDeletingException {
        if (!trainingController.deleteTraining(id)) {
            throw new InvalidTrainingDeletingException("id: " + id);
        }
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public void createTraining(@AuthenticationPrincipal User activeUser, @RequestBody AvailableTraining availableTraining)
    throws InvalidCourtReserveException,
    AllreadyExistTrainingException{
    	for(int i = 0; i < availableTraining.getNumOfWeek(); i++){
	        if (!reserveController.reserveCourt(availableTraining.getCourtId(), availableTraining.getStartHour(), activeUser.getUsername())) {
	            throw new InvalidCourtReserveException(availableTraining.getCourtId() + "-" + availableTraining.getStartHour());
	        }
	        availableTraining.getStartHour().add(Calendar.DAY_OF_YEAR, WEEK);
    	}
    	availableTraining.getStartHour().add(Calendar.DAY_OF_YEAR, -(WEEK*availableTraining.getNumOfWeek()));
    	if(!trainingController.createTraining(availableTraining.getCourtId(), availableTraining.getStartHour(), availableTraining.getNumOfWeek())){
    		throw new AllreadyExistTrainingException(availableTraining.getCourtId() + "-" + availableTraining.getStartHour());
    	}
    }
    
    @RequestMapping(value = Uris.ID + Uris.USERS + Uris.ID, method = RequestMethod.DELETE)
    public void deletePlayerFromTraining(@PathVariable int trainingId, @RequestParam int userId)
    throws InvalidDeletingPlayerFromTrainingException{
    	if(!trainingController.deletePlayerFromTraining(trainingId, userId)){
    		throw new InvalidDeletingPlayerFromTrainingException(trainingId+ "-" + userId);
    	}
    }
}
