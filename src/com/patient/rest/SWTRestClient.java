package com.patient.rest;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.patient.model.Patient;


/**
 * 
 * SWTRestClient is a HTTP Client class which pull the rest data 
 * from Rest services and this rest data are consumed by Rich client applicaiton.
 */

public class SWTRestClient {
	private static final String GET_PATIENT_API_NAME = "http://localhost:8081/api/patient/name/";
	private static final String GET_PATIENT_API_ID = "http://localhost:8081/api/patient/id/";
	private static final String PATIENT_API_BASE_URL = "http://localhost:8081/api/patient/";

	
	/**
	 * This method is used to retrieve one patient details based on Patient Id
	 * @return patient object for the given Patient Id
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static Patient fetchPatientListById(String ID) throws IOException, InterruptedException {
		String serviceUrl = GET_PATIENT_API_ID + Long.valueOf(ID);
		Patient patient = null;
		Shell shell = new Shell();
		var request = HttpRequest.newBuilder().uri(URI.create(serviceUrl)).header("Content-Type", "application-json")
				.GET().build();
		var client = HttpClient.newHttpClient();
		try {
			var response = client.send(request, HttpResponse.BodyHandlers.ofString());
			ObjectMapper om = new ObjectMapper();
			patient = om.readValue(response.body(), Patient.class);
		} catch (IOException | InterruptedException e) {
			MessageBox messageBox = new MessageBox(shell, SWT.OK | SWT.ICON_WARNING | SWT.CANCEL);
			messageBox.setMessage("this Patient does not exist with given " + ID);
			messageBox.open();
		}

		return patient;
	}
	
	/**
	 * This method is used to retrieve one patient details based on Patient name
	 * @return patient object for the given Patient Id
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static List<Patient> fetchPatientListByName(String name) throws IOException, InterruptedException {
		String serviceUrl = GET_PATIENT_API_NAME + name;
		
		
		var request = HttpRequest.newBuilder().uri(URI.create(serviceUrl)).header("Content-Type", "application-json")
				.GET().build();
		var client = HttpClient.newHttpClient();
		var response = client.send(request, HttpResponse.BodyHandlers.ofString());
		ObjectMapper om = new ObjectMapper();
		//List<Patient> patient = om.readValue(response.body(), List<Patient>);
		return om.readValue(response.body(), new TypeReference<List<Patient>>() {
		});
	//	return patient;
	}
	
	/**
	 * This method is used to retrieve list of  patient details 
	 * @return patient list 
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static List<Patient> findAllPatient() throws IOException, InterruptedException {
		String serviceUrl = PATIENT_API_BASE_URL;

		var request = HttpRequest.newBuilder().uri(URI.create(serviceUrl)).header("Content-Type", "application-json")
				.GET().build();
		var client = HttpClient.newHttpClient();
		var response = client.send(request, HttpResponse.BodyHandlers.ofString());
		ObjectMapper om = new ObjectMapper();
		return om.readValue(response.body(), new TypeReference<List<Patient>>() {
		});
	}
	
	/**
	 * This method is used to delete patient details based on its patient id 
	 * @param patientId
	 * @return response message
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static HttpResponse<String> deletePatient(Long patientId) throws IOException, InterruptedException {
		String serviceUrl = PATIENT_API_BASE_URL + patientId;
		var request = HttpRequest.newBuilder().uri(URI.create(serviceUrl)).header("Content-Type", "application-json")
				.DELETE().build();
		var client = HttpClient.newHttpClient();
		var response = client.send(request, HttpResponse.BodyHandlers.ofString());
		return response;
	}

	
	/**
	 * This method is used to save patient details  
	 * @param Patient Object
	 * @return response message
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static HttpResponse<String> savePatient(Patient patient) throws IOException, InterruptedException {
		String url = PATIENT_API_BASE_URL;
		ObjectMapper om = new ObjectMapper();
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).header("Content-Type", "application/json")
				.POST(HttpRequest.BodyPublishers.ofString(om.writeValueAsString(patient), StandardCharsets.UTF_8))
				.build();
		var response = client.send(request, HttpResponse.BodyHandlers.ofString());
		return response;
	}

	
	/**
	 * This method is used to update patient details
	 * @param patient
	 * @return response message
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static HttpResponse<String> updatePatient(Patient patient) throws IOException, InterruptedException {
		String url = PATIENT_API_BASE_URL + patient;
		ObjectMapper om = new ObjectMapper();
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).header("Content-Type", "application/json")
				.POST(HttpRequest.BodyPublishers.ofString(om.writeValueAsString(patient), StandardCharsets.UTF_8))
				.build();

		var response = client.send(request, HttpResponse.BodyHandlers.ofString());
		return response;
	}
}