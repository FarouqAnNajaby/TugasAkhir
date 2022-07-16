package com.example.babycare.ui.main.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.babycare.databinding.ItemBabyBinding;
import com.example.babycare.model.Baby;
import com.example.babycare.ui.main.home.question.QuestionActivity;

import java.util.List;

public class BabyAdapter extends RecyclerView.Adapter<BabyAdapter.MyViewHolder> {

    private Context context;
    private List<Baby> babyList;

    public BabyAdapter(Context context, List<Baby> babyList) {
        this.context = context;
        this.babyList = babyList;
    }

    @NonNull
    @Override
    public BabyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemBabyBinding binding = ItemBabyBinding.inflate(LayoutInflater.from(context), parent,
                false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BabyAdapter.MyViewHolder holder, int position) {
        try {
            Baby data = babyList.get(position);
            holder.binding.tvNama.setText(data.getNameBaby());
//            holder.binding.tvUmur.setText(calculateAge(data.getDateBirthBaby()));
            holder.binding.tvUmur.setText("3 Tahun");
            holder.itemView.setOnClickListener(v->{
                Intent intent = new Intent(context, QuestionActivity.class);
                intent.putExtra(QuestionActivity.ID_BABY, data.getIdBaby());
                context.startActivity(intent);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return babyList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ItemBabyBinding binding;

        public MyViewHolder(@NonNull ItemBabyBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }


}
