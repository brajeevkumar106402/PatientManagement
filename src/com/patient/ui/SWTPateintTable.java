package com.patient.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Point;
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

import com.patient.model.Address;
import com.patient.model.Patient;
import com.patient.model.Telephone;
import com.patient.rest.SWTRestClient;

public class SWTPateintTable {
	static SWTRestClient sWTRestClient;
	static Patient firstPatient;
	static Patient showPatient;
	static PatientForm form;
	Font font1, font;

	static Table table;
	Display display;
	static Shell shell;
	Button viewButton, modifyButton, deleteButton;
	Patient oldPatient, newPatient;

//	public void mainProgramme() throws IOException, InterruptedException {
	public void mainProgramme(Display display) {
		// public void mainProgramme() throws IOException, InterruptedException {
		// display = new Display();
		this.display = display;
		shell = new Shell(display);
		font = new Font(display, "Arial", 22, SWT.BOLD);
		font1 = new Font(display, "Arial", 15, SWT.BOLD);
		Label serachName = new Label(shell, SWT.NONE);
		serachName.setBounds(30, 30, 100, 31);
		serachName.setText("SerachPatient");
		String[] ITEMS = { "ByID", "ByName" };

		Text idText = new Text(shell, SWT.BORDER);
		idText.setBounds(30, 60, 100, 30);
		idText.setTextLimit(30);

		Button searchButton = new Button(shell, SWT.PUSH);
		searchButton.setText("Search");
		searchButton.setVisible(true);
		searchButton.setBounds(700, 50, 180, 31);
		searchButton.setFont(font1);
		// Create a dropdown Combo
		Combo searchOptions = new Combo(shell, SWT.DROP_DOWN);
		searchOptions.setItems(ITEMS);
		searchOptions.setBounds(110, 30, 900, 31);
		// User select a item in the Combo.
		searchButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				int idx = searchOptions.getSelectionIndex();
				String patientType = searchOptions.getItem(idx);
				if (patientType.equalsIgnoreCase("ByID")) {
					System.out.println("called Patient by id " + idText.getText());
					try {
						Patient firstPatientById = SWTRestClient.fetchPatientListById(idText.getText());
						firstPatient = firstPatientById;
						if(firstPatient==null) {
							MessageBox messageBox = new MessageBox(shell, SWT.ICON_WORKING);
							messageBox.setText("fail");
							messageBox.setMessage("Patient "+idText.getText()+ "does not exist");
							//shell.close();
						}
					} catch (IOException e1) {
						if(firstPatient==null) {
							MessageBox messageBox = new MessageBox(shell, SWT.ICON_WORKING);
							messageBox.setText("fail");
							messageBox.setMessage("Patient "+idText.getText()+ "does not exist");
							//shell.close();
						}
						//e1.printStackTrace();
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					System.out.println("called Patient by Name " + idText.getText());
					try {
						Patient firstPatientByName = SWTRestClient.fetchPatientListByName(idText.getText());
						firstPatient = firstPatientByName;
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				// table calls goes there
				if (table != null) {
					table.dispose();
				}
				table = new Table(shell, SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION);
				table.setLinesVisible(true);
				table.setHeaderVisible(true);
				table.setBounds(30, 90, 900, 200);
				GridData data = new GridData(SWT.FILL, SWT.FILL, true, true);
				data.heightHint = 200;
				table.setLayoutData(data);

				String[] titles = { "patientId", "patientName", "dateOfBirth", "genderCode", "addID1", "addType",
						"addLine1", "addLine2", "city", "state", "postalCode", "countryCode", "addID2", "addType",
						"addLine1", "addLine2", "city", "state", "postalCode", "countryCode", "teleID1", "teleType",
						"teleNumber", "isdCode", "teleID2", "teleType", "teleNumber", "isdCode" };
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
				item.setText(22, firstPatient.getTelephoneList().get(0).getTelephoneNumber().toString().toString());
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
						Patient p = new Patient();
						TableItem[] selectedColumns = table.getSelection();
						for (int i = 0; i < selectedColumns.length; i++) {
							tableContent += selectedColumns[i].getText(i) + " ";
							// p.setPatient_id(selectedColumns[i].getData());

						}
						if (tableContent != null && tableContent != "") {
							viewButton.setEnabled(true);
							deleteButton.setEnabled(true);
							modifyButton.setEnabled(true);
						}

						System.out.println("tableContent" + tableContent);
					}

				});

			}

		});

		shell.addListener(SWT.Resize, event -> {
			Shell eventShell = (Shell) event.widget;

			Point size = eventShell.getSize();

			if (size.x < 100 || size.y < 100) {
				// Stop the resize event
				event.doit = false;
			}
		});

		viewButton = new Button(shell, SWT.PUSH);
		viewButton.setText(" VIEW ");
		viewButton.setEnabled(false);
		viewButton.setVisible(true);
		Patient p = new Patient();
		viewButton.setBounds(500, 300, 900, 31);
		viewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Called view!");
				
				try {
					TableItem[] selectedColumns = table.getSelection();
					Patient patient = null;
					for (TableItem columns : selectedColumns) {
						patient = SWTRestClient.fetchPatientListById(columns.getText(0));
					}
					form = new PatientForm();
					form.setDataForFieldsForModify(patient,display,true);
					shell.close();
				}catch(IOException | InterruptedException ex) {
					ex.printStackTrace();
				}
				
			/*	table.addListener(SWT.Selection, new Listener() {
					@Override
					public void handleEvent(Event arg0) {
						String tableContent = "";
						// p =
						TableItem[] selectedColumns = table.getSelection();
						for (int i = 0; i < selectedColumns.length; i++) {
							tableContent += selectedColumns[i].getText(i) + " ";

						}
						if (selectedColumns.length > 0 && selectedColumns.length < 2) {
							p.setPatient_id(new Long(selectedColumns[0].getText(0)));
							p.setPatientName(selectedColumns[0].getText(1));
							p.setDateOfBirth(selectedColumns[0].getText(2));
							p.setGenderCode(selectedColumns[0].getText(3));
						}

						if (tableContent != null && tableContent != "") {
							viewButton.setEnabled(true);
							deleteButton.setEnabled(false);
							modifyButton.setEnabled(false);
						}

						System.out.println("tableContent" + tableContent);
					}

				}); */
			//	ViewPatientDetails(shell, p, false);
				//shell.close();
			//	shell.dispose();

			}
		});

		modifyButton = new Button(shell, SWT.PUSH);
		modifyButton.setText("MODIFY");
		modifyButton.setEnabled(false);
		modifyButton.setVisible(true);
		modifyButton.setBounds(560, 300, 900, 31);
		modifyButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Called modify!");
				// in modify we have to capture all table item into patinet objects and populate
				// into old patient object and open hte patient form and set the text data
				// enable the save button and
				// what we update it in store new patient object and call the update method

				table.addListener(SWT.Selection, new Listener() {
					@Override
					public void handleEvent(Event arg0) {
						String tableContent = "";
						oldPatient = new Patient();
						Address currentAddress = new Address();
						Address permanentAddress = new Address();
						Telephone home = new Telephone();
						Telephone office = new Telephone();
						List<Telephone> telephoneList = new ArrayList<>();

						List<Address> addressList = new ArrayList<>();
						TableItem[] selectedColumns = table.getSelection();
						for (int i = 0; i < selectedColumns.length; i++) {
							tableContent += selectedColumns[i].getText(i) + " ";

						}
						if (selectedColumns.length > 0 && selectedColumns.length < 2) {
							oldPatient.setPatient_id(new Long(selectedColumns[0].getText(0)));
							oldPatient.setPatientName(selectedColumns[0].getText(1));
							oldPatient.setDateOfBirth(selectedColumns[0].getText(2));
							oldPatient.setGenderCode(selectedColumns[0].getText(3));

							currentAddress.setId(new Long(selectedColumns[0].getText(4)));
							currentAddress.setAddressType(selectedColumns[0].getText(5));
							currentAddress.setAddressLine1(selectedColumns[0].getText(6));
							currentAddress.setAddressLine2(selectedColumns[0].getText(7));
							currentAddress.setCity(selectedColumns[0].getText(8));
							currentAddress.setState(selectedColumns[0].getText(9));
							currentAddress.setPostalCode(selectedColumns[0].getText(10));
							currentAddress.setCountry(selectedColumns[0].getText(11));
							permanentAddress.setId(new Long(selectedColumns[0].getText(12)));
							permanentAddress.setAddressType(selectedColumns[0].getText(13));
							permanentAddress.setAddressLine1(selectedColumns[0].getText(14));
							permanentAddress.setAddressLine2(selectedColumns[0].getText(15));
							permanentAddress.setCity(selectedColumns[0].getText(16));
							permanentAddress.setState(selectedColumns[0].getText(17));
							permanentAddress.setPostalCode(selectedColumns[0].getText(18));
							permanentAddress.setCountry(selectedColumns[0].getText(19));
							addressList.add(currentAddress);
							addressList.add(permanentAddress);
							home.setId(new Long(selectedColumns[0].getText(20)));
							home.setTelephoneType(selectedColumns[0].getText(21));
							home.setTelephoneNumber(selectedColumns[0].getText(22));
							home.setTelephonCountryCode(selectedColumns[0].getText(23));
							office.setId(new Long(selectedColumns[0].getText(24)));
							office.setTelephoneType(selectedColumns[0].getText(25));
							office.setTelephoneNumber(selectedColumns[0].getText(26));
							office.setTelephonCountryCode(selectedColumns[0].getText(27));
							telephoneList.add(home);
							telephoneList.add(office);
							oldPatient.setAddressList(addressList);
							oldPatient.setTelephoneList(telephoneList);
							System.out.println("oldPatient data from table list " + oldPatient);

						}
						form = new PatientForm();
						System.out.println("oldPatient data from table list After pateint from init " + oldPatient);
						form.createModifyFormView(oldPatient, new Patient(), shell);

						if (tableContent != null && tableContent != "") {
							viewButton.setEnabled(true);
							deleteButton.setEnabled(false);
							modifyButton.setEnabled(false);
						}

						System.out.println("tableContent" + tableContent);
					}

				});

			}
		});

		deleteButton = new Button(shell, SWT.PUSH);
		deleteButton.setText("DELETE");
		deleteButton.setEnabled(false);
		deleteButton.setVisible(true);
		deleteButton.setBounds(620, 300, 900, 31);
		deleteButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Called delete!");
				try {
					TableItem[] item = table.getSelection();
					for (TableItem itemTable : item) {
						System.out.println("before itemTable.getText(0) ");
						System.out.println(itemTable.getText(0));
						System.out.println(itemTable.getText(1));
						sWTRestClient.deletePatient(new Long(itemTable.getText(0)));
						// sWTRestClient.
						MessageBox messageBox = new MessageBox(shell, SWT.ICON_WORKING);
						messageBox.setText("Success");
						messageBox.setMessage("Patient deleted successfully");
						int buttonId = messageBox.open();
						switch (buttonId) {
						case SWT.YES:
						case SWT.NO:
							// new PatientForm().open();
							// new PatientForm().createPatientHeader(display, shell);
							break;
						case SWT.CANCEL:
						}
						System.out.println(buttonId);
						new PatientForm().setDataForDefaultsPage();
						shell.close();

					}
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
					System.out.println(ex.getStackTrace());

				}
				// deletePatient(/*display,*/ shell);
				// System.out.println(e.getSource());
			}

			private void deletePatient(Shell shell) {
				// TODO Auto-generated method stub

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
		shell.setSize(600, 600);
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		shell.dispose();
	}

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

	protected void ViewPatientDetails(Shell shell, Patient p, boolean b) {
		System.out.println("inside create patient process");
		System.out.println(p);
		String name = "nameText.getText()";
		new PatientForm().createPatientHeader(this.display, shell);

	}

	/*
	 * public static void main(String[] args) throws IOException,
	 * InterruptedException { // static Display display1 = new Display(); new
	 * SWTPateintTable().mainProgramme(); }
	 */

}
