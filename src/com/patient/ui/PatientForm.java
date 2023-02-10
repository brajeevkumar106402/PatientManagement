package com.patient.ui;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Rectangle;
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

	private static final String SAVE_MESSAGE = "Patient is Successfully created and its response status code is";
	static PatientForm patientForm;
	Patient presentPatient;
	static Shell shellNew;
	Display display;
	static Font font1, font;
	static Label id, patientLabel, label;
	Color buttonColor;
	Color headingColor;

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
	static Button save, close, locateButton;

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

	public PatientForm getPatientForm() {
		return patientForm;
	}

	public static void setView(PatientForm patientForm) {
		patientForm = patientForm;
	}

	/*
	 * Method to create Dispaly and Shell object and this method is starting method
	 * of application which creates Patient login form *
	 * 
	 */
	public void open() {
		Display display = new Display();
		shellNew = new Shell();
		createPatientHeader(display, shellNew);
	}

	/*
	 * Method to create new patient header page details *
	 * 
	 * @Param1 Display reference *
	 * 
	 * @param2 Shell reference
	 */
	public void createPatientHeader(Display display, Shell shell) {
		font = new Font(display, "Arial", 22, SWT.BOLD);
		font1 = new Font(display, "Arial", 14, SWT.BOLD);
		headingColor = new Color(display, 187, 221, 255);
		shell.setText("welcome to Cerner Dev Academy");
		shell.setFont(font1);
		label = new Label(shell, SWT.CENTER);
		label.setFont(font);
		label.setBounds(500, 5, 700, 31);
		label.setText("Patient Details Form");
		label.setBackground(headingColor);

		patientLabel = new Label(shell, SWT.None);
		patientLabel.setFont(font1);
		patientLabel.setBounds(100, 100, 800, 25);
		patientLabel.setText("Patient Details");
		patientLabel.setBackground(headingColor);
		creatPatientInfo(display, shell);
		label.pack();
		patientLabel.pack();
		shell.open();
		shell.layout();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/*
	 * Method to create new patient body details *
	 * 
	 * @Param1 Display reference *
	 * 
	 * @param2 Shell reference
	 */
	public void creatPatientInfo(Display display, Shell shell) {
		id = new Label(shell, SWT.NONE);
		id.setBounds(100, 150, 900, 25);
		id.setText("PatientId");
		buttonColor = new Color(display, 224, 255, 255);
		id.setForeground(display.getSystemColor(SWT.COLOR_BLACK));
		id.setBackground(display.getSystemColor(SWT.COLOR_GREEN));

		idText = new Text(shell, SWT.BORDER);
		idText.setBounds(300, 150, 900, 25);
		idText.setTextLimit(30);
		idText.setText("AutoId");
		idText.setEditable(false);
		idText.setVisible(false);

		name = new Label(shell, SWT.NONE);
		name.setBounds(100, 180, 900, 25);
		name.setText("Name");
		name.setBackground(display.getSystemColor(SWT.COLOR_GREEN));

		nameText = new Text(shell, SWT.BORDER);
		nameText.setBounds(300, 180, 900, 25);
		nameText.setTextLimit(30);

		gender = new Label(shell, SWT.NONE);
		gender.setBounds(100, 210, 900, 40);
		gender.setText("Gender");
		gender.setBackground(display.getSystemColor(SWT.COLOR_GREEN));

		String[] genderItems = { "MALE", "FEMALE" };
		genderOptions = new Combo(shell, SWT.DROP_DOWN);
		genderOptions.setItems(genderItems);
		genderOptions.setBounds(300, 210, 100, 30);

		dob = new Label(shell, SWT.NONE);
		dob.setBounds(100, 240, 900, 25);
		dob.setText("DOB");
		dob.setBackground(display.getSystemColor(SWT.COLOR_GREEN));

		doBText = new Text(shell, SWT.BORDER);
		doBText.setBounds(300, 240, 900, 25);
		doBText.setTextLimit(30);

		addressType1 = new Label(shell, SWT.NONE);
		addressType1.setBounds(100, 300, 800, 25);
		addressType1.setText("AddressType");
		addressType1.setBackground(display.getSystemColor(SWT.COLOR_GRAY));

		address1TypeText = new Text(shell, SWT.BORDER);
		address1TypeText.setBounds(200, 300, 800, 25);
		address1TypeText.setTextLimit(30);
		address1TypeText.setText("PresentAddr");

		address1Line1 = new Label(shell, SWT.NONE);
		address1Line1.setBounds(100, 330, 800, 25);
		address1Line1.setText("AddressLine1");
		address1Line1.setBackground(display.getSystemColor(SWT.COLOR_GRAY));

		address1Line1Text = new Text(shell, SWT.BORDER);
		address1Line1Text.setBounds(200, 330, 800, 25);
		address1Line1Text.setTextLimit(30);

		address1Line2 = new Label(shell, SWT.NONE);
		address1Line2.setBounds(100, 360, 800, 25);
		address1Line2.setText("AddressLine2");
		address1Line2.setBackground(display.getSystemColor(SWT.COLOR_GRAY));

		address1Line2Text = new Text(shell, SWT.BORDER);
		address1Line2Text.setBounds(200, 360, 800, 25);
		address1Line2Text.setTextLimit(30);

		city = new Label(shell, SWT.NONE);
		city.setBounds(100, 390, 800, 25);
		city.setText("City");
		city.setBackground(display.getSystemColor(SWT.COLOR_GRAY));

		cityText = new Text(shell, SWT.BORDER);
		cityText.setBounds(200, 390, 800, 25);
		cityText.setTextLimit(30);

		state = new Label(shell, SWT.NONE);
		state.setBounds(100, 420, 800, 25);
		state.setText("State");
		state.setBackground(display.getSystemColor(SWT.COLOR_GRAY));

		stateText = new Text(shell, SWT.BORDER);
		stateText.setBounds(200, 420, 800, 25);
		stateText.setTextLimit(30);

		postalCode = new Label(shell, SWT.NONE);
		postalCode.setBounds(100, 450, 800, 25);
		postalCode.setText("PinCode");
		postalCode.setBackground(display.getSystemColor(SWT.COLOR_GRAY));

		postalCodeText = new Text(shell, SWT.BORDER);
		postalCodeText.setBounds(200, 450, 800, 25);
		postalCodeText.setTextLimit(30);

		country = new Label(shell, SWT.NONE);
		country.setBounds(100, 480, 800, 25);
		country.setText("Country");
		country.setBackground(display.getSystemColor(SWT.COLOR_GRAY));

		countryText = new Text(shell, SWT.BORDER);
		countryText.setBounds(200, 480, 800, 25);
		countryText.setTextLimit(30);

		addressType2 = new Label(shell, SWT.NONE);
		addressType2.setBounds(300, 300, 800, 25);
		addressType2.setText("AddressType2");
		addressType2.setBackground(display.getSystemColor(SWT.COLOR_GRAY));

		addressType2Text = new Text(shell, SWT.BORDER);
		addressType2Text.setBounds(450, 300, 800, 25);
		addressType2Text.setTextLimit(30);
		addressType2Text.setText("HomeAddr ");

		address2Line1 = new Label(shell, SWT.NONE);
		address2Line1.setBounds(300, 330, 800, 25);
		address2Line1.setText("AddressLine1");
		address2Line1.setBackground(display.getSystemColor(SWT.COLOR_GRAY));

		address2Line1Text = new Text(shell, SWT.BORDER);
		address2Line1Text.setBounds(450, 330, 800, 25);
		address2Line1Text.setTextLimit(30);

		address2Line2 = new Label(shell, SWT.NONE);
		address2Line2.setBounds(300, 360, 800, 25);
		address2Line2.setText("AddressLine2");
		address2Line2.setBackground(display.getSystemColor(SWT.COLOR_GRAY));

		address2Line2Text = new Text(shell, SWT.BORDER);
		address2Line2Text.setBounds(450, 360, 800, 25);
		address2Line2Text.setTextLimit(30);

		city1 = new Label(shell, SWT.NONE);
		city1.setBounds(300, 390, 800, 25);
		city1.setText("City");
		city1.setBackground(display.getSystemColor(SWT.COLOR_GRAY));

		city1Text = new Text(shell, SWT.BORDER);
		city1Text.setBounds(450, 390, 800, 25);
		city1Text.setTextLimit(30);

		state1 = new Label(shell, SWT.NONE);
		state1.setBounds(300, 420, 800, 25);
		state1.setText("State");
		state1.setBackground(display.getSystemColor(SWT.COLOR_GRAY));

		state1Text = new Text(shell, SWT.BORDER);
		state1Text.setBounds(450, 420, 800, 25);
		state1Text.setTextLimit(30);

		postalCode1 = new Label(shell, SWT.NONE);
		postalCode1.setBounds(300, 450, 800, 25);
		postalCode1.setText("PinCode");
		postalCode1.setBackground(display.getSystemColor(SWT.COLOR_GRAY));

		postalCode1Text = new Text(shell, SWT.BORDER);
		postalCode1Text.setBounds(450, 450, 800, 25);
		postalCode1Text.setTextLimit(30);

		country1 = new Label(shell, SWT.NONE);
		country1.setBounds(300, 480, 800, 25);
		country1.setText("Country");
		country1.setBackground(display.getSystemColor(SWT.COLOR_GRAY));

		country1Text = new Text(shell, SWT.BORDER);
		country1Text.setBounds(450, 480, 800, 25);
		country1Text.setTextLimit(30);

		telephoneType = new Label(shell, SWT.NONE);
		telephoneType.setBounds(550, 300, 800, 25);
		telephoneType.setText("TelType");
		telephoneType.setBackground(display.getSystemColor(SWT.COLOR_GRAY));

		telephoneTypeText = new Text(shell, SWT.BORDER);
		telephoneTypeText.setBounds(600, 300, 800, 25);
		telephoneTypeText.setTextLimit(30);
		telephoneTypeText.setText("Office          ");

		telephoneNumber = new Label(shell, SWT.NONE);
		telephoneNumber.setBounds(550, 330, 800, 25);
		telephoneNumber.setText("TelNum");
		telephoneNumber.setBackground(display.getSystemColor(SWT.COLOR_GRAY));

		telephoneNumberText = new Text(shell, SWT.BORDER);
		telephoneNumberText.setBounds(600, 330, 800, 25);
		telephoneNumberText.setTextLimit(30);

		telephonCountryCode = new Label(shell, SWT.NONE);
		telephonCountryCode.setBounds(550, 360, 800, 25);
		telephonCountryCode.setText("ISDCode");
		telephonCountryCode.setBackground(display.getSystemColor(SWT.COLOR_GRAY));

		telephonCountryCodeText = new Text(shell, SWT.BORDER);
		telephonCountryCodeText.setBounds(600, 360, 800, 25);
		telephonCountryCodeText.setTextLimit(30);

		telephoneType2 = new Label(shell, SWT.NONE);
		telephoneType2.setBounds(700, 300, 800, 25);
		telephoneType2.setText("TelType");
		telephoneType2.setBackground(display.getSystemColor(SWT.COLOR_GRAY));

		telephoneType2Text = new Text(shell, SWT.BORDER);
		telephoneType2Text.setBounds(750, 300, 800, 25);
		telephoneType2Text.setTextLimit(30);
		telephoneType2Text.setText("Home          ");

		telephoneNumber2 = new Label(shell, SWT.NONE);
		telephoneNumber2.setBounds(700, 330, 800, 25);
		telephoneNumber2.setText("TelNum");
		telephoneNumber2.setBackground(display.getSystemColor(SWT.COLOR_GRAY));

		telephoneNumber2Text = new Text(shell, SWT.BORDER);
		telephoneNumber2Text.setBounds(750, 330, 800, 25);
		telephoneNumber2Text.setTextLimit(30);

		telephonCountryCode2 = new Label(shell, SWT.NONE);
		telephonCountryCode2.setBounds(700, 360, 800, 25);
		telephonCountryCode2.setText("ISDCode");
		telephonCountryCode2.setBackground(display.getSystemColor(SWT.COLOR_GRAY));

		telephonCountryCode2Text = new Text(shell, SWT.BORDER);
		telephonCountryCode2Text.setBounds(750, 360, 800, 25);
		telephonCountryCode2Text.setTextLimit(30);

		locateButton = new Button(shell, SWT.NONE);
		locateButton.setText("Patient Search Button");
		locateButton.setBounds(700, 80, 180, 31);
		locateButton.setFont(font1);
		locateButton.setBackground(buttonColor);

		save = new Button(shell, SWT.PUSH);
		save.setText("SAVE");
		save.setVisible(true);
		save.setBounds(600, 500, 800, 25);
		save.setBackground(buttonColor);

		close = new Button(shell, SWT.PUSH);
		close.setText("CLOSE");
		close.setVisible(true);
		close.setBounds(680, 500, 800, 25);
		close.setBackground(buttonColor);

		locateButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				SWTPateintTable swt = new SWTPateintTable(display);
				swt.setForm(patientForm);
				swt.loadPatientAPI();

			}
		});
		save.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				createOrModifyPatientDetails(display, shell, false);
			}
		});

		close.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.close();
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
		telephoneType.pack();
		telephoneTypeText.pack();
		telephoneNumber.pack();
		telephoneNumberText.pack();
		telephonCountryCode.pack();
		telephonCountryCodeText.pack();
		telephoneType2.pack();
		telephoneType2Text.pack();
		telephoneNumber2.pack();
		telephoneNumber2Text.pack();
		telephonCountryCode2.pack();
		telephonCountryCode2Text.pack();
		save.pack();
		close.pack();
		locateButton.pack();
		shell.open();
		shell.layout();

	}

	/*
	 * Method to create new patient body details with the help of Patient object
	 * 
	 * @Param 1 Display reference
	 * 
	 * @param 2 Patient reference
	 */
	public void creatPatientInfo(Shell shell, Patient oldPatient) {
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

		String[] ITEMS = { "MALE", "FEMALE" };
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
			}
		});

		modify.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {			
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
		Rectangle bounds = shell.computeTrim(0, 0, 700, 600);
		shell.setSize(bounds.width, bounds.height);
		// shell.setSize(800, 700);

	}

	/*
	 * Method to createOrModifyPatientDetails with the help of boolean flag
	 * 
	 * @Param 1 Display reference
	 * 
	 * @param 2 Patient reference
	 * 
	 * @Param 3 boolean reference if boolean reference is true than patient details
	 * is modified otherwise it is only available for view
	 */
	protected void createOrModifyPatientDetails(Display display, Shell shell, boolean b) {
		System.out.println("inside create patient process");
		String message = populatePateintDataForSaveButton();
		MessageBox messageBox = new MessageBox(shell, SWT.ICON_WORKING);
		messageBox.setText("Success");
		messageBox.setMessage(SAVE_MESSAGE + message);
		int buttonId = messageBox.open();
		switch (buttonId) {
		case SWT.YES:
		case SWT.NO:
			break;
		case SWT.CANCEL:
		}

		System.out.println(buttonId);
		if (messageBox.getText().equals("Success")) {
			clearFormData();
		}

	}

	/*
	 * Method which open the Patient login form
	 * 
	 */
	
	public static void main(String[] args) {
		patientForm = new PatientForm();
		patientForm.open();
	}

	/*
	 * Method clearFormData This method is used to set the login from data to
	 * default value. Patient UI and hit the rest api create otpin and persist the
	 * patient data in DB
	 * 
	 */
	public void clearFormData() {
		try {
			nameText.setText("");
			genderOptions.setItems("MALE,FEMALE");
			doBText.setText("");
			address1Line1Text.setText("");
			address1Line2Text.setText("");
			cityText.setText("");
			stateText.setText("");
			postalCodeText.setText("");
			countryText.setText("");
			address2Line1Text.setText("");
			address2Line2Text.setText("");
			city1Text.setText("");
			state1Text.setText("");
			postalCode1Text.setText("");
			country1Text.setText("");
			telephoneTypeText.setText("");
			telephoneNumberText.setText("");
			telephonCountryCodeText.setText("");
			telephoneNumber2Text.setText("");
			telephonCountryCode2Text.setText("");
		} catch (Exception e) {
			e.getCause();
		}
	}

	/*
	 * Method topopulatePateintDataForSaveButton This method captured the data from
	 * Patient UI and hit the rest api create otpin and persist the patient data in
	 * DB
	 * 
	 */
	public String populatePateintDataForSaveButton() {
		HttpResponse<String> saveResponse = null;
		try {
			presentPatient = new Patient();
			List<Address> addressList = new ArrayList<>();
			Address a1 = new Address();
			Address a2 = new Address();
			presentPatient.setPatientName(nameText.getText().replaceAll(" ",""));
			presentPatient.setGenderCode(genderOptions.getItem(0));

			presentPatient.setDateOfBirth(doBText.getText());

			a1.setAddressType(address1TypeText.getText());
			a1.setAddressLine1(address1Line1Text.getText());
			a1.setAddressLine2(address1Line2Text.getText());
			a1.setCity(cityText.getText());
			a1.setState(stateText.getText());
			a1.setPostalCode(postalCodeText.getText());
			a1.setCountry(countryText.getText());
			a2.setAddressType(addressType2Text.getText());
			a2.setAddressLine1(address2Line1Text.getText());
			a2.setAddressLine2(address2Line2Text.getText());
			a2.setCity(city1Text.getText());
			a2.setState(state1Text.getText());
			a2.setPostalCode(postalCode1Text.getText());
			a2.setCountry(country1Text.getText());
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

		} catch (Exception e) {
			System.out.println(e.getCause());
		}
		return Integer.valueOf(saveResponse.statusCode()).toString();
	}

	public void createModifyFormView(Patient oldPatient, Patient newPatient, Shell shell) {

		patientForm = new PatientForm();
		patientForm.creatPatientInfo(shell, oldPatient);
		// patientForm.setDataForModifyPage(oldPatient);
		// patientForm.open();

	}
	/*
	 * Method to populate PateintData For crate and modify Button This method
	 * captured the data from Patient UI and hit the create and update api and and
	 * persist the patient data in DB
	 * 
	 */

	public void setDataForFieldsForModifyAndCrate(Patient patient, Display display, boolean b) {
		try {
			if (b == false && patient != null
					&& (patient.getAddressList().size() > 1 && patient.getAddressList().size() < 3)
					&& (patient.getTelephoneList().size() > 1 && patient.getTelephoneList().size() < 3)) {
				nameText.setText(patient.getPatientName());
				nameText.setEditable(false);
				Gender male = Gender.MALE;
				Gender femmale = Gender.MALE;
				genderOptions.setItems("MALE", "FEMALE");
				if (patient.getGenderCode().equalsIgnoreCase("MALE")) {
					genderOptions.select(male.ordinal());
				} else
					genderOptions.select(femmale.ordinal());

				genderOptions.setEnabled(false);

				doBText.setText(patient.getDateOfBirth() != null ? patient.getDateOfBirth() : "");
				doBText.setEditable(false);
				address1TypeText.setText(patient.getAddressList().get(0).getAddressType() != null
						? patient.getAddressList().get(0).getAddressType()
						: "");
				address1TypeText.setEditable(false);
				address1Line1Text.setText(patient.getAddressList().get(0).getAddressLine1() != null
						? patient.getAddressList().get(0).getAddressLine1()
						: "");
				address1Line1Text.setEditable(false);
				address1Line2Text.setText(patient.getAddressList().get(0).getAddressLine2() != null
						? patient.getAddressList().get(0).getAddressLine2()
						: "");
				address1Line2Text.setEditable(false);
				cityText.setText(
						patient.getAddressList().get(0).getCity() != null ? patient.getAddressList().get(0).getCity()
								: "");
				cityText.setEditable(false);
				stateText.setText(
						patient.getAddressList().get(0).getState() != null ? patient.getAddressList().get(0).getState()
								: "");
				stateText.setEditable(false);
				postalCodeText.setText(patient.getAddressList().get(0).getPostalCode() != null
						? patient.getAddressList().get(0).getPostalCode()
						: "");
				postalCodeText.setEditable(false);
				countryText.setText(patient.getAddressList().get(0).getCountry() != null
						? patient.getAddressList().get(0).getCountry()
						: "");
				countryText.setEditable(false);
				addressType2Text.setText(patient.getAddressList().get(1).getAddressType() != null
						? patient.getAddressList().get(1).getAddressType()
						: "");
				addressType2Text.setEditable(false);
				address2Line1Text.setText(patient.getAddressList().get(1).getAddressLine1() != null
						? patient.getAddressList().get(1).getAddressLine1()
						: "");
				address2Line1Text.setEditable(false);
				address2Line2Text.setText(patient.getAddressList().get(1).getAddressLine2() != null
						? patient.getAddressList().get(1).getAddressLine2()
						: "");
				address2Line2Text.setEditable(false);
				city1Text.setText(
						patient.getAddressList().get(1).getCity() != null ? patient.getAddressList().get(1).getCity()
								: "");
				city1Text.setEditable(false);
				state1Text.setText(
						patient.getAddressList().get(1).getState() != null ? patient.getAddressList().get(1).getState()
								: "");
				state1Text.setEditable(false);
				postalCode1Text.setText(patient.getAddressList().get(1).getPostalCode() != null
						? patient.getAddressList().get(1).getPostalCode()
						: "");
				postalCode1Text.setEditable(false);
				country1Text.setText(patient.getAddressList().get(1).getCountry() != null
						? patient.getAddressList().get(1).getCountry()
						: "");
				country1Text.setEditable(false);
				telephoneTypeText.setText(patient.getTelephoneList().get(0).getTelephoneType() != null
						? patient.getTelephoneList().get(0).getTelephoneType()
						: "");
				telephoneTypeText.setEditable(false);
				telephoneNumberText.setText(patient.getTelephoneList().get(0).getTelephoneNumber() != null
						? patient.getTelephoneList().get(0).getTelephoneNumber()
						: "");
				telephoneNumberText.setEditable(false);
				telephonCountryCodeText.setText(patient.getTelephoneList().get(0).getTelephonCountryCode() != null
						? patient.getTelephoneList().get(0).getTelephonCountryCode()
						: "");
				telephonCountryCodeText.setEditable(false);
				telephoneType2Text.setText(patient.getTelephoneList().get(1).getTelephoneType() != null
						? patient.getTelephoneList().get(1).getTelephoneType()
						: "");
				telephoneType2Text.setEditable(false);
				telephoneNumber2Text.setText(patient.getTelephoneList().get(1).getTelephoneNumber() != null
						? patient.getTelephoneList().get(1).getTelephoneNumber()
						: "");
				telephoneNumber2Text.setEditable(false);
				telephonCountryCode2Text.setText(patient.getTelephoneList().get(1).getTelephonCountryCode() != null
						? patient.getTelephoneList().get(1).getTelephoneNumber()
						: "");
				telephonCountryCode2Text.setEditable(false);

				save.setEnabled(false);
			}
			// code for save button
			else if (b == true && patient != null
					&& (patient.getAddressList().size() > 1 && patient.getAddressList().size() < 3)
					&& (patient.getTelephoneList().size() > 1 && patient.getTelephoneList().size() < 3)) {
				nameText.setText(patient.getPatientName());
				// genderOptions.setData(null);
				doBText.setText(patient.getDateOfBirth() != null ? patient.getDateOfBirth() : "");
				doBText.setEditable(true);
				genderOptions.setItems((patient.getGenderCode() != null ? patient.getGenderCode() : null));
				Gender male = Gender.MALE;
				Gender femmale = Gender.MALE;
				genderOptions.setItems("MALE", "FEMALE");
				if (patient.getGenderCode().equalsIgnoreCase("MALE")) {
					genderOptions.select(male.ordinal());
				} else
					genderOptions.select(femmale.ordinal());

				genderOptions.setEnabled(false);
				genderOptions.setEnabled(true);
				address1TypeText.setText(patient.getAddressList().get(0).getAddressType() != null
						? patient.getAddressList().get(0).getAddressType()
						: "");
				address1TypeText.setEditable(true);
				address1Line1Text.setText(patient.getAddressList().get(0).getAddressLine1() != null
						? patient.getAddressList().get(0).getAddressLine1()
						: "");
				address1Line1Text.setEditable(true);
				address1Line2Text.setText(patient.getAddressList().get(0).getAddressLine2() != null
						? patient.getAddressList().get(0).getAddressLine2()
						: "");
				address1Line2Text.setEditable(true);
				cityText.setText(
						patient.getAddressList().get(0).getCity() != null ? patient.getAddressList().get(0).getCity()
								: "");
				cityText.setEditable(true);
				stateText.setText(
						patient.getAddressList().get(0).getState() != null ? patient.getAddressList().get(0).getState()
								: "");
				stateText.setEditable(true);
				postalCodeText.setText(patient.getAddressList().get(0).getPostalCode() != null
						? patient.getAddressList().get(0).getPostalCode()
						: "");
				postalCodeText.setEditable(true);
				countryText.setText(patient.getAddressList().get(0).getCountry() != null
						? patient.getAddressList().get(0).getCountry()
						: "");
				countryText.setEditable(true);
				addressType2Text.setText(patient.getAddressList().get(1).getAddressType() != null
						? patient.getAddressList().get(1).getAddressType()
						: "");
				addressType2Text.setEditable(true);
				address2Line1Text.setText(patient.getAddressList().get(1).getAddressLine1() != null
						? patient.getAddressList().get(1).getAddressLine1()
						: "");
				address2Line1Text.setEditable(true);
				address2Line2Text.setText(patient.getAddressList().get(1).getAddressLine2() != null
						? patient.getAddressList().get(1).getAddressLine2()
						: "");
				address2Line2Text.setEditable(true);
				city1Text.setText(
						patient.getAddressList().get(1).getCity() != null ? patient.getAddressList().get(1).getCity()
								: "");
				city1Text.setEditable(true);
				state1Text.setText(
						patient.getAddressList().get(1).getState() != null ? patient.getAddressList().get(1).getState()
								: "");
				state1Text.setEditable(true);
				postalCode1Text.setText(patient.getAddressList().get(1).getPostalCode() != null
						? patient.getAddressList().get(1).getPostalCode()
						: "");
				postalCode1Text.setEditable(true);
				country1Text.setText(patient.getAddressList().get(1).getCountry() != null
						? patient.getAddressList().get(1).getCountry()
						: "");
				country1Text.setEditable(true);
				telephoneTypeText.setText(patient.getTelephoneList().get(0).getTelephoneType() != null
						? patient.getTelephoneList().get(0).getTelephoneType()
						: "");
				telephoneTypeText.setEditable(true);
				telephoneNumberText.setText(patient.getTelephoneList().get(0).getTelephoneNumber() != null
						? patient.getTelephoneList().get(0).getTelephoneNumber()
						: "");
				telephoneNumberText.setEditable(true);
				telephonCountryCodeText.setText(patient.getTelephoneList().get(0).getTelephonCountryCode() != null
						? patient.getTelephoneList().get(0).getTelephonCountryCode()
						: "");
				telephonCountryCodeText.setEditable(true);
				telephoneType2Text.setText(patient.getTelephoneList().get(1).getTelephoneType() != null
						? patient.getTelephoneList().get(1).getTelephoneType()
						: "");
				telephoneType2Text.setEditable(true);
				telephoneNumber2Text.setText(patient.getTelephoneList().get(1).getTelephoneNumber() != null
						? patient.getTelephoneList().get(1).getTelephoneNumber()
						: "");
				telephoneNumber2Text.setEditable(true);
				telephonCountryCode2Text.setText(patient.getTelephoneList().get(1).getTelephonCountryCode() != null
						? patient.getTelephoneList().get(1).getTelephoneNumber()
						: "");
				telephonCountryCode2Text.setEditable(true);
				save.setEnabled(true);
			} else {

				System.out.println("Pass some validation message");
			}

			// create.setEnabled(false);
		} catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
		}

	}

}
