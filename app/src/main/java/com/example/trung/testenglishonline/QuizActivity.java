package com.example.trung.testenglishonline;

import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.support.annotation.IdRes;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.triggertrap.seekarc.SeekArc;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import butterknife.BindView;
import butterknife.ButterKnife;


public class QuizActivity extends AppCompatActivity {

    private ArrayList<Questions> arrayListQuestionse;
    private final  String json = "[{\"ID\":1,\"QUESTION\":\"What is this?\",\"OPTA\":\"This is this\",\"OPTB\":\"This is that\", \"OPTC\":\"Dick\",\"ANSWER\":\"\"},{\"ID\":2,\"QUESTION\":\"What is that?\",\"OPTA\":\"This is this\",\"OPTB\":\"This is that\", \"OPTC\":\"Dick\",\"ANSWER\":\"\"},{\"ID\":3,\"QUESTION\":\"What is means?\",\"OPTA\":\"This is this\",\"OPTB\":\"This is that\", \"OPTC\":\"Dick\",\"ANSWER\":\"\"}]";
    private int qid = 0;
    private Questions currentQ;
    private ArrayList<Questions> arrayListComplite;
    private final String TOTAL_NUMBER_Q = "/10";
    private CountDownTimer countDownTimer;
    private Map map;

    @BindView(R.id.btn_Back)
    ImageButton _btnBack;
    @BindView(R.id.txtvSkip)
    TextView _txtvSkip;
    @BindView(R.id.txtTittle) TextView _txtcTittle;
    @BindView(R.id.btnQuizNumber)
    AppCompatTextView _txtvQuizNumber;
    @BindView(R.id.seekArcWarning)
    SeekArc _seekArc;
    @BindView(R.id.fabWarning)
    FloatingActionButton _fabPlay;
    @BindView(R.id.txtvQuiz) TextView _txtvQView;
    @BindView(R.id.radioGroup)
    RadioGroup _radioGroup;
    @BindView(R.id.rbtnA)
    RadioButton _rbtnA;
    @BindView(R.id.rbtnB)
    RadioButton _rbtnB;
    @BindView(R.id.rbtnC)
    RadioButton _rbtnC;
    @BindView(R.id.txtvProcess) TextView _txtvProcess;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        ButterKnife.bind(this);

        arrayListQuestionse = new ArrayList<Questions>();
        //An di flaoat action button
        _fabPlay.hide();

        _btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        layArray();
//        Toast.makeText(getApplicationContext(), " " + arrayListQuestionse.size(), Toast.LENGTH_SHORT).show();
        setQuestionView();
        _radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                countDownTimer.cancel();
                final android.os.Handler handlerDelay = new android.os.Handler();
                handlerDelay.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        setQuestionView();
                    }
                }, 1000);

//                RadioButton currentRadioButton = (RadioButton) findViewById(_radioGroup.getCheckedRadioButtonId());
//                currentQ.setANSWER(currentRadioButton.getText().toString().trim());
//                arrayListComplite.add(currentQ);

                _radioGroup.setOnCheckedChangeListener(null);
                _radioGroup.clearCheck();
                _radioGroup.setOnCheckedChangeListener(this);
//                for (Questions a : arrayListQuestionse){
//                    Log.i("ss", a.toString());
//                }

            }
        });

        _txtvSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countDownTimer.cancel();
                setQuestionView();
            }
        });

    }

    private void setMap(){


    }

    private void setAnswer(long changeClick){

    }

    private void setSeekarc(){
        final int progress = 300;
        _seekArc.setProgress(progress);
        _txtvProcess.setText("29");
        countDownTimer = new CountDownTimer(30000, 200) {
            int currentTime = 29;
            @Override
            public void onTick(long millisUntilFinished) {
                _seekArc.setProgress(_seekArc.getProgress() - 2);
                if (_seekArc.getProgress() % 10 == 0){
                    currentTime -= 1;
                    _txtvProcess.setText("" + currentTime);
                }
            }

            @Override
            public void onFinish() {
                setQuestionView();
            }
        }.start();
//        _seekArc.;
    }



    private void setQuestionView()
    {
        if (qid < arrayListQuestionse.size()) {
            setSeekarc();
            _txtvQuizNumber.setText(qid + 1 + TOTAL_NUMBER_Q);
            currentQ = arrayListQuestionse.get(qid);
            _txtvQView.setText(currentQ.getQUESTION());
            _rbtnA.setText(currentQ.getOPTA());
            _rbtnB.setText(currentQ.getOPTB());
            _rbtnC.setText(currentQ.getOPTC());
            qid++;
        }
    }

    void layArray(){
        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++){
                JSONObject quiz = jsonArray.getJSONObject(i);
                arrayListQuestionse.add(new Questions(
                        quiz.getInt("ID"),quiz.getString("QUESTION"), quiz.getString("OPTA"),
                        quiz.getString("OPTB"), quiz.getString("OPTC"),
                        quiz.getString("ANSWER")
                ));

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    class ReadJson extends AsyncTask<String, Integer, String>{

        @Override
        protected String doInBackground(String... params) {

            return docNoiDung_Tu_URL(params[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            //s la Chuoi Json
        }
    }

    private String docNoiDung_Tu_URL(String theUrl){
        StringBuilder content = new StringBuilder();
        try    {
            // create a url object
            URL url = new URL(theUrl);

            // create a urlconnection object
            URLConnection urlConnection = url.openConnection();

            // wrap the urlconnection in a bufferedreader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String line;

            // read from the urlconnection via the bufferedreader
            while ((line = bufferedReader.readLine()) != null){
                content.append(line + "\n");
            }
            bufferedReader.close();
        }
        catch(Exception e)    {
            e.printStackTrace();
        }
        return content.toString();
    }

}
