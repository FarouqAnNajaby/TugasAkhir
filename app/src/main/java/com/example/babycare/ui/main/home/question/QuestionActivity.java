package com.example.babycare.ui.main.home.question;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.babycare.R;
import com.example.babycare.api.ApiClient;
import com.example.babycare.databinding.ActivityQuestionBinding;
import com.example.babycare.helper.DbHelper;
import com.example.babycare.model.AnswerQuestion;
import com.example.babycare.model.Question;
import com.example.babycare.model.UserResponse;
import com.example.babycare.ui.main.home.adapter.QuestionAdapter;
import com.example.babycare.ui.main.home.contract.HomeContract;
import com.example.babycare.ui.main.home.hasil.HasilActvity;
import com.example.babycare.utils.Constanta;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionActivity extends AppCompatActivity implements HomeContract {

    public static final String ID_BABY = "id";
    static ActivityQuestionBinding binding;
    private ImageView Image;
    static QuestionAdapter adapter;
    static DbHelper dbHelper;
    Intent intent;
    static List<String> trueTemp = new ArrayList<>();
    AnswerQuestion answerQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQuestionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getResources().getString(R.string.question));

        intent = getIntent();
        dbHelper = new DbHelper(this);
        trueTemp = new ArrayList<>();

        dbHelper.put(Constanta.PREF_ID_BABY,intent.getStringExtra(ID_BABY));
        Log.i("cekIdBaby", "onCreate: "+dbHelper.getString(Constanta.PREF_ID_BABY));
        Log.i("cekIdBaby", "onCreate: "+dbHelper.getString(Constanta.PREF_ID_BABY));
        setOnBoardingItem();
        binding.vpQuestion.setAdapter(adapter);
        binding.vpQuestion.setUserInputEnabled(false);
        setOnBoardingIndicator();
        setCurrentOnboardingIndicators(0);

        binding.vpQuestion.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentOnboardingIndicators(position);
            }
        });

        binding.btnOnBoarding.setOnClickListener(v->{
            intent = new Intent(this,HasilActvity.class);
            intent.putExtra(HasilActvity.LABEL,"question");
            startActivity(intent);
            finish();
        });
    }

    private void setCurrentOnboardingIndicators(int index) {
        int childCount = binding.containerIndicator.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ImageView imageView = (ImageView) binding.containerIndicator.getChildAt(i);
            if (i == index) {
                imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),
                        R.drawable.onboarding_indicator_active));
            } else {
                imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),
                        R.drawable.onboarding_indicator_inactive));
            }
        }
        binding.btnOnBoarding.setText("Cek Diagnosa");
    }

    @Override
    protected void onResume() {
        super.onResume();
        trueTemp = new ArrayList<>();
    }

    private void setOnBoardingIndicator() {
        ImageView[] indicators = new ImageView[adapter.getItemCount()];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(8, 0, 8, 0);
        for (int i = 0; i < indicators.length; i++) {
            indicators[i] = new ImageView(getApplicationContext());
            indicators[i].setImageDrawable(ContextCompat.getDrawable(
                    getApplicationContext(), R.drawable.onboarding_indicator_inactive
            ));
            indicators[i].setLayoutParams(layoutParams);
            binding.containerIndicator.addView(indicators[i]);
        }
    }

    private void setOnBoardingItem() {
        List<Question> onBoardingItems = new ArrayList<>();

        Question boarding = new Question();
        boarding.setNo("Pertanyaan 1");
        boarding.setId("G001");
        boarding.setTitle(getResources().getString(R.string.question1));
        boarding.setScreenImg(R.drawable.womanwithbaby);

        Question boarding1 = new Question();
        boarding1.setNo("Pertanyaan 2");
        boarding1.setId("G002");
        boarding1.setTitle(getResources().getString(R.string.question2));
        boarding1.setScreenImg(R.drawable.icon_diagnosa);

        Question boarding2 = new Question();
        boarding2.setNo("Pertanyaan 3");
        boarding2.setId("G003");
        boarding2.setTitle(getResources().getString(R.string.question3));
        boarding2.setScreenImg(R.drawable.womanwithbaby);

        Question boarding3 = new Question();
        boarding3.setNo("Pertanyaan 4");
        boarding3.setId("G004");
        boarding3.setTitle(getResources().getString(R.string.question4));
        boarding3.setScreenImg(R.drawable.icon_diagnosa);

        Question boarding4 = new Question();
        boarding4.setNo("Pertanyaan 5");
        boarding4.setId("G005");
        boarding4.setTitle(getResources().getString(R.string.question5));
        boarding4.setScreenImg(R.drawable.womanwithbaby);

        Question boarding5 = new Question();
        boarding5.setNo("Pertanyaan 6");
        boarding5.setId("G006");
        boarding5.setTitle(getResources().getString(R.string.question6));
        boarding5.setScreenImg(R.drawable.icon_diagnosa);

//        Question boarding6 = new Question();
//        boarding6.setNo("Pertanyaan 6");
//        boarding6.setId("G007");
//        boarding6.setTitle(getResources().getString(R.string.question7));
//        boarding6.setScreenImg(R.drawable.womanwithbaby);

        Question boarding7 = new Question();
        boarding7.setNo("Pertanyaan 7");
        boarding7.setId("G008");
        boarding7.setTitle(getResources().getString(R.string.question8));
        boarding7.setScreenImg(R.drawable.icon_diagnosa);

        Question boarding8 = new Question();
        boarding8.setNo("Pertanyaan 8");
        boarding8.setId("G009");
        boarding8.setTitle(getResources().getString(R.string.question9));
        boarding8.setScreenImg(R.drawable.womanwithbaby);

        Question boarding9 = new Question();
        boarding9.setNo("Pertanyaan 9");
        boarding9.setId("G010");
        boarding9.setTitle(getResources().getString(R.string.question10));
        boarding9.setScreenImg(R.drawable.icon_diagnosa);

        Question boarding10 = new Question();
        boarding10.setNo("Pertanyaan 10");
        boarding10.setId("G011");
        boarding10.setTitle(getResources().getString(R.string.question11));
        boarding10.setScreenImg(R.drawable.womanwithbaby);

        Question boarding11 = new Question();
        boarding11.setNo("Pertanyaan 11");
        boarding11.setId("G012");
        boarding11.setTitle(getResources().getString(R.string.question12));
        boarding11.setScreenImg(R.drawable.icon_diagnosa);

        Question boarding12 = new Question();
        boarding12.setNo("Pertanyaan 12");
        boarding12.setId("G013");
        boarding12.setTitle(getResources().getString(R.string.question13));
        boarding12.setScreenImg(R.drawable.womanwithbaby);

        Question boarding13 = new Question();
        boarding13.setNo("Pertanyaan 13");
        boarding13.setId("G014");
        boarding13.setTitle(getResources().getString(R.string.question14));
        boarding13.setScreenImg(R.drawable.icon_diagnosa);

        Question boarding14 = new Question();
        boarding14.setNo("Pertanyaan 14");
        boarding14.setId("G015");
        boarding14.setTitle(getResources().getString(R.string.question15));
        boarding14.setScreenImg(R.drawable.womanwithbaby);

        Question boarding15 = new Question();
        boarding15.setNo("Pertanyaan 15");
        boarding15.setId("G016");
        boarding15.setTitle(getResources().getString(R.string.question16));
        boarding15.setScreenImg(R.drawable.icon_diagnosa);

        onBoardingItems.add(boarding);
        onBoardingItems.add(boarding1);
        onBoardingItems.add(boarding2);
        onBoardingItems.add(boarding3);
        onBoardingItems.add(boarding4);
        onBoardingItems.add(boarding5);
//        onBoardingItems.add(boarding6);
        onBoardingItems.add(boarding7);
        onBoardingItems.add(boarding8);
        onBoardingItems.add(boarding9);
        onBoardingItems.add(boarding10);
        onBoardingItems.add(boarding11);
        onBoardingItems.add(boarding12);
        onBoardingItems.add(boarding13);
        onBoardingItems.add(boarding14);
        onBoardingItems.add(boarding15);
        adapter = new QuestionAdapter(onBoardingItems,this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void getDataAndNext(String id, Boolean answer) {
        if (answer) {
            trueTemp.add(id);
        }
        if (binding.vpQuestion.getCurrentItem() + 1 < adapter.getItemCount()) {
            binding.vpQuestion.setCurrentItem(binding.vpQuestion.getCurrentItem() + 1);
        } else {
            answerQuestion = new AnswerQuestion();
            answerQuestion.setId_baby(dbHelper.getString(Constanta.PREF_ID_BABY));
            answerQuestion.setId_user(dbHelper.getString(Constanta.PREF_ID));
            answerQuestion.setId_gejala(trueTemp);
            onDiagnosa(answerQuestion);
            binding.btnOnBoarding.setVisibility(View.VISIBLE);
        }
    }

    public void onDiagnosa(AnswerQuestion dataDiagnosa){

        Call<UserResponse> login = ApiClient.getApiServiceLaravel().toDiagnosa(dataDiagnosa);

        login.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                Log.i("error", "onResponse: "+response.message());
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Log.i("error", "onFailure: "+t.getMessage());
                t.printStackTrace();
            }
        });
    }

}