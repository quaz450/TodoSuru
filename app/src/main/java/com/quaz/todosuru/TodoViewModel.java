package com.quaz.todosuru;

import android.app.Application;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.AndroidViewModel;

import java.util.List;

import androidx.annotation.NonNull;

public class TodoViewModel extends AndroidViewModel {

    private TodoRepo repository;
    private LiveData<List<Todo>> allTodos;

    public TodoViewModel(@NonNull Application application) {
        super(application);
        repository = new TodoRepo(application);
        allTodos = repository.getAllTodos();
    }

    public void insert(Todo todo){
        repository.insert(todo);
    }

    public void update(Todo todo){
        repository.update(todo);
    }

    public void delete(Todo todo){
        repository.delete(todo);
    }

    public void deleteAllTodos(){
        repository.deleteAllTodos();
    }

    public LiveData<List<Todo>> getAllTodos(){
        return allTodos;
    }
}
