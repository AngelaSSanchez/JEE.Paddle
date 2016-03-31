package business.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import business.api.exceptions.InvalidTrainingRegistrationException;
import business.api.exceptions.NotAvailableTrainingException;
import business.controllers.RegisterController;
import business.controllers.TrainingController;
import business.wrapper.AvailableTraining;

@RestController
@RequestMapping(Uris.SERVLET_MAP + Uris.REGISTRATIONS)
public class RegisterResource {

    private RegisterController registerController;

    private TrainingController trainingController;
    
    @Autowired
    public void setRegisterController(RegisterController registerController) {
        this.registerController = registerController;
    }
    
    @Autowired
    public void setTrainingController(TrainingController trainingController) {
        this.trainingController = trainingController;
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public List<AvailableTraining> showAvailableTrainings() throws NotAvailableTrainingException {
    	return registerController.showAvailableTrainings();
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public void registerPlayerInTraining(@AuthenticationPrincipal User activeUser, @RequestBody AvailableTraining availableTraining) 
    		throws NotAvailableTrainingException, InvalidTrainingRegistrationException 
    {
        if (!trainingController.exist(availableTraining.getTrainingId())) {
            throw new InvalidTrainingRegistrationException("" + availableTraining.getTrainingId());
        }
        if (!registerController.registerTraining(availableTraining.getTrainingId(), activeUser.toString())){
        	throw new InvalidTrainingRegistrationException("" + availableTraining.getTrainingId());
        }
    }
}
