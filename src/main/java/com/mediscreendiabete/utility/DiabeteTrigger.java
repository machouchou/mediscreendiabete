package com.mediscreendiabete.utility;

public enum DiabeteTrigger {
	
	/** Hemoglobine A1C. */
    HEMOGLOBINE_A1C("Hemoglobine A1C"),
    
    /** Microalbumine. */
    MICROALBUMINE("Microalbumine"),
    
    /** Taille. */
    TAILLE("Taille"),
    
    /** Poids. */
    POIDS("Poids"),
    
    /** Fumeur. */
    FUMEUR("Fumeur"),
    
    /** Anormal. */
    ANORMAL("Anormal"),
    
    /** Cholesterol. */
    CHOLESTEROL("Cholesterol"),
    
    /** Vertige. */
    VERTIGE("Vertige"),
    
    /** Rechute. */
    RECHUTE("Rechute"),
    
    /** Reaction. */
    REACTION("Reaction"),
    
    /** Anticorps. */
    ANTICORPS("Anticorps");
	
	private String trigger;

	DiabeteTrigger(String trigger) {
		this.trigger = trigger;	}

	public String getTrigger() {
		return trigger;
	}

	public void setTrigger(String trigger) {
		this.trigger = trigger;
	}


}
