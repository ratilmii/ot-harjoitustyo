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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javax.imageio.ImageIO;
import mosaicbuilder.ImageBuild;
import mosaicbuilder.ImageColor;
import mosaicbuilder.ImageCompare;

public class FXMLController implements Initializable {

    private File selectedFile;
    private File sourceFiles;
    private File[] sourceFolder;
    private Image goalImage;
    private BufferedImage goalBuffer;
    private BufferedImage exportBuffer;

    @FXML
    private Pane anchorPane;

    @FXML
    private CheckBox checkBoxColor;
    
    @FXML
    private CheckBox checkBoxPixel;
    
    @FXML
    private Button openImageButton;

    @FXML
    private Button openDirectoryButton;

    @FXML
    private Button goalImageClearButton;

    @FXML
    private Button createButton;
    
    @FXML
    private Label messageField;

    @FXML
    private Button sourceImageClearButton;

    @FXML
    private ImageView goalImagePreview;

    @FXML
    private ImageView mainImageView;

    @FXML
    private TextField rgbField;

    @FXML
    private TextField goalImageTextField;

    @FXML
    private TextField sourceImageTextField;

    @FXML
    public void goalButtonAction(ActionEvent event) throws IOException {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(
                new ExtensionFilter("Image Files", "*.jpg", "*.png", "*.bmp"));
        this.selectedFile = fc.showOpenDialog(null);

        this.goalBuffer = ImageIO.read(this.selectedFile);
        this.goalImage = SwingFXUtils.toFXImage(this.goalBuffer, null);

        if (this.selectedFile != null) {
            goalImagePreview.setImage(this.goalImage);
            goalImageTextField.setText(this.selectedFile.getAbsolutePath());
        }

        int k;
        int y;

        ImageColor ic = new ImageColor();

        int i = ic.getDominantColor(this.goalBuffer);
        int[] ar = ic.getPixelARGBArray(i);

        System.out.println(i + "\n");
        for (k = 0; k < ar.length; k++) {
            System.out.println(ar[k]);
        }
        System.out.println("");

        rgbField.setText("(" + ar[1] + ", " + ar[2] + ", " + ar[3] + ")");

    }

    public ImageView getGoalImagePreview() {
        return this.goalImagePreview;
    }

    @FXML
    public void createButtonAction(ActionEvent event) throws IOException {
        messageField.setText("");
        ImageBuild ib = new ImageBuild(20, 20);
        if (this.sourceFiles == null) {
            throw new IOException("No provided images");
        }

        this.sourceFolder = this.sourceFiles.listFiles();

        int i, k, l;

        double lowestSum;
        int x = 0;
        int y = 0;

        int tilesColumns = ib.getTilesColumns(this.goalBuffer);
        int tilesRows = ib.getTilesRows(this.goalBuffer);

        int[][] idArray = new int[tilesRows][tilesColumns];

        System.out.println(tilesColumns + ", " + tilesRows + ", " + this.sourceFolder.length);
        int bestMatchID;
        double distSum;

        double progress = 0;
        
        loop:
        for (k = 0; k < tilesRows; k++) {
            for (l = 0; l < tilesColumns; l++) {
                lowestSum = Double.POSITIVE_INFINITY;
                BufferedImage tile = ib.getTile(this.goalBuffer, x, y);
                
                for (i = 0; i < this.sourceFolder.length; i++) {
                    ImageCompare ic = new ImageCompare();
                    BufferedImage image = ImageIO.read(this.sourceFolder[i]);
                    BufferedImage resized = null;
                    
                    if (checkBoxColor.isSelected()) {
                        try {
                            resized = ib.prepareSourceImg(image);
                            ic.compareTileColor(tile, resized);
                        } catch (NullPointerException e) {
                            System.out.println("NullPointerException: " + e);
                            continue;
                        }

                        distSum = ic.getDistanceSum();
                        if (distSum < lowestSum) {
                            lowestSum = distSum;
                            bestMatchID = i;
                            idArray[k][l] = bestMatchID;
                        }    
                    } else if (checkBoxPixel.isSelected()) {
                        try {
                            resized = ib.prepareSourceImg(image);
                            ic.compareTilePixels(tile, resized);
                        } catch (NullPointerException e) {
                            System.out.println("NullPointerException:" + e);
                            continue;
                        }

                        distSum = ic.getDistanceSum();
    //                    System.out.println(distSum);
                        if (distSum < lowestSum) {
                            lowestSum = distSum;
                            bestMatchID = i;
                            idArray[k][l] = bestMatchID;
    //                        System.out.println(idArray[currentTile] + "\n");
                        }
                    } else {
                        messageField.setText("Select one!");
                        return;
                    }
                    
                }
                x += ib.getTileWidth();
                if ( x > this.goalBuffer.getWidth() - ib.getTileWidth()) {
                    x = 0;
                    y += ib.getTileHeight();
                    if ( y > this.goalBuffer.getHeight() - ib.getTileHeight()) {
                        System.out.println("Done!");
                        break loop;
                    }
                }
                System.out.println(progress + "%");
                progress += (1.0 / (tilesRows * tilesColumns)) * 100.0;
            }
        }
        
        BufferedImage resultBuffer = ib.buildMosaic(this.sourceFolder, idArray, tilesColumns, tilesRows);
        Image result = SwingFXUtils.toFXImage(resultBuffer, null);
        this.exportBuffer = resultBuffer;
        mainImageView.setImage(result);
    }

    @FXML
    public void exportButtonAction(ActionEvent event) throws IOException {
        File outputFile = new File("mosaic.png");
        ImageIO.write(this.exportBuffer, "png", outputFile);
        messageField.setText("Mosaic exported successfully!");
    }
    
    @FXML
    public void sourceButtonAction(ActionEvent event) {
        DirectoryChooser dc = new DirectoryChooser();
        this.sourceFiles = dc.showDialog(null);

        if (this.sourceFiles != null) {
            sourceImageTextField.setText(this.sourceFiles.getAbsolutePath());
        }
    }

    @FXML
    public void clearGoalImagePreview(ActionEvent event) {
        goalImagePreview.setImage(null);
        goalImageTextField.setText(null);
        rgbField.setText(null);
    }

    @FXML
    public void clearSourceImagePath(ActionEvent event) {
        sourceImageTextField.setText(null);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML 
    public void clickColor(ActionEvent event) {
        checkBoxPixel.setSelected(false);
    }
    
    @FXML 
    public void clickPixel(ActionEvent event) {
        checkBoxColor.setSelected(false);
    }
    
    @FXML
    public void closeWindow() {
        Platform.exit();
    }
}
