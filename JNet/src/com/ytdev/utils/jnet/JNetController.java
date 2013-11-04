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

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;

import org.omg.CORBA.SystemException;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.stage.Stage;

public class JNetController {

	@FXML
	private Label appTitle;

	/* TCP */
	@FXML
	private TextField txfIP_TCP;
	@FXML
	private TextField txfPort_TCP;
	@FXML
	private TextField txfMessage_TCP;
	@FXML
	private TextArea txaMessage_TCP;
	@FXML
	private ChoiceBox<JNetBean> chbSocketMode_TCP;

	/* UDP */
	@FXML
	private TextField txfIP_UDP;
	@FXML
	private TextField txfPort_UDP;
	@FXML
	private TextField txfMessage_UDP;
	@FXML
	private TextArea txaMessage_UDP;
	@FXML
	private ChoiceBox<String> chbSocketMode_UDP;
	@FXML
	private RadioButton rdbMulticast;
	@FXML
	private RadioButton rdbUnicast;

	/* Network Interface */
	@FXML
	private ProgressBar progressBarNWInterface;
	@FXML
	private TreeView<String> treViewNWInterface;
	@FXML
	private Tab tabNWInterface;

	/* System Information */
	@FXML
	private ProgressBar progressBarSysInfo;
	@FXML
	private TableView<SysInfoBean> tblViewSysInfo;
	@FXML
	private Tab tabSysInfo;

	private Stage stage;

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	@FXML
	protected void sendTCPMessage() {
		System.out.println("TCP Message Send" + txfIP_TCP.getText()
				+ txfPort_TCP.getText());
	}

	@FXML
	protected void sendUDPMessage() {
		System.out.println("UDP Message Send");
	}

	@FXML
	protected void clearTCPMsgHistory() {
		txaMessage_TCP.clear();
	}

	@FXML
	protected void clearUDPMsgHistory() {
		txaMessage_UDP.clear();
	}

	@FXML
	protected void processdistributionMode() {

	}

	@FXML
	protected void closeApp() {
		stage.close();
	}

	@FXML
	protected void minimizeApp() {
		stage.setIconified(true);
	}

	@FXML
	protected void aboutApp() {
		System.out.println("JNet App version 0.01");
	}

	@FXML
	protected void loadNWInterfaceInfo() {
		HashMap<String, List<String>> nwInfoMap = NetworkInterface
				.getInformation();
		TreeItem<String> hostItem = new TreeItem<String>(
				NetworkInterface.getHostName());
		hostItem.setExpanded(true);
		Iterator<Entry<String, List<String>>> iterator = nwInfoMap.entrySet()
				.iterator();
		while (iterator.hasNext()) {
			Entry<String, List<String>> entry = (Entry<String, List<String>>) iterator
					.next();
			List<String> infoList = (List<String>) entry.getValue();
			TreeItem<String> interfaceItem = new TreeItem<String>(
					entry.getKey());
			if (infoList != null && !infoList.isEmpty()) {
				for (String info : infoList) {
					interfaceItem.getChildren().add(new TreeItem<String>(info));
				}
			}
			hostItem.getChildren().add(interfaceItem);
		}
		progressBarNWInterface.setVisible(false);
		treViewNWInterface.setRoot(hostItem);
	}

	@FXML
	protected void loadSysInfo() {
		System.out.println();
		ObservableList<SysInfoBean> sysInfoData = tblViewSysInfo.getItems();
		Properties systemProperties = System.getProperties();
		Iterator<Entry<Object, Object>> iterator = systemProperties.entrySet()
				.iterator();
		sysInfoData.clear();
		while (iterator.hasNext()) {
			Entry<Object, Object> entry = iterator.next();
			SysInfoBean sysInfoBean = new SysInfoBean((String) entry.getKey(),
					(String) entry.getValue());
			sysInfoData.add(sysInfoBean);
		}
		progressBarSysInfo.setVisible(false);
	}

	/**
	 * @return the appTitle
	 */
	public final Label getAppTitle() {
		return appTitle;
	}

	/**
	 * @param appTitle
	 *            the appTitle to set
	 */
	public final void setAppTitle(Label appTitle) {
		this.appTitle = appTitle;
	}

	/**
	 * @return the txfIP_TCP
	 */
	public final TextField getTxfIP_TCP() {
		return txfIP_TCP;
	}

	/**
	 * @param txfIP_TCP
	 *            the txfIP_TCP to set
	 */
	public final void setTxfIP_TCP(TextField txfIP_TCP) {
		this.txfIP_TCP = txfIP_TCP;
	}

	/**
	 * @return the txfPort_TCP
	 */
	public final TextField getTxfPort_TCP() {
		return txfPort_TCP;
	}

	/**
	 * @param txfPort_TCP
	 *            the txfPort_TCP to set
	 */
	public final void setTxfPort_TCP(TextField txfPort_TCP) {
		this.txfPort_TCP = txfPort_TCP;
	}

	/**
	 * @return the txfMessage_TCP
	 */
	public final TextField getTxfMessage_TCP() {
		return txfMessage_TCP;
	}

	/**
	 * @param txfMessage_TCP
	 *            the txfMessage_TCP to set
	 */
	public final void setTxfMessage_TCP(TextField txfMessage_TCP) {
		this.txfMessage_TCP = txfMessage_TCP;
	}

