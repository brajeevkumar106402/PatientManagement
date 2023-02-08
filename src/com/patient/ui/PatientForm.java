package com.patient.ui;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.patient.model.Address;
import com.patient.model.Patient;
import com.patient.model.Telephone;
import com.patient.rest.SWTRestClient;

public class PatientForm {

	private static PatientForm patientForm;
	Patient presentPatient;
	Shell shellNew;
	Font font1, font;
	Label id;
	Text idText;
	Label name;
	Text nameText;
	Label gender;
	Combo genderOptions;
	Label dob;
	Text doBText;
	Label addressId1;
	Text addressId1Text;
	Label addressType1;
	Text address1TypeText;
	Label address1Line1;
	Text address1Line1Text;
	Label address1Line2;
	Text address1Line2Text;
	Label city;
	Text cityText;
	Label state;
	Text stateText;
	Label postalCode;
	Text postalCodeText;
	Label addressId2;
	Text addressId2Text;
	Label addressType2;
	Text addressType2Text;
	Label address2Line1;
	Text address2Line1Text;
	Label address2Line2;
	Text address2Line2Text;
	Label city1;
	Text city1Text;
	Label state1;
	Text state1Text;
	Label postalCode1;
	Text postalCode1Text;
	Label telephoneID1;
	Text telephoneID1Text;
	Label telephoneType;
	Text telephoneTypeText;
	Label telephoneNumber;
	Text telephoneNumberText;
	Label telephonCountryCode;
	Text telephonCountryCodeText;
	Label telephoneID2;
	Text telephoneID2Text;
	Label telephoneType2;
	Text telephoneType2Text;
	Label telephoneNumber2;
	Text telephoneNumber2Text;
	Label telephonCountryCode2;
	Text telephonCountryCode2Text;
	Label country, country1;
	Text countryText, country1Text;
	Button create;

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
		font = new Font(display, "Arial", 22, SWT.BOLD);
		font1 = new Font(display, "Arial", 14, SWT.BOLD);
		shell.setText("welcome to Cerner BOT");
		shell.setFont(font1);

		Label label = new Label(shell, SWT.CENTER);

		label.setFont(font);
		label.setBounds(500, 5, 700, 31);
		label.setText("Patient Details Form");

