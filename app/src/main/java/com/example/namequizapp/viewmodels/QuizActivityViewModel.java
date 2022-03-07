package com.example.namequizapp.viewmodels;

import android.app.Application;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.lifecycle.AndroidViewModel;

import com.example.namequizapp.database.Person;
import com.example.namequizapp.database.PersonDatabase;
import com.example.namequizapp.database.PersonRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class QuizActivityViewModel extends AndroidViewModel {

    private PersonDatabase db;

    public QuizActivityViewModel(Application application) {
        super(application);
        db = PersonDatabase.getDbInstance(application);
    }

    private int currentScore = 0;
    private int totalAttempts = 0;

    private Random rnd = new Random();

    private final int TOTAL_ANSWERS = 3;

    private List<Person> alternatives;
    private Person currentPerson;

    private boolean answered = false;

    public String getCurrentScore() {

        return currentScore + " / " + totalAttempts;
    }

    public void incrementScore() {
        currentScore++;
    }

    public void resetScore() {
        currentScore = 0;
    }

    public void incrementAttempts() {
        totalAttempts++;
    }

    public int getTotalAttempts() {
        return totalAttempts;
    }

    public void initQuestion() {
        if (currentPerson == null)
            answered = true;
        if (answered == false) {
            Log.i("QuizActivityViewModel", "Question is attempted initialized, but question has not been answered");
            return;
        }
        List<Person> allPeople = db.personDao().getAllPeople();
        if(allPeople.size() < 3)
            Log.e("QuizActivityViewModel","Not enough people in database. Need at least 3 people to run quiz");
        else {
            alternatives = new ArrayList<Person>();
            currentPerson = (allPeople.get(rnd.nextInt(allPeople.size())));
            alternatives.add(currentPerson);

            Person p;
            for (int i = 0; i < TOTAL_ANSWERS - 1; i++) {
                p = allPeople.get(rnd.nextInt(allPeople.size()));
                if (alternatives.contains(p))
                    i--;
                else {
                    alternatives.add(p);
                }
            }
            Collections.shuffle(alternatives);
            answered = false;
        }
    }

    public List<Person> getAlternatives() {
        return alternatives;
    }

    public void setAlternatives(List<Person> alternatives) {
        this.alternatives = alternatives;
    }

    public Person getCurrentPerson() {
        return currentPerson;
    }

    public boolean submitAnswer(int alternative) {
        if (alternatives.get(alternative).getName().equals(currentPerson.getName())) {
            incrementScore();
            incrementAttempts();
            answered = true;
            return true;
        } else {
            incrementAttempts();
            answered = true;
            return false;
        }
    }

    public boolean isInitialized() {
        return (currentPerson != null && !answered);
    }

    public int getCorrectAnswer() {
        int i = 0;
        while (i < alternatives.size()) {
            if (currentPerson.getName().equals(alternatives.get(i).getName())) {
                return i;
            } else {
                i++;
            }
        }
        return i;
    }

}
