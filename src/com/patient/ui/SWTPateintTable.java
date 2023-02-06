package com.patient.ui;

import java.io.IOException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import com.patient.model.Patient;
import com.patient.rest.SWTRestClient;

public class SWTPateintTable {
	static SWTRestClient sWTRestClient;
	static Patient firstPatient;
	static Table table;
	Display display;
	static Shell shell;

//	public void mainProgramme() throws IOException, InterruptedException {
	public void mainProgramme(Display display) {
	//	public void mainProgramme() throws IOException, InterruptedException {
		//display = new Display();
		this.display = display;
		shell = new Shell(display);

		Label serachName = new Label(shell, SWT.NONE);
		serachName.setBounds(30, 30, 100, 31);
		serachName.setText("SerachPatient");
		String[] ITEMS = { "ByID", "ByName" };

		Text idText = new Text(shell, SWT.BORDER);
		idText.setBounds(110, 30, 900, 31);
		idText.setTextLimit(30);

		Button searchButton = new Button(shell, SWT.PUSH);
		searchButton.setText("Search");
		searchButton.setVisible(true);
		searchButton.setBounds(500, 30, 900, 31);

		// Create a dropdown Combo
		Combo searchOptions = new Combo(shell, SWT.DROP_DOWN);
		searchOptions.setItems(ITEMS);
		searchOptions.setBounds(30, 60, 100, 30);
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
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
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

				String[] titles = { "patient_id", "patientName", "dateOfBirth", "genderCode", "AddressID1",
						"addressType", "addressLine1", "addressLine2", "city", "state", "postalCode", "AddressID2",
						"addressType", "addressLine1", "addressLine2", "city", "state", "postalCode", "TelephoneID1",
						"telephoneType", "telephoneNumber", "telephonCountryCode", "TelephoneID2", "telephoneType",
						"telephoneNumber", "telephonCountryCode" };
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
				item.setText(11, firstPatient.getGenderCode().toString());
				item.setText(12, firstPatient.getPatient_id().toString());
				item.setText(13, firstPatient.getPatientName().toString());
				item.setText(14, firstPatient.getDateOfBirth().toString());
				item.setText(15, firstPatient.getGenderCode().toString());

				for (int i = 0; i < titles.length; i++) {
					table.getColumn(i).pack();
				}

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

		Button view = new Button(shell, SWT.PUSH);
		view.setText(" VIEW ");
	////	view.setEnabled(false);
		view.setVisible(true);
		view.setBounds(500, 300, 900, 31);

		Button modify = new Button(shell, SWT.PUSH);
		modify.setText("MODIFY");
		modify.setVisible(true);
		modify.setBounds(560, 300, 900, 31);

		Button delete = new Button(shell, SWT.PUSH);
		delete.setText("DELETE");
		delete.setVisible(true);
		delete.setBounds(620, 300, 900, 31);
		delete.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Called delete!");
				deletePatient(/*display,*/ shell);
				// System.out.println(e.getSource());
			}

			private void deletePatient( Shell shell) {
				// TODO Auto-generated method stub

			}
		});
		serachName.pack();
		idText.pack();
		searchOptions.pack();
		searchButton.pack();
		view.pack();
		modify.pack();
		delete.pack();

		shell.pack();
		shell.open();
		shell.setSize(700, 600);
		// shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		shell.dispose();
	}

	/*public static void main(String[] args) throws IOException, InterruptedException {
		// static Display display1 = new Display();
		new SWTPateintTable().mainProgramme();
	}*/

	
}
