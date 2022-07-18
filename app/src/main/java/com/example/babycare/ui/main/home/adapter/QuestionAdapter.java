package com.example.babycare.ui.main.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.babycare.databinding.ItemQuestionBinding;
import com.example.babycare.model.Question;
import com.example.babycare.ui.main.home.contract.HomeContract;
import com.example.babycare.ui.main.home.question.QuestionActivity;

import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder>{

    private final List<Question> questionList;
    private final Context context;
    private HomeContract homeContract;

    public QuestionAdapter(List<Question> questionList, Context context) {
        this.questionList = questionList;
        this.context = context;
    }

    @NonNull
    @Override
    public QuestionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemQuestionBinding binding = ItemQuestionBinding.inflate(LayoutInflater.from(context)
                , parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionAdapter.ViewHolder holder, int position) {
        Question question = questionList.get(position);
        Glide.with(context)
                .load(question.getScreenImg())
                .into(holder.binding.imgOnBoarding);
        holder.binding.tvTitle.setText(question.getTitle());
        holder.binding.tvNo.setText(question.getNo());
        QuestionActivity a = new QuestionActivity();
        homeContract = (HomeContract) a;
        holder.binding.btnYa.setOnClickListener(v -> {
                homeContract.getDataAndNext(question.getId(), true);
        });
        holder.binding.btnTidak.setOnClickListener(v -> {
            homeContract.getDataAndNext(question.getId(), false);
        });
    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemQuestionBinding binding;
        public ViewHolder(ItemQuestionBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
