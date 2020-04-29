package com.quaz.todosuru;

import android.app.Application;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.AndroidViewModel;

import java.util.List;

import androidx.annotation.NonNull;

public class NoteViewModel extends AndroidViewModel {

    private NoteRepo repository;
    private LiveData<List<Note>> allNotes;

    public NoteViewModel(@NonNull Application application) {
        super(application);
        repository = new NoteRepo(application);
        allNotes = repository.getAllNotes();
    }

    public void insert(Note note){
        repository.insert(note);
    }

    public void update(Note note){
        repository.update(note);
    }

    public void delete(Note note){
        repository.delete(note);
    }

    public void deleteAllNotes(){
        repository.deleteAllNotes();
    }

    public LiveData<List<Note>> getAllNotes(){
        return allNotes;
    }
}
