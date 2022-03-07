package com.example.namequizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.namequizapp.database.Person;
import com.example.namequizapp.viewmodels.QuizActivityViewModel;

import java.util.List;

public class QuizActivity extends AppCompatActivity {

    private TextView score;
    private Button option1, option2, option3;
    private ImageView image;

    private QuizActivityViewModel viewModel;

    private boolean readyForNext = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        viewModel = new ViewModelProvider(this).get(QuizActivityViewModel.class);

        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);

        score = findViewById(R.id.score);
        image = findViewById(R.id.quizpicture);

        play();
    }

    public void play() {
        if (viewModel.isInitialized()) {
            Log.i("QuizActivityViewModel", "Question already initialized, and has not been answered");
        } else {
            viewModel.initQuestion();
        }
        score.setText(viewModel.getCurrentScore());
        image.setImageURI(Uri.parse(viewModel.getCurrentPerson().getPicture()));
        populateButtons();
    }

    public void highlightCorrectAnswer() {
        int correct = viewModel.getCorrectAnswer();

        switch (correct) {
            case 0:
                option1.setBackgroundColor(Color.parseColor("#50C878"));
                break;
            case 1:
                option2.setBackgroundColor(Color.parseColor("#50C878"));
                break;
            case 2:
                option3.setBackgroundColor(Color.parseColor("#50C878"));
                break;
        }
        readyForNext = true;
    }

    private void populateButtons() {
        long time = System.currentTimeMillis() + 200;
        List<Person> alternatives = viewModel.getAlternatives();
        option1.setText(alternatives.get(0).getName());
        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (time > System.currentTimeMillis()) {
                    Log.i("QuizActivity", "Key pressed too early");
                    return;
                }
                if (!viewModel.submitAnswer(0)) {
                    highlightCorrectAnswer();
                } else {
                    play();
                }
            }
        });
        option2.setText(alternatives.get(1).getName());
        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (time > System.currentTimeMillis()) {
                    Log.i("QuizActivity", "Key pressed too early");
                    return;
                }
                if (!viewModel.submitAnswer(1)) {
                    highlightCorrectAnswer();
                } else {
                    play();
                }
            }
        });
        option3.setText(alternatives.get(2).getName());
        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (time > System.currentTimeMillis()) {
                    Log.i("QuizActivity", "Key pressed too early");
                    return;
                }
                if (!viewModel.submitAnswer(2)) {
                    highlightCorrectAnswer();
                } else {
                    play();
                }
            }
        });
    }

    @Override
    public void onUserInteraction() {
        if (readyForNext) {
            option1.setBackgroundColor(Color.parseColor("#6200ee"));
            option2.setBackgroundColor(Color.parseColor("#6200ee"));
            option3.setBackgroundColor(Color.parseColor("#6200ee"));
            viewModel.initQuestion();
            readyForNext = false;
            play();
        }
    }
}