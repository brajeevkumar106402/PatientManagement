package com.patient.rest;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.patient.model.Patient;

public class SWTRestClient {
	public static void main(String[] args) throws IOException, InterruptedException {
		Patient pateint = fetchPatientListById(new Long(1).toString());
	}

	public static Patient fetchPatientListById(String ID) throws IOException, InterruptedException {
		String serviceUrl = "http://localhost:8081/api/patient/id/" + new Long(ID);
		var request = HttpRequest.newBuilder().uri(URI.create(serviceUrl)).header("Content-Type", "application-json")
				.GET().build();
		var client = HttpClient.newHttpClient();
		var response = client.send(request, HttpResponse.BodyHandlers.ofString());
		ObjectMapper om = new ObjectMapper();
		// System.out.println(response.body());
		Patient p1 = om.readValue(response.body(), Patient.class);
		System.out.println(p1);
		return p1;
	}

	public static Patient fetchPatientListByName(String name) throws IOException, InterruptedException {
		String serviceUrl = "http://localhost:8081/api/patient/name/" + name;
		var request = HttpRequest.newBuilder().uri(URI.create(serviceUrl)).header("Content-Type", "application-json")
				.GET().build();
		var client = HttpClient.newHttpClient();
		var response = client.send(request, HttpResponse.BodyHandlers.ofString());
		ObjectMapper om = new ObjectMapper();
		// System.out.println(response.body());
		Patient p1 = om.readValue(response.body(), Patient.class);
		System.out.println(p1);
		return p1;
	}

	public static List<Patient> findAllPatient() throws IOException, InterruptedException {
		String serviceUrl = "http://localhost:8081/api/patient/";

		var request = HttpRequest.newBuilder().uri(URI.create(serviceUrl)).header("Content-Type", "application-json")
				.GET().build();
		var client = HttpClient.newHttpClient();
		var response = client.send(request, HttpResponse.BodyHandlers.ofString());
		ObjectMapper om = new ObjectMapper();
		return om.readValue(response.body(), new TypeReference<List<Patient>>() {
		});
	}

	public static HttpResponse<String> deletePatient(String patientId) throws IOException, InterruptedException {
		String serviceUrl = "http://localhost:8081/api/patient/id/" + patientId;
		var request = HttpRequest.newBuilder().uri(URI.create(serviceUrl)).header("Content-Type", "application-json")
				.DELETE().build();
		var client = HttpClient.newHttpClient();
		var response = client.send(request, HttpResponse.BodyHandlers.ofString());
		return response;
	}

	public static HttpResponse<String> savePatient(Patient patient) throws IOException, InterruptedException {
		String url = "http://localhost:8081/api/patient/" + patient;
		ObjectMapper om = new ObjectMapper();
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).header("Content-Type", "application/json")
				.POST(HttpRequest.BodyPublishers.ofString(om.writeValueAsString(patient), StandardCharsets.UTF_8))
				.build();

		var response = client.send(request, HttpResponse.BodyHandlers.ofString());

		return response;
	}

	public static HttpResponse<String> updatePatient(Patient patient) throws IOException, InterruptedException {
		String url = "http://localhost:8081/api/patient/" + patient;
		ObjectMapper om = new ObjectMapper();

		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).header("Content-Type", "application/json")
				.POST(HttpRequest.BodyPublishers.ofString(om.writeValueAsString(patient), StandardCharsets.UTF_8))
				.build();

		var response = client.send(request, HttpResponse.BodyHandlers.ofString());
		System.out.println(response.statusCode());
		System.out.println(response.body());
		return response;
	}
}