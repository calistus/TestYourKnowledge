package com.grandilo.qa;

import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.grandilo.qa.db.DBAdapter;

public class TakeATest extends AppCompatActivity {

    DBAdapter db;
    private int QUESTION_COUNTER = 1;
    RadioGroup radioGroup;
    RadioButton ans1Button;
    RadioButton ans2Button;
    RadioButton ans3Button;
    RadioButton ans4Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_atest);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        db = new DBAdapter(this);
        final TextView counterTextView = (TextView) findViewById(R.id.counter);

        Button nextButton = (Button) findViewById(R.id.next_button);
        Button previousButton = (Button) findViewById(R.id.previous_button);
        radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        ans1Button = (RadioButton) findViewById(R.id.ans1);
        ans2Button = (RadioButton) findViewById(R.id.ans2);
        ans3Button = (RadioButton) findViewById(R.id.ans3);
        ans4Button = (RadioButton) findViewById(R.id.ans4);
        final TextView questionTextView = (TextView) findViewById(R.id.question_text_view);
        questionTextView.setText(getQuestion(QUESTION_COUNTER));
        populateRadioButtons();

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(QUESTION_COUNTER < 4){
                    QUESTION_COUNTER++;
                    counterTextView.setText(QUESTION_COUNTER +"/4");
                    questionTextView.setText(getQuestion(QUESTION_COUNTER));
                    populateRadioButtons();
                    int selectedId = radioGroup.getCheckedRadioButtonId();
                    Toast.makeText(TakeATest.this, "You selected "+radioGroup.getCheckedRadioButtonId(), Toast.LENGTH_SHORT).show();


                }

            }
        });
        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(QUESTION_COUNTER > 1){
                    --QUESTION_COUNTER;
                    questionTextView.setText(getQuestion(QUESTION_COUNTER));
                    counterTextView.setText(QUESTION_COUNTER + "/4");
                    populateRadioButtons();
                }
            }
        });

    }

    public String getQuestion(int QUESTION_COUNTER){
        db.open();
        if((QUESTION_COUNTER == 5)||(QUESTION_COUNTER < 1))
        {
            QUESTION_COUNTER = 1;
        }
        Cursor c = db.getQuestions(QUESTION_COUNTER);
        db.close();
        return c.getString(1);
    }

    public String getAnswer(int counter){
        db.open();
        if((QUESTION_COUNTER == 5)||(QUESTION_COUNTER < 1))
        {
            QUESTION_COUNTER = 1;
        }
        Cursor c = db.getAnswers(QUESTION_COUNTER);
        db.close();
        return c.getString(counter);

    }
    public void populateRadioButtons(){
        /*
        for(int columnIndex = 1; columnIndex<=4; columnIndex++ ){
        }*/
            ans1Button.setText(getAnswer(1));
            ans2Button.setText(getAnswer(2));
            ans3Button.setText(getAnswer(3));
            ans4Button.setText(getAnswer(4));
    }

    public void endOfQuestion(){
        Snackbar.make(null, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
}


