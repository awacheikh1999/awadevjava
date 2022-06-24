package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sn.isi.dao.IUser;
import sn.isi.dao.UserImpl;
import sn.isi.entities.User;
import tools.Notification;
import tools.Outils;

public class MainController {
	
	@FXML
	private TextField emailtxt;
	@FXML
	private PasswordField passwordtxt;
	private IUser userdao = new UserImpl();
	
	
	public void login(ActionEvent event) throws IOException {
		String email = emailtxt.getText().toString();
		String password = passwordtxt.getText().toString();
		User user = userdao.login(email, password);
		if (user != null) {
			//Notification.NotifSucces("Message", "Information correcte");
			if (user.getEmail().contains("awa")) {
				Outils.load(event, "Administration", "/sn/isi/ui/Admin.fxml");
			}
			else {
				
			}
		}
		else {
			Notification.NotifError("Message", "Email ou mot de passe incorrecte");
		}
		
	}
	
	
	
}
