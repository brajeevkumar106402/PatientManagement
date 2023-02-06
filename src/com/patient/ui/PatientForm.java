package com.patient.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.patient.model.Patient;

public class PatientForm {

	private static PatientForm patientForm;
	Patient presentPatient;
	Shell shellNew;

	public Patient getPresentPatient() {
		return presentPatient;
	}

	public void setPresentPatient(Patient presentPatient) {
		this.presentPatient = presentPatient;
	}

	public Shell getShellNew() {
		return shellNew;
	}

	public void setShellNew(Shell shellNew) {
		this.shellNew = shellNew;
	}

	public static PatientForm getPatientForm() {
		return patientForm;
	}

	public static void setView(PatientForm patientForm) {
		PatientForm.patientForm = patientForm;
	}

	public void open() {
		Display display = new Display();
		shellNew = new Shell();
		createPatientHeader(display, shellNew);
	}

	public void createPatientHeader(Display display, Shell shell) {
		shell.setText("welcome to Cerner BOT"); //

		Label label = new Label(shell, SWT.CENTER);
		Font font = new Font(display, "Arial", 22, SWT.ITALIC);
		Font font1 = new Font(display, "Arial", 12, SWT.ITALIC);
		label.setFont(font);
		label.setBounds(500, 5, 700, 31);
		label.setText("Patient Details Input form");

		Label patientLabel = new Label(shell, SWT.None);
		patientLabel.setFont(font1);
		patientLabel.setBounds(100, 100, 900, 31);
		// name.setBounds(100, 80, 900, 31);
		patientLabel.setText("Patient Details");

		creatPatientInfo(display, shell);

		// set widgets size to their preferred size
		label.pack();
		patientLabel.pack();
		shell.open();
		shell.layout();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		// display.dispose();
	}

	// method to create new patient details
	private void creatPatientInfo(Display display, Shell shell) {

		Label id = new Label(shell, SWT.NONE);
		id.setBounds(100, 150, 900, 31);
		id.setText("Id");
		Label name = new Label(shell, SWT.NONE);
		name.setBounds(100, 180, 900, 31);
		name.setText("Name");

		Label gender = new Label(shell, SWT.NONE);
		gender.setBounds(100, 210, 900, 31);
		gender.setText("gender");

		Label dob = new Label(shell, SWT.NONE);
		dob.setBounds(100, 240, 900, 31);
		dob.setText("dob");

		Label addressId1 = new Label(shell, SWT.NONE);
		addressId1.setBounds(100, 270, 900, 31);
		addressId1.setText("address1");

		Label addressID2 = new Label(shell, SWT.NONE);
		addressID2.setBounds(500, 270, 900, 31);
		addressID2.setText("address2");

		Label phoneId1 = new Label(shell, SWT.NONE);
		phoneId1.setBounds(100, 300, 900, 31);
		phoneId1.setText("phoneId1");

		Label phoneId2 = new Label(shell, SWT.NONE);
		phoneId2.setBounds(500, 300, 900, 31);
		phoneId2.setText("phoneId2");
		Button locateButton = new Button(shell, SWT.NONE);
		locateButton.setText("Pateint Search Locator");
		locateButton.setBounds(600, 80, 180, 31);
		// locateButton.setBackground(display.getSystemColor(SWT.COLOR_LIST_BACKGROUND));

		Text idText = new Text(shell, SWT.BORDER);
		idText.setBounds(300, 150, 900, 31);
		idText.setTextLimit(30);

		Text nameText = new Text(shell, SWT.BORDER);
		nameText.setBounds(300, 180, 900, 31);
		nameText.setTextLimit(30);

		String[] ITEMS = { "Male", "Female" };

		// Create a dropdown Combo
		Combo genderOptions = new Combo(shell, SWT.DROP_DOWN);
		genderOptions.setItems(ITEMS);
		genderOptions.setBounds(300, 210, 100, 30);

		Text doBText = new Text(shell, SWT.BORDER);
		doBText.setBounds(300, 240, 900, 30);
		doBText.setTextLimit(30);
		/*
		 * patient_id", "patientName", "dateOfBirth", "genderCode", "AddressID1",
		 * "addressType", "addressLine1", "addressLine2", "city", "state", "postalCode",
		 * "AddressID2", "addressType", "addressLine1", "addressLine2", "city", "state",
		 * "postalCode", "TelephoneID1", "telephoneType", "telephoneNumber",
		 * "telephonCountryCode", "TelephoneID2", "telephoneType", "telephoneNumber",
		 * "telephonCountryCode" };
		 */

		Text address1Text = new Text(shell, SWT.BORDER);
		address1Text.setBounds(300, 270, 900, 31);
		address1Text.setTextLimit(30);

		Text address2Text = new Text(shell, SWT.BORDER);
		address2Text.setBounds(550, 270, 900, 31);
		address2Text.setTextLimit(30);

		Text phone1Text = new Text(shell, SWT.BORDER);
		phone1Text.setBounds(300, 300, 900, 31);
		phone1Text.setTextLimit(30);

		Text phone2Text = new Text(shell, SWT.BORDER);
		phone2Text.setBounds(550, 300, 900, 31);
		phone2Text.setTextLimit(30);

		Button create = new Button(shell, SWT.PUSH);
		create.setText("SAVE");
		create.setVisible(true);
		create.setBounds(100, 330, 900, 31);

		Button modify = new Button(shell, SWT.PUSH);
		modify.setText("MODIFY");
		modify.setVisible(true);
		modify.setBounds(200, 330, 900, 31);

		// register listener for the selection event
		locateButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Called locateButton!");
				new SWTPateintTable().mainProgramme(display);
				System.out.println("Called locateButton!");

			}
		});
		create.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// createOrModifyPatientDetails(display, shell, false);
			}
		});

		modify.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Called modify!");
				// createOrModifyPatientDetails(display, shell, true);
				// System.out.println(e.getSource());
			}
		});

		id.pack();
		name.pack();
		gender.pack();
		dob.pack();
		phoneId1.pack();
		phoneId2.pack();
		addressId1.pack();
		addressID2.pack();
		idText.pack();
		nameText.pack();
		genderOptions.pack();
		doBText.pack();
		phone1Text.pack();
		address1Text.pack();
		phone2Text.pack();
		address2Text.pack();
		create.pack();
		modify.pack();
		locateButton.pack();
		shell.open();
		shell.layout();

	}

	protected void createOrModifyPatientDetails(Display display, Shell shell, boolean b) {
		System.out.println("inside create patient process");
		String name = "nameText.getText()";
		createPatientHeader(display, shell);

	}

	public static void main(String[] args) {
		patientForm = new PatientForm();
		patientForm.open();
	}

}