	/**
	 * @return the txaMessage_TCP
	 */
	public final TextArea getTxaMessage_TCP() {
		return txaMessage_TCP;
	}

	/**
	 * @param txaMessage_TCP
	 *            the txaMessage_TCP to set
	 */
	public final void setTxaMessage_TCP(TextArea txaMessage_TCP) {
		this.txaMessage_TCP = txaMessage_TCP;
	}

	/**
	 * @return the chbSocketMode_TCP
	 */
	public final ChoiceBox<JNetBean> getChbSocketMode_TCP() {
		return chbSocketMode_TCP;
	}

	/**
	 * @param chbSocketMode_TCP
	 *            the chbSocketMode_TCP to set
	 */
	public final void setChbSocketMode_TCP(ChoiceBox<JNetBean> chbSocketMode_TCP) {
		this.chbSocketMode_TCP = chbSocketMode_TCP;
	}

	/**
	 * @return the txfIP_UDP
	 */
	public final TextField getTxfIP_UDP() {
		return txfIP_UDP;
	}

	/**
	 * @param txfIP_UDP
	 *            the txfIP_UDP to set
	 */
	public final void setTxfIP_UDP(TextField txfIP_UDP) {
		this.txfIP_UDP = txfIP_UDP;
	}

	/**
	 * @return the txfPort_UDP
	 */
	public final TextField getTxfPort_UDP() {
		return txfPort_UDP;
	}

	/**
	 * @param txfPort_UDP
	 *            the txfPort_UDP to set
	 */
	public final void setTxfPort_UDP(TextField txfPort_UDP) {
		this.txfPort_UDP = txfPort_UDP;
	}

	/**
	 * @return the txfMessage_UDP
	 */
	public final TextField getTxfMessage_UDP() {
		return txfMessage_UDP;
	}

	/**
	 * @param txfMessage_UDP
	 *            the txfMessage_UDP to set
	 */
	public final void setTxfMessage_UDP(TextField txfMessage_UDP) {
		this.txfMessage_UDP = txfMessage_UDP;
	}

	/**
	 * @return the txaMessage_UDP
	 */
	public final TextArea getTxaMessage_UDP() {
		return txaMessage_UDP;
	}

	/**
	 * @param txaMessage_UDP
	 *            the txaMessage_UDP to set
	 */
	public final void setTxaMessage_UDP(TextArea txaMessage_UDP) {
		this.txaMessage_UDP = txaMessage_UDP;
	}

	/**
	 * @return the chbSocketMode_UDP
	 */
	public final ChoiceBox<String> getChbSocketMode_UDP() {
		return chbSocketMode_UDP;
	}

	/**
	 * @param chbSocketMode_UDP
	 *            the chbSocketMode_UDP to set
	 */
	public final void setChbSocketMode_UDP(ChoiceBox<String> chbSocketMode_UDP) {
		this.chbSocketMode_UDP = chbSocketMode_UDP;
	}

	/**
	 * @return the rdbMulticast
	 */
	public final RadioButton getRdbMulticast() {
		return rdbMulticast;
	}

	/**
	 * @param rdbMulticast
	 *            the rdbMulticast to set
	 */
	public final void setRdbMulticast(RadioButton rdbMulticast) {
		this.rdbMulticast = rdbMulticast;
	}

	/**
	 * @return the rdbUnicast
	 */
	public final RadioButton getRdbUnicast() {
		return rdbUnicast;
	}

	/**
	 * @param rdbUnicast
	 *            the rdbUnicast to set
	 */
	public final void setRdbUnicast(RadioButton rdbUnicast) {
		this.rdbUnicast = rdbUnicast;
	}

	/**
	 * @return the progressBarNWInterface
	 */
	public final ProgressBar getProgressBarNWInterface() {
		return progressBarNWInterface;
	}

	/**
	 * @param progressBarNWInterface
	 *            the progressBarNWInterface to set
	 */
	public final void setProgressBarNWInterface(
			ProgressBar progressBarNWInterface) {
		this.progressBarNWInterface = progressBarNWInterface;
	}

	/**
	 * @return the treViewNWInterface
	 */
	public final TreeView<String> getTreViewNWInterface() {
		return treViewNWInterface;
	}

	/**
	 * @param treViewNWInterface
	 *            the treViewNWInterface to set
	 */
	public final void setTreViewNWInterface(TreeView<String> treViewNWInterface) {
		this.treViewNWInterface = treViewNWInterface;
	}

	/**
	 * @return the progressBarSysInfo
	 */
	public final ProgressBar getProgressBarSysInfo() {
		return progressBarSysInfo;
	}

	/**
	 * @param progressBarSysInfo
	 *            the progressBarSysInfo to set
	 */
	public final void setProgressBarSysInfo(ProgressBar progressBarSysInfo) {
		this.progressBarSysInfo = progressBarSysInfo;
	}

	/**
	 * @return the tblViewSysInfo
	 */
	public final TableView<SysInfoBean> getTblViewSysInfo() {
		return tblViewSysInfo;
	}

	/**
	 * @param tblViewSysInfo
	 *            the tblViewSysInfo to set
	 */
	public final void setTblViewSysInfo(TableView<SysInfoBean> tblViewSysInfo) {
		this.tblViewSysInfo = tblViewSysInfo;
	}

	/**
	 * @return the stage
	 */
	public final Stage getStage() {
		return stage;
	}

}
