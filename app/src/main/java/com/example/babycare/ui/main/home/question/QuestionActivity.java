package com.example.babycare.ui.main.home.question;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.babycare.R;
import com.example.babycare.databinding.ActivityQuestionBinding;
import com.example.babycare.model.AnswerQuestion;
import com.example.babycare.model.Question;
import com.example.babycare.ui.main.home.adapter.QuestionAdapter;
import com.example.babycare.ui.main.home.contract.HomeContract;
import com.example.babycare.ui.main.home.hasil.HasilActvity;

import java.util.ArrayList;
import java.util.List;

public class QuestionActivity extends AppCompatActivity implements HomeContract {

    public static final String UMUR = "umur";
    static ActivityQuestionBinding binding;
    private int currentQuestionIndex = 0;
    private TextView questionTextView;
    private ImageView Image;
    static QuestionAdapter adapter;
    private List<AnswerQuestion> answeredQuestion = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        binding = ActivityQuestionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

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

        binding.btnOnBoarding.setOnClickListener(view -> {
            if (binding.vpQuestion.getCurrentItem() + 1 < adapter.getItemCount()) {
                binding.vpQuestion.setCurrentItem(binding.vpQuestion.getCurrentItem() + 1);
            } else {
                startActivity(new Intent(getApplicationContext(), HasilActvity.class));
                finish();
            }
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
        if (index == adapter.getItemCount() - 1){
            binding.btnOnBoarding.setText("Mulai");
        }else {
            binding.btnOnBoarding.setText("Next");
        }
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
        boarding.setId("G001");
        boarding.setTitle(getResources().getString(R.string.question1));
        boarding.setScreenImg(R.drawable.womanwithbaby);

        Question boarding1 = new Question();
        boarding.setId("G002");
        boarding1.setTitle(getResources().getString(R.string.question2));
        boarding1.setScreenImg(R.drawable.icon_diagnosa);

        Question boarding2 = new Question();
        boarding.setId("G003");
        boarding2.setTitle(getResources().getString(R.string.question3));
        boarding2.setScreenImg(R.drawable.womanwithbaby);

        onBoardingItems.add(boarding);
        onBoardingItems.add(boarding1);
        onBoardingItems.add(boarding2);
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
//        if (answer) {
//        }
        AnswerQuestion answ = new AnswerQuestion(id, answer);
        answeredQuestion.add(answ);
        if (binding.vpQuestion.getCurrentItem() + 1 < adapter.getItemCount()) {
            binding.vpQuestion.setCurrentItem(binding.vpQuestion.getCurrentItem() + 1);
        } else {
//            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
//            finish();
            for (int i = 0; i < answeredQuestion.size(); i++) {
                Log.d("TAG", "getDataAndNext: "+answeredQuestion.get(i).getAnswer());
            }
        }
    }
}