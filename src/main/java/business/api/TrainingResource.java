package business.api;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import business.api.exceptions.NotFoundTrainingIdException;
import business.controllers.CourtController;
import business.controllers.ReserveController;
import business.controllers.TrainingController;
import business.wrapper.AvailableTraining;

@RestController
@RequestMapping(Uris.SERVLET_MAP + Uris.TRAININGS)
public class TrainingResource {

    private ReserveController reserveController;

    private CourtController courtController;
    
    private TrainingController trainingController;
    
    private static final int WEEK = 7;
    
    @Autowired
    public void setCourtController(CourtController courtController) {
        this.courtController = courtController;
    }
    
    @Autowired
    public void setTrainingController(TrainingController trainingController) {
        this.trainingController = trainingController;
    }
    
    @RequestMapping(value = Uris.ID, method = RequestMethod.DELETE)
    public void deleteTraining(@AuthenticationPrincipal User activeUser, @PathVariable int id) throws NotFoundTrainingIdException {
        if (!trainingController.deleteTraining(id)) {
            throw new NotFoundTrainingIdException("id: " + id);
        }
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public void createTraining(@AuthenticationPrincipal User activeUser, @RequestBody AvailableTraining availableTraining){
    	for(int i = 0; i < availableTraining.getNumOfWeek(); i++){
	        if (!reserveController.reserveCourt(availableTraining.getCourtId(), availableTraining.getStartHour(), activeUser.getUsername())) {
	            //throw new InvalidCourtReserveException(availableTraining.getCourtId() + "-" + availableTraining.getStartHour());
	        }
	        availableTraining.getStartHour().add(Calendar.DAY_OF_YEAR, WEEK);
    	}
    	availableTraining.getStartHour().add(Calendar.DAY_OF_YEAR, -(WEEK*availableTraining.getNumOfWeek()));
    	if(!trainingController.createTraining(availableTraining.getCourtId(), availableTraining.getStartHour(), availableTraining.getNumOfWeek())){
    		
    	}
    }
}
