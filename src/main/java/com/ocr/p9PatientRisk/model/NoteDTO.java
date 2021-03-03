package com.ocr.p9PatientRisk.model;

import java.time.LocalDateTime;

public class NoteDTO {

    private String noteId;
    private Integer patientId;
    private String title;
    private String note;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    public NoteDTO() {

    }

    public String getNoteId() {
        return noteId;
    }

    public void setNoteId(String noteId) {
        this.noteId = noteId;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        return "NoteDTO{" +
                "noteId='" + noteId + '\'' +
                ", patientId=" + patientId +
                ", title='" + title + '\'' +
                ", note='" + note + '\'' +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                '}';
    }
}