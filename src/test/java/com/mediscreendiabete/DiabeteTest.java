package com.mediscreendiabete;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
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
	
	@Mock
	MediscreenNoteProxy mediscreenNoteProxy;
	
	//ResponseEntity<Response<Patient>> patient1 = new ResponseEntity<Response<Patient>>();
	Note note1;
	Note note2;
	List<Note> listPatientNote;
	
	@BeforeEach
	void initialize() {
		
		listPatientNote = new ArrayList<>();
		
	}
	
	@Test
	void test_diabeteAssess_MaleInf30Years5Triggers() {
		//ARRANGE:
		//patient1 = new Patient(1, "firstname", "lastname", LocalDate.now().minusYears(22), "M", "address1", "111-222-333");
		note1 = new Note("mongoid1", 1, "note1 fumeur, cholestero");
		note2 = new Note("mongoid2", 1, "note2 Anticorps vertige");
		listPatientNote.add(note1);
		listPatientNote.add(note2);
		//when(mediscreenProxy.getPatientById(1)).thenReturn(patient1);
		when(mediscreenNoteProxy.getAllPatientNote(1)).thenReturn(listPatientNote);		

		//ACT:
		DiabeteAssess result = assessmentService.getdiabeteAssess(1);

		//ASSERT:
		assertEquals("Patient: firstname lastname (age 22) diabetes assessment is: Early onset", result);
	}

}
