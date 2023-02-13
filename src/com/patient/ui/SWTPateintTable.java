package com.patient.ui;

import java.io.IOException;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import com.patient.model.Patient;
import com.patient.rest.SWTRestClient;

/**
 * This class is used to show the Search Patient record by Its Id and name and
 * load the data int SWT table
 * 
 */
public class SWTPateintTable {
	static SWTRestClient sWTRestClient;
	static Patient firstPatient;
	static List<Patient> firstPatientByNameList;
	
	static Patient showPatient;
	PatientForm form;
	Font font1, font;
	static Table table;
	Display display;
	Shell shell;
	Button viewButton, modifyButton, deleteButton;
	Patient oldPatient, newPatient;
	Color buttonColor, headingColor;
	String errorornousPateintId = null;

	public PatientForm getForm() {
		return form;
	}

	public void setForm(PatientForm form) {
		this.form = form;
	}

	public SWTPateintTable(Display display) {
		this.display = display;
	}

	/**
	 * This method is used to search the patient by its ID and Name and populate the
	 * table data by patient id and patient name
	 * 
	 */
	public void loadPatientAPI() {
		shell = new Shell(this.display);
		font = new Font(display, "Arial", 22, SWT.BOLD);
		font1 = new Font(display, "Arial", 15, SWT.BOLD);
		headingColor = new Color(display, 187, 221, 255);
		buttonColor = new Color(display, 224, 255, 255);

		Label serachName = new Label(shell, SWT.NONE);
		serachName.setBounds(30, 30, 900, 30);
		serachName.setText("Serach Patient     ");
		serachName.setBackground(display.getSystemColor(SWT.COLOR_CYAN));
		String[] ITEMS = { "ByID", "ByName" };

		Label idLabel = new Label(shell, SWT.BORDER);
		idLabel.setBounds(30, 80, 100, 20);
		idLabel.setText("EnterID/Name");
		idLabel.setBackground(display.getSystemColor(SWT.COLOR_CYAN));
		Text idText = new Text(shell, SWT.BORDER);
		idText.setBounds(140, 80, 900, 30);
		idText.setTextLimit(30);

		Button searchButton = new Button(shell, SWT.PUSH);
		searchButton.setText("Search");
		searchButton.setVisible(true);
		searchButton.setBounds(700, 50, 900, 30);
		searchButton.setFont(font1);
		searchButton.setBackground(buttonColor);
		Combo searchOptions = new Combo(shell, SWT.DROP_DOWN);
		searchOptions.setItems(ITEMS);
		searchOptions.setBounds(140, 30, 900, 30);
		searchButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				int idx = searchOptions.getSelectionIndex();

				if (idText.getText().equals("") || idText.getText().equals(null) && idx != -1) {
					MessageBox messageBox = new MessageBox(shell, SWT.OK | SWT.ICON_WARNING | SWT.CANCEL);
					messageBox
							.setMessage("Please choose Patient Search Options and Enter some valid value in text box");
					messageBox.open();
				} else {
					String patientType = searchOptions.getItem(idx);
					System.out.println("called Patient by id " + idText.getText());
					if (patientType.equalsIgnoreCase("ByID")) {
						try {
							Patient firstPatientById = SWTRestClient.fetchPatientListById(idText.getText());
							// put some extra check here for telephone list and address list size
							firstPatient = firstPatientById;
							if (firstPatient == null) {
								MessageBox messageBox = new MessageBox(shell, SWT.ICON_WORKING);
								messageBox.setText("fail");
								messageBox.setMessage("Patient " + idText.getText() + "does not exist");
								// shell.close();
							}
						} catch (IOException | InterruptedException e1) {
							e1.printStackTrace();
						}

						catch (Exception e1) {
							if (firstPatient == null) {
								MessageBox messageBox = new MessageBox(shell, SWT.ICON_WORKING);
								messageBox.setText("fail");
								messageBox.setMessage("Patient " + idText.getText() + "does not exist");
							}

						}
					} else {
						try {
						List<Patient> firstPatientByName = SWTRestClient.fetchPatientListByName(idText.getText());
						firstPatientByNameList = firstPatientByName;
						} catch (IOException e1) {
							e1.printStackTrace();
						} catch (InterruptedException e1) {

							e1.printStackTrace();
						}
					}
					if (table != null) {
						table.removeAll();
						table.dispose();
					}
					if (firstPatientByNameList != null) {
						table = new Table(shell, SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION);
						table.setLinesVisible(true);
						table.setHeaderVisible(true);
						table.setBounds(30, 130, 900, 150);
						GridData data = new GridData(SWT.FILL, SWT.FILL, true, true);
						data.heightHint = 200;
						table.setLayoutData(data);

						String[] titles = { "patientId", "patientName", "dateOfBirth", "genderCode", "addID1",
								"addType", "addLine1", "addLine2", "city", "state", "postalCode", "countryCode",
								"addID2", "addType", "addLine1", "addLine2", "city", "state", "postalCode",
								"countryCode", "teleID1", "teleType", "teleNumber", "isdCode", "teleID2", "teleType",
								"teleNumber", "isdCode" };
						for (int i = 0; i < titles.length; i++) {
							TableColumn column = new TableColumn(table, SWT.NONE);
							column.setText(titles[i]);
							table.getColumn(i).pack();
						}

						
					//	TableItem item = new TableItem(table, SWT.NONE);
						
						
						for(Patient firstPatient:firstPatientByNameList)
						{
						TableItem item = new TableItem(table, SWT.ITALIC);
						item.setText(0, firstPatient.getPatient_id().toString());
						item.setText(1, firstPatient.getPatientName().toString());
						item.setText(2, firstPatient.getDateOfBirth().toString());
						item.setText(3, firstPatient.getGenderCode().toString());
						item.setText(4, firstPatient.getAddressList().get(0).getId().toString());
						item.setText(5, firstPatient.getAddressList().get(0).getAddressType().toString());
						item.setText(6, firstPatient.getAddressList().get(0).getAddressLine1().toString());
						item.setText(7, firstPatient.getAddressList().get(0).getAddressLine2().toString().toString());
						item.setText(8, firstPatient.getAddressList().get(0).getCity().toString());
						item.setText(9, firstPatient.getAddressList().get(0).getState().toString());
						item.setText(10, firstPatient.getAddressList().get(0).getPostalCode().toString());
						item.setText(11, firstPatient.getAddressList().get(0).getCountry().toString());
						item.setText(12, firstPatient.getAddressList().get(1).getId().toString());
						item.setText(13, firstPatient.getAddressList().get(1).getAddressType().toString());
						item.setText(14, firstPatient.getAddressList().get(1).getAddressLine1().toString());
						item.setText(15, firstPatient.getAddressList().get(1).getAddressLine2().toString().toString());
						item.setText(16, firstPatient.getAddressList().get(1).getCity().toString());
						item.setText(17, firstPatient.getAddressList().get(1).getState().toString());
						item.setText(18, firstPatient.getAddressList().get(1).getPostalCode().toString());
						item.setText(19, firstPatient.getAddressList().get(1).getCountry().toString());
						item.setText(20, firstPatient.getTelephoneList().get(0).getId().toString());
						item.setText(21, firstPatient.getTelephoneList().get(0).getTelephoneType().toString());
						item.setText(22,
								firstPatient.getTelephoneList().get(0).getTelephoneNumber().toString().toString());
						item.setText(23, firstPatient.getTelephoneList().get(0).getTelephonCountryCode().toString());
						item.setText(24, firstPatient.getTelephoneList().get(1).getId().toString());
						item.setText(25, firstPatient.getTelephoneList().get(1).getTelephoneType().toString());
						item.setText(26, firstPatient.getTelephoneList().get(1).getTelephoneNumber().toString());
						item.setText(27, firstPatient.getTelephoneList().get(1).getTelephonCountryCode().toString());
					}
						for (int i = 0; i < titles.length; i++) {
							table.getColumn(i).pack();
						}

						table.addListener(SWT.Selection, new Listener() {
							@Override
							public void handleEvent(Event arg0) {
								String tableContent = "";
								TableItem[] selectedColumns = table.getSelection();
								for (int i = 0; i < selectedColumns.length; i++) {
									tableContent += selectedColumns[i].getText(i) + " ";
								}
								if (tableContent != null && tableContent != "") {
									viewButton.setEnabled(true);
									deleteButton.setEnabled(true);
									modifyButton.setEnabled(true);
								}

							}

						});

					}
					if (firstPatient != null && firstPatient.getPatient_id() > 0) {
						table = new Table(shell, SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION);
						table.setLinesVisible(true);
						table.setHeaderVisible(true);
						table.setBounds(30, 130, 900, 150);
						GridData data = new GridData(SWT.FILL, SWT.FILL, true, true);
						data.heightHint = 200;
						table.setLayoutData(data);

						String[] titles = { "patientId", "patientName", "dateOfBirth", "genderCode", "addID1",
								"addType", "addLine1", "addLine2", "city", "state", "postalCode", "countryCode",
								"addID2", "addType", "addLine1", "addLine2", "city", "state", "postalCode",
								"countryCode", "teleID1", "teleType", "teleNumber", "isdCode", "teleID2", "teleType",
								"teleNumber", "isdCode" };
						for (int i = 0; i < titles.length; i++) {
							TableColumn column = new TableColumn(table, SWT.NONE);
							column.setText(titles[i]);
							table.getColumn(i).pack();
						}

						TableItem item = new TableItem(table, SWT.NONE);
						item.setText(0, firstPatient.getPatient_id().toString());
						item.setText(1, firstPatient.getPatientName().toString());
						item.setText(2, firstPatient.getDateOfBirth().toString());
						item.setText(3, firstPatient.getGenderCode().toString());
						item.setText(4, firstPatient.getAddressList().get(0).getId().toString());
						item.setText(5, firstPatient.getAddressList().get(0).getAddressType().toString());
						item.setText(6, firstPatient.getAddressList().get(0).getAddressLine1().toString());
						item.setText(7, firstPatient.getAddressList().get(0).getAddressLine2().toString().toString());
						item.setText(8, firstPatient.getAddressList().get(0).getCity().toString());
						item.setText(9, firstPatient.getAddressList().get(0).getState().toString());
						item.setText(10, firstPatient.getAddressList().get(0).getPostalCode().toString());
						item.setText(11, firstPatient.getAddressList().get(0).getCountry().toString());
						item.setText(12, firstPatient.getAddressList().get(1).getId().toString());
						item.setText(13, firstPatient.getAddressList().get(1).getAddressType().toString());
						item.setText(14, firstPatient.getAddressList().get(1).getAddressLine1().toString());
						item.setText(15, firstPatient.getAddressList().get(1).getAddressLine2().toString().toString());
						item.setText(16, firstPatient.getAddressList().get(1).getCity().toString());
						item.setText(17, firstPatient.getAddressList().get(1).getState().toString());
						item.setText(18, firstPatient.getAddressList().get(1).getPostalCode().toString());
						item.setText(19, firstPatient.getAddressList().get(1).getCountry().toString());
						item.setText(20, firstPatient.getTelephoneList().get(0).getId().toString());
						item.setText(21, firstPatient.getTelephoneList().get(0).getTelephoneType().toString());
						item.setText(22,
								firstPatient.getTelephoneList().get(0).getTelephoneNumber().toString().toString());
						item.setText(23, firstPatient.getTelephoneList().get(0).getTelephonCountryCode().toString());
						item.setText(24, firstPatient.getTelephoneList().get(1).getId().toString());
						item.setText(25, firstPatient.getTelephoneList().get(1).getTelephoneType().toString());
						item.setText(26, firstPatient.getTelephoneList().get(1).getTelephoneNumber().toString());
						item.setText(27, firstPatient.getTelephoneList().get(1).getTelephonCountryCode().toString());

						for (int i = 0; i < titles.length; i++) {
							table.getColumn(i).pack();
						}

						table.addListener(SWT.Selection, new Listener() {
							@Override
							public void handleEvent(Event arg0) {
								String tableContent = "";
								TableItem[] selectedColumns = table.getSelection();
								for (int i = 0; i < selectedColumns.length; i++) {
									tableContent += selectedColumns[i].getText(i) + " ";
								}
								if (tableContent != null && tableContent != "") {
									viewButton.setEnabled(true);
									deleteButton.setEnabled(true);
									modifyButton.setEnabled(true);
								}

							}

						});

					}

				}
			}
		});

		viewButton = new Button(shell, SWT.PUSH);
		viewButton.setText(" VIEW ");
		viewButton.setEnabled(false);
		viewButton.setVisible(true);
		viewButton.setBackground(buttonColor);
		PatientForm.locateButton.setEnabled(true);
		viewButton.setBounds(500, 300, 900, 31);
		viewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					TableItem[] selectedColumns = table.getSelection();
					Patient patient = null;
					for (TableItem columns : selectedColumns) {
						patient = SWTRestClient.fetchPatientListById(columns.getText(0));
					}
					form.setDataForFieldsForModifyAndCrate(patient, display, false);
					////shell.close();
				} catch (IOException | InterruptedException ex) {
					ex.printStackTrace();
				}

			}
		});

		modifyButton = new Button(shell, SWT.PUSH);
		modifyButton.setText("MODIFY");
		modifyButton.setEnabled(false);
		modifyButton.setVisible(true);
		modifyButton.setBounds(560, 300, 900, 31);
		modifyButton.setBackground(buttonColor);
		modifyButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Called view!");
				PatientForm.locateButton.setEnabled(true);

				try {
					TableItem[] selectedColumns = table.getSelection();
					Patient patient = null;
					for (TableItem columns : selectedColumns) {
						errorornousPateintId = columns.getText(0);
						patient = SWTRestClient.fetchPatientListById(columns.getText(0));
					}
					form.setDataForFieldsForModifyAndCrate(patient, display, true);
				////	shell.close();
				} catch (IOException | InterruptedException ex) {
					MessageBox messageBox = new MessageBox(shell, SWT.ICON_ERROR);
					messageBox.setText("Error");
					messageBox.setMessage("Patient" + errorornousPateintId + "does not exist ind DB");
					;

				}

			}
		});

		deleteButton = new Button(shell, SWT.PUSH);
		deleteButton.setText("DELETE");
		deleteButton.setEnabled(false);
		deleteButton.setVisible(true);
		deleteButton.setBounds(620, 300, 900, 31);
		deleteButton.setBackground(display.getSystemColor(SWT.COLOR_RED));
		deleteButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Called delete!");

				try {
					TableItem[] item = table.getSelection();
					for (TableItem itemTable : item) {

						errorornousPateintId = itemTable.getText(0);
						System.out.println(itemTable.getText(1));
						SWTRestClient.deletePatient(Long.valueOf(itemTable.getText(0)));
						MessageBox messageBox = new MessageBox(shell, SWT.ICON_WORKING);
						messageBox.setText("Success");
						messageBox.setMessage("Patient deleted successfully");
						int buttonId = messageBox.open();
						switch (buttonId) {
						case SWT.YES:
						case SWT.NO:
							break;
						case SWT.CANCEL:
						}
						PatientForm.locateButton.setEnabled(true);
					////	shell.close();

					}
				} catch (Exception ex) {
					MessageBox messageBox = new MessageBox(shell, SWT.ICON_ERROR);
					messageBox.setText("Error");
					messageBox.setMessage("Patient" + errorornousPateintId + "does not exist ind DB");
					;

				}

			}

		});
		serachName.pack();
		idText.pack();
		searchOptions.pack();
		searchButton.pack();
		viewButton.pack();
		modifyButton.pack();
		deleteButton.pack();
		shell.pack();
		shell.open();
		shell.layout();
		shell.setSize(800, 700);

	}

	/**
	 * This method is used to show the dialog box for error message *
	 * 
	 * @param shell
	 * @param errorMsg
	 */

	public void showErrorDialogBox(Shell shell, String errorMsg) {
		MessageBox mbox = new MessageBox(shell, SWT.ICON_ERROR);
		mbox.setText("ERROR");
		mbox.setMessage(errorMsg);
		int buttonId = mbox.open();
		switch (buttonId) {
		case SWT.YES:
		case SWT.NO:
			break;
		case SWT.CANCEL:
		}
	}

}