		Label patientLabel = new Label(shell, SWT.None);
		patientLabel.setFont(font1);
		patientLabel.setBounds(100, 100, 800, 25);
		// name.setBounds(100, 80, 800, 25);
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
	public void creatPatientInfo(Display display, Shell shell) {

		id = new Label(shell, SWT.NONE);
		id.setBounds(100, 150, 900, 25);
		id.setText("PatientId");

		idText = new Text(shell, SWT.BORDER);
		idText.setBounds(300, 150, 900, 25);
		idText.setTextLimit(30);

		name = new Label(shell, SWT.NONE);
		name.setBounds(100, 180, 900, 25);
		name.setText("Name");

		nameText = new Text(shell, SWT.BORDER);
		nameText.setBounds(300, 180, 900, 25);
		nameText.setTextLimit(30);

		gender = new Label(shell, SWT.NONE);
		gender.setBounds(100, 210, 900, 30);
		gender.setText("Gender");

		String[] ITEMS = { "Male", "Female" };
		genderOptions = new Combo(shell, SWT.DROP_DOWN);
		genderOptions.setItems(ITEMS);
		genderOptions.setBounds(300, 210, 100, 30);

		dob = new Label(shell, SWT.NONE);
		dob.setBounds(100, 240, 900, 25);
		dob.setText("DOB");

		doBText = new Text(shell, SWT.BORDER);
		doBText.setBounds(300, 240, 900, 25);
		doBText.setTextLimit(30);

	/*	addressId1 = new Label(shell, SWT.NONE);
		addressId1.setBounds(100, 270, 900, 25);
		addressId1.setText("AddId1");

		addressId1Text = new Text(shell, SWT.BORDER);
		addressId1Text.setBounds(200, 270, 900, 25);
		addressId1Text.setTextLimit(30);*/

		addressType1 = new Label(shell, SWT.NONE);
		addressType1.setBounds(100, 300, 800, 25);
		addressType1.setText("AddType");

		address1TypeText = new Text(shell, SWT.BORDER);
		address1TypeText.setBounds(200, 300, 800, 25);
		address1TypeText.setTextLimit(30);
		address1TypeText.setText("Present");

		address1Line1 = new Label(shell, SWT.NONE);
		address1Line1.setBounds(100, 330, 800, 25);
		address1Line1.setText("AddLine1");

		address1Line1Text = new Text(shell, SWT.BORDER);
		address1Line1Text.setBounds(200, 330, 800, 25);
		address1Line1Text.setTextLimit(30);

		address1Line2 = new Label(shell, SWT.NONE);
		address1Line2.setBounds(100, 360, 800, 25);
		address1Line2.setText("AddLine2");

		address1Line2Text = new Text(shell, SWT.BORDER);
		address1Line2Text.setBounds(200, 360, 800, 25);
		address1Line2Text.setTextLimit(30);

		city = new Label(shell, SWT.NONE);
		city.setBounds(100, 390, 800, 25);
		city.setText("City");

		cityText = new Text(shell, SWT.BORDER);
		cityText.setBounds(200, 390, 800, 25);
		cityText.setTextLimit(30);

		state = new Label(shell, SWT.NONE);
		state.setBounds(100, 420, 800, 25);
		state.setText("State");

		stateText = new Text(shell, SWT.BORDER);
		stateText.setBounds(200, 420, 800, 25);
		stateText.setTextLimit(30);

		postalCode = new Label(shell, SWT.NONE);
		postalCode.setBounds(100, 450, 800, 25);
		postalCode.setText("PinCode");

		postalCodeText = new Text(shell, SWT.BORDER);
		postalCodeText.setBounds(200, 450, 800, 25);
		postalCodeText.setTextLimit(30);

		country = new Label(shell, SWT.NONE);
		country.setBounds(100, 480, 800, 25);
		country.setText("Country");

		countryText = new Text(shell, SWT.BORDER);
		countryText.setBounds(200, 480, 800, 25);
		countryText.setTextLimit(30);

	/*	addressId2 = new Label(shell, SWT.NONE);
		addressId2.setBounds(300, 270, 800, 25);
		addressId2.setText("AddId2");

		addressId2Text = new Text(shell, SWT.BORDER);
		addressId2Text.setBounds(450, 270, 800, 25);
		addressId2Text.setTextLimit(30);*/

		addressType2 = new Label(shell, SWT.NONE);
		addressType2.setBounds(300, 300, 800, 25);
		addressType2.setText("AddType2");

		addressType2Text = new Text(shell, SWT.BORDER);
		addressType2Text.setBounds(450, 300, 800, 25);
		addressType2Text.setTextLimit(30);
		addressType2Text.setText("Home");

		address2Line1 = new Label(shell, SWT.NONE);
		address2Line1.setBounds(300, 330, 800, 25);
		address2Line1.setText("AddLine1");

		address2Line1Text = new Text(shell, SWT.BORDER);
		address2Line1Text.setBounds(450, 330, 800, 25);
		address2Line1Text.setTextLimit(30);

		address2Line2 = new Label(shell, SWT.NONE);
		address2Line2.setBounds(300, 360, 800, 25);
		address2Line2.setText("AddLine2");

		address2Line2Text = new Text(shell, SWT.BORDER);
		address2Line2Text.setBounds(450, 360, 800, 25);
		address2Line2Text.setTextLimit(30);

		city1 = new Label(shell, SWT.NONE);
		city1.setBounds(300, 390, 800, 25);
		city1.setText("City");

		city1Text = new Text(shell, SWT.BORDER);
		city1Text.setBounds(450, 390, 800, 25);
		city1Text.setTextLimit(30);

		state1 = new Label(shell, SWT.NONE);
		state1.setBounds(300, 420, 800, 25);
		state1.setText("State");

		state1Text = new Text(shell, SWT.BORDER);
		state1Text.setBounds(450, 420, 800, 25);
		state1Text.setTextLimit(30);

		postalCode1 = new Label(shell, SWT.NONE);
		postalCode1.setBounds(300, 450, 800, 25);
		postalCode1.setText("PinCode");

		postalCode1Text = new Text(shell, SWT.BORDER);
		postalCode1Text.setBounds(450, 450, 800, 25);
		postalCode1Text.setTextLimit(30);

		country1 = new Label(shell, SWT.NONE);
		country1.setBounds(300, 480, 800, 25);
		country1.setText("Country");

		country1Text = new Text(shell, SWT.BORDER);
		country1Text.setBounds(450, 480, 800, 25);
		country1Text.setTextLimit(30);
		

	/*	telephoneID1 = new Label(shell, SWT.NONE);
		telephoneID1.setBounds(550, 270, 900, 20);
		telephoneID1.setText("TelID");

		telephoneID1Text = new Text(shell, SWT.BORDER);
		telephoneID1Text.setBounds(600, 270, 800, 25);
		telephoneID1Text.setTextLimit(30);*/

		telephoneType = new Label(shell, SWT.NONE);
		telephoneType.setBounds(550, 300, 800, 25);
		telephoneType.setText("TelType");

		telephoneTypeText = new Text(shell, SWT.BORDER);
		telephoneTypeText.setBounds(600, 300, 800, 25);
		telephoneTypeText.setTextLimit(30);
		telephoneTypeText.setText("OFFICE");

		telephoneNumber = new Label(shell, SWT.NONE);
		telephoneNumber.setBounds(550, 330, 800, 25);
		telephoneNumber.setText("TelNum");

		telephoneNumberText = new Text(shell, SWT.BORDER);
		telephoneNumberText.setBounds(600, 330, 800, 25);
		telephoneNumberText.setTextLimit(30);

		telephonCountryCode = new Label(shell, SWT.NONE);
		telephonCountryCode.setBounds(550, 360, 800, 25);
		telephonCountryCode.setText("ISDCode");

		telephonCountryCodeText = new Text(shell, SWT.BORDER);
		telephonCountryCodeText.setBounds(600, 360, 800, 25);
		telephonCountryCodeText.setTextLimit(30);

	/*	telephoneID2 = new Label(shell, SWT.NONE);
		telephoneID2.setBounds(700, 270, 800, 25);
		telephoneID2.setText("TelID");

		telephoneID2Text = new Text(shell, SWT.BORDER);
		telephoneID2Text.setBounds(750, 270, 800, 25);
		telephoneID2Text.setTextLimit(30);*/

		telephoneType2 = new Label(shell, SWT.NONE);
		telephoneType2.setBounds(700, 300, 800, 25);
		telephoneType2.setText("TelType");

		telephoneType2Text = new Text(shell, SWT.BORDER);
		telephoneType2Text.setBounds(750, 300, 800, 25);
		telephoneType2Text.setTextLimit(30);
		telephoneType2Text.setText("Home");

		telephoneNumber2 = new Label(shell, SWT.NONE);
		telephoneNumber2.setBounds(700, 330, 800, 25);
		telephoneNumber2.setText("TelNum");

		telephoneNumber2Text = new Text(shell, SWT.BORDER);
		telephoneNumber2Text.setBounds(750, 330, 800, 25);
		telephoneNumber2Text.setTextLimit(30);

		telephonCountryCode2 = new Label(shell, SWT.NONE);
		telephonCountryCode2.setBounds(700, 360, 800, 25);
		telephonCountryCode2.setText("ISDCode");

		telephonCountryCode2Text = new Text(shell, SWT.BORDER);
		telephonCountryCode2Text.setBounds(750, 360, 800, 25);
		telephonCountryCode2Text.setTextLimit(30);

		Button locateButton = new Button(shell, SWT.NONE);
		locateButton.setText("Pateint Search Locator");
		locateButton.setBounds(700, 80, 180, 31);
		locateButton.setFont(font1);

		// locateButton.setBackground(display.getSystemColor(SWT.COLOR_LIST_BACKGROUND));

		/*
		 * patient_id", "patientName", "dateOfBirth", "genderCode", "AddressID1",
		 * "addressType", "addressLine1", "addressLine2", "city", "state", "postalCode",
		 * "AddressID2", "addressType", "addressLine1", "addressLine2", "city", "state",
		 * "postalCode", "TelephoneID1", "telephoneType", "telephoneNumber",
		 * "telephonCountryCode", "TelephoneID2", "telephoneType", "telephoneNumber",
		 * "telephonCountryCode" };
		 */

		/*
		 * Text address1City = new Text(shell, SWT.BORDER); address1City.setBounds(300,
		 * 270, 800, 25); address1City.setTextLimit(30);
		 * 
		 * Text address1State = new Text(shell, SWT.BORDER);
		 * address1State.setBounds(300, 270, 800, 25); address1State.setTextLimit(30);
		 * 
		 * Text address1 = new Text(shell, SWT.BORDER); address1PinCode.setBounds(300,
		 * 270, 800, 25); address1PinCode.setTextLimit(30);
		 * 
		 * Text address2Text = new Text(shell, SWT.BORDER); address2Text.setBounds(550,
		 * 270, 800, 25); address2Text.setTextLimit(30);
		 * 
		 * Text phone1Text = new Text(shell, SWT.BORDER); phone1Text.setBounds(300, 300,
		 * 800, 25); phone1Text.setTextLimit(30);
		 * 
		 * Text phone2Text = new Text(shell, SWT.BORDER); phone2Text.setBounds(550, 300,
		 * 800, 25); phone2Text.setTextLimit(30);
		 */

		Button create = new Button(shell, SWT.PUSH);
		create.setText("SAVE");
		create.setVisible(true);
		create.setBounds(600, 500, 800, 25);

		Button close = new Button(shell, SWT.PUSH);
		close.setText("CLOSE");
		close.setVisible(true);
		close.setBounds(680, 500, 800, 25);

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
				createOrModifyPatientDetails(display, shell, false);
			}
		});

		close.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Called Close!");
			//	shell.close();
				display.dispose();
				// createOrModifyPatientDetails(display, shell, true);
			}
		});

		id.pack();
		idText.pack();
		name.pack();
		nameText.pack();
		gender.pack();
		genderOptions.pack();
		dob.pack();
		doBText.pack();
	//	addressId1.pack();
	//	addressId1Text.pack();
		addressType1.pack();
		address1TypeText.pack();
		address1Line1.pack();
		address1Line1Text.pack();
		address1Line2.pack();
		address1Line2Text.pack();
		city.pack();
		cityText.pack();
		state.pack();
		stateText.pack();
		postalCode.pack();
		postalCodeText.pack();
		country.pack();
		countryText.pack();
	//	addressId2.pack();
	//	addressId2Text.pack();
		addressType2.pack();
		addressType2Text.pack();
		address2Line1.pack();
		address2Line1Text.pack();
		address2Line2.pack();
		address2Line2Text.pack();
		city1.pack();
		city1Text.pack();
		state1.pack();
		state1Text.pack();
		postalCode1.pack();
		postalCode1Text.pack();
		country1.pack();
		country1Text.pack();
	//	telephoneID1.pack();
	//	telephoneID1Text.pack();
		telephoneType.pack();
		telephoneTypeText.pack();
		telephoneNumber.pack();
		telephoneNumberText.pack();
		telephonCountryCode.pack();
		telephonCountryCodeText.pack();
	//	telephoneID2.pack();
	//	telephoneID2Text.pack();
		telephoneType2.pack();
		telephoneType2Text.pack();
		telephoneNumber2.pack();
		telephoneNumber2Text.pack();
		telephonCountryCode2.pack();
		telephonCountryCode2Text.pack();
		// phone1Text.pack();
		// address1Text.pack();
		// phone2Text.pack();
		// address2Text.pack();

		create.pack();
		close.pack();
		locateButton.pack();
		shell.open();
		shell.layout();

	}

	public void creatPatientInfo(Shell shell, Patient oldPatient) {
		// shell = new Shell();
		// Display display = new Display();
		// createPatientHeader(display, shell);

		id = new Label(shell, SWT.NONE);
		id.setBounds(100, 150, 900, 25);
		id.setText("Id");

		idText = new Text(shell, SWT.BORDER);
		idText.setBounds(300, 150, 900, 25);
		idText.setTextLimit(30);
		idText.setText(oldPatient.getPatient_id().toString());

		name = new Label(shell, SWT.NONE);
		name.setBounds(100, 180, 900, 25);
		name.setText("Name");

		nameText = new Text(shell, SWT.BORDER);
		nameText.setBounds(300, 180, 900, 25);
		nameText.setText(oldPatient.getPatientName());

		gender = new Label(shell, SWT.NONE);
		gender.setBounds(100, 210, 900, 30);
		gender.setText("gender");

		String[] ITEMS = { "Male", "Female" };
		// Create a dropdown Combo
		genderOptions = new Combo(shell, SWT.DROP_DOWN);
		genderOptions.setItems(ITEMS);
		genderOptions.setBounds(300, 210, 100, 30);

		dob = new Label(shell, SWT.NONE);
		dob.setBounds(100, 240, 900, 25);
		dob.setText("dob");

		doBText = new Text(shell, SWT.BORDER);
		doBText.setBounds(300, 240, 900, 25);
		doBText.setText(oldPatient.getDateOfBirth());

		addressId1 = new Label(shell, SWT.NONE);
		addressId1.setBounds(100, 270, 900, 25);
		addressId1.setText("addressId1");

		addressId1Text = new Text(shell, SWT.BORDER);
		addressId1Text.setBounds(200, 270, 900, 25);
		addressId1Text.setText(oldPatient.getAddressList().get(0).getId().toString());
		System.out.println(addressId1Text.getText());

		Button create = new Button(shell, SWT.PUSH);
		create.setText("SAVE");
		create.setVisible(true);
		create.setBounds(500, 500, 800, 25);

		Button modify = new Button(shell, SWT.PUSH);
		modify.setText("MODIFY");
		modify.setVisible(true);
		modify.setBounds(550, 500, 800, 25);

		// register listener for the selection event

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
			}
		});

		id.pack();
		idText.pack();
		name.pack();
		nameText.pack();
		gender.pack();
		genderOptions.pack();
		dob.pack();
		doBText.pack();
		addressId1.pack();
		addressId1Text.pack();

		create.pack();
		modify.pack();
		shell.open();
		shell.layout();
		shell.setSize(600, 600);

	}

	protected void createOrModifyPatientDetails(Display display, Shell shell, boolean b) {
		System.out.println("inside create patient process");
		String message = populatePateintDataForSaveButton();
		MessageBox messageBox = new MessageBox(shell, SWT.ICON_WORKING);
		messageBox.setText("Success");
		messageBox.setMessage(message);
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
		// shell.close();

	}

	protected void createOrModifyPatientDetails(Display display, Shell shell, boolean b, Patient oldPatient,
			Patient newpatient) {
		System.out.println("inside create patient process");
		String message = populatePateintDataForSaveButton();
		MessageBox messageBox = new MessageBox(shell, SWT.ICON_WORKING);
		messageBox.setText("Success");
		messageBox.setMessage(message);
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
		// shell.close();

	}

	// createPatientHeader(display, shell);

	public static void main(String[] args) {
		patientForm = new PatientForm();
		patientForm.open();
	}

	public void setDataForDefaultsPage() {
		try {
			// idText.setText("");

			name.setText("");
			nameText.setText("");
			gender.setText("");
			genderOptions.setData(null);
			dob.setText("");
			doBText.setText("");
			// addressId1.setText("");
			addressId1Text.setText("");
			addressType1.setText("");
			address1TypeText.setText("");
			address1Line1.setText("");
			address1Line1Text.setText("");
			address1Line2.setText("");
			address1Line2Text.setText("");
			city.setText("");
			cityText.setText("");
			state.setText("");
			stateText.setText("");
			postalCode.setText("");
			postalCodeText.setText("");
			// addressId2.setText("");
			addressId2Text.setText("");
			addressType2.setText("");
			addressType2Text.setText("");
			address2Line1.setText("");
			address2Line1Text.setText("");
			address2Line2.setText("");
			address2Line2Text.setText("");
			city1.setText("");
			city1Text.setText("");
			state1.setText("");
			state1Text.setText("");
			postalCode1.setText("");
			postalCode1Text.setText("");
			// telephoneID1.setText("");
			telephoneID1Text.setText("");
			telephoneType.setText("");
			telephoneTypeText.setText("");
			telephoneNumber.setText("");
			telephoneNumberText.setText("");
			telephonCountryCode.setText("");
			telephonCountryCodeText.setText("");
			// telephoneID2.setText("");
			telephoneID2Text.setText("");
			telephoneType2.setText("");
			telephoneType2Text.setText("");
			telephoneNumber2.setText("");
			telephoneNumber2Text.setText("");
			telephonCountryCode2.setText("");
			telephonCountryCode2Text.setText("");
		} catch (Exception e) {
			e.getCause();
		}
	}

	public String populatePateintDataForSaveButton() {
		HttpResponse<String> saveResponse = null;
		try {
			presentPatient = new Patient();
			List<Address> addressList = new ArrayList<>();
			Address a1 = new Address();
			Address a2 = new Address();
			presentPatient.setPatientName(nameText.getText());
			presentPatient.setGenderCode("MALE");
			presentPatient.setDateOfBirth(doBText.getText());

			a1.setAddressType(address1TypeText.getText());
			a1.setAddressLine1(address1Line1Text.getText());
			a1.setAddressLine2(address1Line2Text.getText());
			a1.setCity(cityText.getText());
			a1.setState(stateText.getText());
			a1.setPostalCode(postalCodeText.getText());
			a1.setCountry("india");
			a2.setAddressType(addressType2Text.getText());
			a2.setAddressLine1(address2Line1Text.getText());
			a2.setAddressLine2(address2Line2Text.getText());
			a2.setCity(city1Text.getText());
			a2.setState(state1Text.getText());
			// a2.setPostalCode(postalCode1Text.getText());
			a2.setPostalCode("201009");
			a2.setCountry("india");
			addressList.add(0, a1);
			addressList.add(1, a2);

			presentPatient.setAddressList(addressList);

			List<Telephone> telephoneList = new ArrayList<>();
			Telephone t1 = new Telephone();
			Telephone t2 = new Telephone();
			t1.setTelephoneType(telephoneTypeText.getText());
			t1.setTelephoneNumber(telephoneNumberText.getText());
			t1.setTelephonCountryCode(telephonCountryCodeText.getText());
			t2.setTelephoneType(telephoneType2.getText());
			t2.setTelephoneNumber(telephoneNumber2.getText());
			t2.setTelephonCountryCode(telephonCountryCode2Text.getText());
			telephoneList.add(0, t1);
			telephoneList.add(1, t2);
			presentPatient.setTelephoneList(telephoneList);

			saveResponse = SWTRestClient.savePatient(presentPatient);
			System.out.println(saveResponse.body());
			return saveResponse.body();

		} catch (Exception e) {
			System.out.println(e.getCause());
		}
		return saveResponse.body();
	}

	public void createModifyFormView(Patient oldPatient, Patient newPatient, Shell shell) {

		patientForm = new PatientForm();
		patientForm.creatPatientInfo(shell, oldPatient);
		// patientForm.setDataForModifyPage(oldPatient);
		// patientForm.open();

	}

	private void setDataForModifyPage(Patient oldPatient) {
		nameText.setText(oldPatient.getPatientName());
		// genderOptions.setData(null);
		doBText.setText(oldPatient.getDateOfBirth());
		// addressId1.setText("");
		addressId1Text.setText(oldPatient.getAddressList().get(0).getId().toString());
		address1TypeText.setText(oldPatient.getAddressList().get(0).getAddressType());
		address1Line1Text.setText(oldPatient.getAddressList().get(0).getAddressLine1());
		address1Line2.setText("");
		address1Line2Text.setText("");
		city.setText("");
		cityText.setText("");
		state.setText("");
		stateText.setText("");
		postalCode.setText("");
		postalCodeText.setText("");
		// addressId2.setText("");
		addressId2Text.setText("");
		addressType2.setText("");
		addressType2Text.setText("");
		address2Line1.setText("");
		address2Line1Text.setText("");
		address2Line2.setText("");
		address2Line2Text.setText("");
		city1.setText("");
		city1Text.setText("");
		state1.setText("");
		state1Text.setText("");
		postalCode1.setText("");
		postalCode1Text.setText("");
		// telephoneID1.setText("");
		telephoneID1Text.setText("");
		telephoneType.setText("");
		telephoneTypeText.setText("");
		telephoneNumber.setText("");
		telephoneNumberText.setText("");
		telephonCountryCode.setText("");
		telephonCountryCodeText.setText("");
		// telephoneID2.setText("");
		telephoneID2Text.setText("");
		telephoneType2.setText("");
		telephoneType2Text.setText("");
		telephoneNumber2.setText("");
		telephoneNumber2Text.setText("");
		telephonCountryCode2.setText("");
		telephonCountryCode2Text.setText("");

	}

	public void setDataForFieldsForModify(Patient patient, Display display, boolean b) {
	Shell	shell = new Shell();
		try {
			creatPatientInfo(display,shell);
		nameText.setText(patient.getPatientName());
		// genderOptions.setData(null);
		doBText.setText(patient.getDateOfBirth());
		// addressId1.setText("");
	//	addressId1Text.setText(patient.getAddressList().get(0).getId().toString());
		address1TypeText.setText(patient.getAddressList().get(0).getAddressType());
		address1Line1Text.setText(patient.getAddressList().get(0).getAddressLine1());
		address1Line2Text.setText(patient.getAddressList().get(0).getAddressLine2());
		cityText.setText(patient.getAddressList().get(0).getCity());
				stateText.setText(patient.getAddressList().get(0).getState());
				postalCodeText.setText(patient.getAddressList().get(0).getPostalCode());
				countryText.setText(patient.getAddressList().get(0).getCountry()); 
				addressType2Text.setText(patient.getAddressList().get(1).getAddressType()); 
				address2Line1Text.setText(patient.getAddressList().get(1).getAddressLine1()); 
				address2Line2Text.setText(patient.getAddressList().get(1).getAddressLine2());
				city1Text.setText(patient.getAddressList().get(1).getCity()); 
				state1Text.setText(patient.getAddressList().get(1).getState());
				postalCode1Text.setText(patient.getAddressList().get(1).getPostalCode()); 
				country1Text.setText(patient.getAddressList().get(1).getCountry());
				telephoneTypeText.setText(patient.getTelephoneList().get(0).getTelephoneType());
				telephoneNumberText.setText(patient.getTelephoneList().get(0).getTelephoneNumber()); 
				telephonCountryCodeText.setText(patient.getTelephoneList().get(0).getTelephonCountryCode());
				telephoneType2Text.setText(patient.getTelephoneList().get(1).getTelephoneType()); 
				telephoneNumber2Text.setText(patient.getTelephoneList().get(1).getTelephoneNumber());
				telephonCountryCode2Text.setText(patient.getTelephoneList().get(1).getTelephonCountryCode());
		
		
	//	create.setEnabled(false);
		}catch(Exception e) {
			e.printStackTrace();
			e.getMessage();
		}
		
	}

}
