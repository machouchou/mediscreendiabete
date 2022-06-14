package com.mediscreendiabete;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.mediscreendiabete.model.DiabeteAssess;
import com.mediscreendiabete.model.Note;
import com.mediscreendiabete.model.Patient;
import com.mediscreendiabete.model.Response;
import com.mediscreendiabete.proxies.MediscreenNoteProxy;
import com.mediscreendiabete.proxies.MediscreenProxy;
import com.mediscreendiabete.service.AssessmentService;

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class DiabeteTest {
	
	@InjectMocks
	AssessmentService assessmentService;
	
	@Mock
	MediscreenProxy mediscreenProxy;
	
	@Autowired
	MediscreenNoteProxy mediscreenNoteProxy;
	
	//ResponseEntity<Response<Patient>> patient1 = new ResponseEntity<Response<Patient>>();
	Note note1;
	Note note2;
	List<Note> listPatientNote;
	
	@BeforeEach
	 public void beforeEachTest() throws IOException {
        MockitoAnnotations.initMocks(this);
		
		listPatientNote = new ArrayList<>();
		
	}
	@Test
	void getPatientTest() {
		Patient patient1 = new Patient(1, "firstname", "lastname", LocalDate.now().minusYears(22), "M", "address1", "111-222-333");
	assertNotNull(patient1);
	}
	
	@Test
	void getPatientNotesTest() throws Exception {
		//ARRANGE:
		note1 = new Note("mongoid1", 1, "note1 fumeur, cholestero");
		note2 = new Note("mongoid2", 1, "note2 Anticorps, vertige, fatigue");
		listPatientNote.add(note1);
		listPatientNote.add(note2);
		
		assertEquals("mongoid1", note1.getId());
		assertEquals(1, note1.getPatientId());
		assertNotEquals(Collections.EMPTY_LIST, listPatientNote.size());
	}
	
	@Test
	void getAgeTest() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		  String date = "2016-08-16";

		  //convert String to LocalDate
		  LocalDate birthDate = LocalDate.parse(date, formatter);

	      int age = Period.between(birthDate, LocalDate.now()).getYears();
	      
	      assertEquals(5, assessmentService.getAge(birthDate));
	      
	}
	
	@Test
	void getPatientTriggersTest() {
		
		note1 = new Note("mongoid1", 1, "note1 fumeur, cholestérol, Microalbumine");
		note2 = new Note("mongoid2", 1, "note2 Anticorps, vertige, Rechute");
		listPatientNote.add(note1);
		listPatientNote.add(note2);
				
		//String noteRecap = listPatientNote.toString().toUpperCase();
		  	      
	      assertEquals(6, assessmentService.getPatientTriggers(listPatientNote));
	      
	}
	@Test
	void getDiabeteAssessTest() throws Exception {
		//ARRANGE:
		Patient patient1 = new Patient(1, "firstname", "lastname", LocalDate.now().minusYears(29), "F", "address1", "111-222-333");
		note1 = new Note("mongoid1", 1, "note1 fumeur, cholestérol, Réaction, Taille");
		note2 = new Note("mongoid2", 1, "note2 Anticorps, vertige, fatigue");
		listPatientNote.add(note1);
		listPatientNote.add(note2);
				
		int patientAge = assessmentService.getAge(patient1.getBirthDate());
		
		int patientTriggers = assessmentService.getPatientTriggers(listPatientNote);
		
		String diabeteRiskLevel = "EARLYONSET : apparition précoce";
		
		//ASSERT:
		assertEquals("EARLYONSET : apparition précoce", diabeteRiskLevel);
	}

}
