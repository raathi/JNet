/*******************************************************************************
 * Copyright (c) 2013 Thirumalaivasan Rajasekaran.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 * 
 * Contributors:
 *     Thirumalaivasan Rajasekaran - initial API and implementation
 ******************************************************************************/
package com.ytdev.utils.jnet;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class JNetLauncher extends Application {
	@Override
	public void start(Stage stage) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("JNet.fxml"));
			final Parent root = (Parent) fxmlLoader.load();
			final JNetController controller = fxmlLoader.getController();
			controller.setStage(stage);
			controller.getProgressBarNWInterface().setPrefHeight(0.5);
			Scene scene = new Scene(root,511,503);
			scene.getStylesheets().add(getClass().getResource("JFxMetro_Rivulet.css").toExternalForm());
			stage.initStyle(StageStyle.UNDECORATED);
			stage.setTitle("JNet v0.01");		
			stage.getIcons().add(new Image(getClass().getResourceAsStream( "JNet_Taskbar.png"))); 
			stage.centerOnScreen();
			stage.setScene(scene);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
