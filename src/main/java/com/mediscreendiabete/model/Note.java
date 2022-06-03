package com.mediscreendiabete.model;

public class Note {
	
	/** The id. */
    private String id;

    /** The patient id. */
    private Long patientId;

    /** The note. */
    private String note;

	public Note() {
	}

	public Note(String id, Long patientId, String note) {
		super();
		this.id = id;
		this.patientId = patientId;
		this.note = note;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}


	
}
