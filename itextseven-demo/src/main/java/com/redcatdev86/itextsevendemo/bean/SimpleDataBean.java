package com.redcatdev86.itextsevendemo.bean;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SimpleDataBean implements Serializable {

    @Serial
    private static final long serialVersionUID = 4121557130329533463L;

    private List<String> notes = new ArrayList<>();

    public List<String> getNotes() {
        return notes;
    }

    public void setNotes(List<String> notes) {
        this.notes = notes;
    }

    public void addNote(String note) {
        notes.add(note);
    }
}
