package mosaicbuilder;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
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
import javax.imageio.ImageIO;

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
    private ImageView mainImageView;
    
    @FXML
    private TextField RGBField;
    
    @FXML
    private TextField goalImageTextField;
    
    @FXML
    private TextField sourceImageTextField;
    
    @FXML
    public void goalButtonAction(ActionEvent event) throws IOException{
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(
                new ExtensionFilter("Image Files", "*.jpg", "*.png", "*.bmp"));
        File selectedFile = fc.showOpenDialog(null);
        
        BufferedImage buffer = ImageIO.read(selectedFile);
        Image goalImage = SwingFXUtils.toFXImage(buffer, null);
        
        if(selectedFile != null){
            goalImagePreview.setImage(goalImage);
            goalImageTextField.setText(selectedFile.getAbsolutePath());
        }
        
        int k;
        int y;
        
        ImageColor ic = new ImageColor();
        
        int i = ic.getDominantColor(buffer);
        int[] ar = ic.getPixelARGBArray(i);

//        int[] arr = new int[4];
//        arr[0] = (i >> 24) & 0xff;
//        arr[1] = (i >> 16) & 0xff;
//        arr[2] = (i >> 8) & 0xff;
//        arr[3] = (i) & 0xff;
        
//        int[] j = ic.getPixelARGBArray(buffer.getRGB(8, 0));
//        int[] l = ic.getRoundedPixelARGBArray(j);
        System.out.println(i+"\n");
        for(k=0;k<ar.length;k++){
            System.out.println(ar[k]);
        }
        System.out.println("");
//        for(y=0;y<l.length;y++){
//            System.out.println(l[y]);
//        }
        RGBField.setText("(" + ar[1] + ", " + ar[2] + ", " + ar[3] + ")");
        
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
        RGBField.setText(null);
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
