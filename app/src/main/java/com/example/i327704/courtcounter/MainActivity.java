package com.example.i327704.courtcounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    boolean inning1 = true;
    int scoreA = 0, scoreB = 0, countA = 0, countB = 0;
    String star = "*";
    Button sixRunsA, fourRunsA, threeRunsA, twoRunsA, oneRunA, zeroRunsA, noBallA, wideBallA, outA, sixRunsB, fourRunsB, threeRunsB, twoRunsB, oneRunB, zeroRunsB, noBallB, wideBallB, outB;
    TextView oversOfA, oversOfB, runsA, runsB, matchStatus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sixRunsA = findViewById(R.id.A6);
        fourRunsA = findViewById(R.id.A4);
        threeRunsA = findViewById(R.id.A3);
        twoRunsA = findViewById(R.id.A2);
        oneRunA = findViewById(R.id.A1);
        zeroRunsA = findViewById(R.id.A0);
        noBallA = findViewById(R.id.Anb);
        wideBallA = findViewById(R.id.Awd);
        outA = findViewById(R.id.OutA);
        runsA = findViewById(R.id.ScoreA);
        sixRunsB = findViewById(R.id.B6);
        fourRunsB = findViewById(R.id.B4);
        threeRunsB = findViewById(R.id.B3);
        twoRunsB = findViewById(R.id.B2);
        oneRunB = findViewById(R.id.B1);
        zeroRunsB = findViewById(R.id.B0);
        noBallB = findViewById(R.id.Bnb);
        wideBallB = findViewById(R.id.Bwd);
        outB = findViewById(R.id.OutB);
        runsB = findViewById(R.id.ScoreB);
        matchStatus = findViewById(R.id.status);
        oversOfA = findViewById(R.id.OversA);
        oversOfB = findViewById(R.id.OversB);
        setMatchStatus();
    }

    public void incSix(View view) {
        if (inning1) {
            scoreA += 6;
            overs(++countA);
        } else {
            scoreB += 6;
            overs(++countB);
        }
    }

    public void incFour(View view) {
        if (inning1) {
            scoreA += 4;
            overs(++countA);
        } else {
            scoreB += 4;
            overs(++countB);
        }
    }

    public void incThree(View view) {
        if (inning1) {
            scoreA += 3;
            overs(++countA);
        } else {
            scoreB += 3;
            overs(++countB);
        }
    }

    public void incTwo(View view) {
        if (inning1) {
            scoreA += 2;
            overs(++countA);
        } else {
            scoreB += 2;
            overs(++countB);
        }
    }

    public void incOne(View view) {
        if (inning1) {
            scoreA += 1;
            overs(++countA);
        } else {
            scoreB += 1;
            overs(++countB);
        }
    }

    public void extras(View view) {
        if (inning1) {
            scoreA += 1;
            overs(countA);
        } else {
            scoreB += 1;
            overs(countB);
        }
    }

    public void incZero(View view) {
        if (inning1) {
            scoreA += 0;
            overs(++countA);
        } else {
            scoreB += 0;
            overs(++countB);
        }
    }

    public void overs(int counter) {
        if (inning1) {
            if (counter == 0) {
                oversOfA.setText("Overs: 0.0");
                displayScore(scoreA);
            }
            if (counter <= 30) {
                displayScore(scoreA);
                int balls = counter % 6;
                int overNum = counter / 6;
                oversOfA.setText("Overs: " + String.valueOf(overNum) + "." + String.valueOf(balls));
            }
            if (counter == 30) {
                disableA();
            }
        } else {
            if (counter == 0) {
                oversOfA.setText("Overs: 0.0");
                displayScore(scoreB);
            }
            if (counter <= 30) {
                displayScore(scoreB);
                int balls = counter % 6;
                int overNum = counter / 6;
                oversOfB.setText("Overs: " + String.valueOf(overNum) + "." + String.valueOf(balls));
            }
            if (counter == 30) {
                disableB();
            }
        }
    }

    public void Out(View view) {
        star = "";
        if (inning1) {
            overs(++countA);
            displayScore(scoreA);
            disableA();
        } else {
            overs(++countB);
            displayScore(scoreB);
            disableB();
            if (scoreB < scoreA) {
                String win = "Team A won by " + String.valueOf(scoreA - scoreB);
                matchStatus.setText(win);
                Toast t = Toast.makeText(this, win, Toast.LENGTH_SHORT);
                t.show();
            }else if (scoreB == scoreA) {
                matchStatus.setText("Its a TIE");
//                Toast t = Toast.makeText(this, "Its a TIE", Toast.LENGTH_SHORT);
//                t.show();
            }
        }
    }


    public void displayScore(int runs) {
        if (inning1)
            runsA.setText("Runs: " + String.valueOf(runs) + star);
        else
            runsB.setText("Runs: " + String.valueOf(runs) + star);
        if (scoreB > scoreA) {
            matchStatus.setText("Team B won by 1 wicket");
            disableB();
        } else if (scoreB == scoreA) {
            matchStatus.setText("Its a TIE");
//            Toast t = Toast.makeText(this, "Its a TIE", Toast.LENGTH_SHORT);
//            t.show();
        }
    }

    public void disableA() {
        Toast t = Toast.makeText(this, "Team B won by 1 wicket", Toast.LENGTH_SHORT);
        t.show();
        inning1 = false;
        setMatchStatus();
        outA.setEnabled(false);
        wideBallA.setEnabled(false);
        noBallA.setEnabled(false);
        sixRunsA.setEnabled(false);
        fourRunsA.setEnabled(false);
        threeRunsA.setEnabled(false);
        twoRunsA.setEnabled(false);
        oneRunA.setEnabled(false);
        zeroRunsA.setEnabled(false);
        enableB();
    }

    public void disableB() {
        outB.setEnabled(false);
        wideBallB.setEnabled(false);
        noBallB.setEnabled(false);
        sixRunsB.setEnabled(false);
        fourRunsB.setEnabled(false);
        threeRunsB.setEnabled(false);
        twoRunsB.setEnabled(false);
        oneRunB.setEnabled(false);
        zeroRunsB.setEnabled(false);
    }

    public void enableB() {
        inning1 = false;
        setMatchStatus();
        star = "*";
        outB.setEnabled(true);
        wideBallB.setEnabled(true);
        noBallB.setEnabled(true);
        sixRunsB.setEnabled(true);
        fourRunsB.setEnabled(true);
        threeRunsB.setEnabled(true);
        twoRunsB.setEnabled(true);
        oneRunB.setEnabled(true);
        zeroRunsB.setEnabled(true);
    }

    public void enableA() {
        inning1 = true;
        star = "*";
        setMatchStatus();
        outA.setEnabled(true);
        wideBallA.setEnabled(true);
        noBallA.setEnabled(true);
        sixRunsA.setEnabled(true);
        fourRunsA.setEnabled(true);
        threeRunsA.setEnabled(true);
        twoRunsA.setEnabled(true);
        oneRunA.setEnabled(true);
        zeroRunsA.setEnabled(true);
    }

    public void newGame(View view) {
        countA = 0;
        scoreA = 0;
        countB = 0;
        scoreB = 0;
        enableA();
        disableB();
        runsA.setText("Runs");
        oversOfA.setText("Overs");
        runsB.setText("Runs");
        oversOfB.setText("Overs");

    }

    public void setMatchStatus() {
        if (inning1) {
            matchStatus.setText("Ist Innings in progress");
        } else {
            matchStatus.setText("Target is " + String.valueOf(scoreA + 1));
        }
    }

}
