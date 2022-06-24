package sn.isi.ui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import sn.isi.dao.IUser;
import sn.isi.dao.UserImpl;
import sn.isi.entities.User;
import tools.Notification;

public class AdminController  implements Initializable{
	@FXML
	private TextField nomtxt;
	@FXML
	private TextField prenomtxt;
	@FXML
	private TextField emailtxt;
	@FXML
	private PasswordField passwordtxt;
	@FXML
	private Button validerbt;

	@FXML
	private TableView<User> usertable;
	@FXML
	private TableColumn<User, Integer> idColumn;
	@FXML
	private TableColumn<User, String> nomColumn;
	@FXML
	private TableColumn<User, String> prenomColumn;
	@FXML
	private TableColumn<User, String> emailColumn;
	@FXML
	private TableColumn<User, String> passwordColumn;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
		prenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
		emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
		passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));

		load();		
	}
	private User user;
	private IUser userdao = new UserImpl();
	public void insertion(ActionEvent event) {

		user= new User();
		user.setNom(nomtxt.getText());
		user.setPrenom(prenomtxt.getText());
		user.setEmail(emailtxt.getText());
		user.setPassword(passwordtxt.getText());

		int ok = userdao.add(user);
		if(ok != 0) {
			Notification.NotifSucces("Success", "Données ajoutées");
			load();
		} else {
			Notification.NotifError("Error", "Erreur");
		}
		
	}
	public void load() {
		User u = new User();
		ObservableList<User> users = FXCollections.observableArrayList();

		List<User> userList = userdao.list(u);
		for (User user : userList) {
			users.add(user);
		}
		usertable.setItems(users);
	}
	
	
}
