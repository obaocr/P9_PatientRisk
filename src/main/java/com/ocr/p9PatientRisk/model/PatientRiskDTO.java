package com.ocr.p9PatientRisk.model;

/**
 * Model for Patient Risk
 */
public class PatientRiskDTO {
    private Integer patientId;
    private String patientInfo;
    private String risk;
    private Boolean isCalculated;
    private Integer nbrisk;

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public String getPatientInfo() {
        return patientInfo;
    }

    public void setPatientInfo(String patientInfo) {
        this.patientInfo = patientInfo;
    }

    public String getRisk() {
        return risk;
    }

    public void setRisk(String risk) {
        this.risk = risk;
    }

    public Boolean getCalculated() {
        return isCalculated;
    }

    public void setCalculated(Boolean calculated) {
        isCalculated = calculated;
    }

    public Integer getNbrisk() {
        return nbrisk;
    }

    public void setNbrisk(Integer nbrisk) {
        this.nbrisk = nbrisk;
    }

}
