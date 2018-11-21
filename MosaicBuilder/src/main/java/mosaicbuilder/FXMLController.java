package mosaicbuilder;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class FXMLController implements Initializable {

    @FXML
    private Pane anchorPane;
    
    @FXML
    private Button openImageButton;
    
    @FXML
    private Button openDirectoryButton;        
    
    @FXML
    private Button goalImageClearButton;
    
    @FXML
    private Button sourceImageClearButton;
    
    @FXML
    private ImageView goalImagePreview;
    
    @FXML
    private TextField goalImageTextField;
    
    @FXML
    private TextField sourceImageTextField;
    
    @FXML
    public void goalButtonAction(ActionEvent event){
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(
                new ExtensionFilter("Image Files", "*.jpg", "*.png", "*.bmp"));
        File selectedFile = fc.showOpenDialog(null);
        
        Image goalImage = new Image(selectedFile.toURI().toString());
        
        if(selectedFile != null){
            goalImagePreview.setImage(goalImage);
            goalImageTextField.setText(selectedFile.getAbsolutePath());
        }
    }
    
    public ImageView getGoalImagePreview(){
        return this.goalImagePreview;
    }
    
    @FXML 
    public void sourceButtonAction(ActionEvent event){
        DirectoryChooser dc = new DirectoryChooser();
        File selectedDirectory = dc.showDialog(null);
        
        if(selectedDirectory != null){
            sourceImageTextField.setText(selectedDirectory.getAbsolutePath());
        }
    }
    
    @FXML
    public void clearGoalImagePreview(ActionEvent event){
        goalImagePreview.setImage(null);
        goalImageTextField.setText(null);
    }
    
    @FXML 
    public void clearSourceImagePath(ActionEvent event){
        sourceImageTextField.setText(null);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    @FXML
    public void closeWindow() {
        Platform.exit();
    }
}
