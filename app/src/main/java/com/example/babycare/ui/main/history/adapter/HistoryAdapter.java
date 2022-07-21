package com.example.babycare.ui.main.history.adapter;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.babycare.databinding.ItemHistoryBinding;
import com.example.babycare.model.Baby;
import com.example.babycare.model.DataDiagnosa;
import com.example.babycare.ui.main.home.hasil.HasilActvity;
import com.example.babycare.ui.main.home.question.QuestionActivity;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    private Context context;
    private List<DataDiagnosa> dataDiagnosaList;

    public HistoryAdapter(Context context, List<DataDiagnosa> dataDiagnosaList) {
        this.context = context;
        this.dataDiagnosaList = dataDiagnosaList;
    }

    @NonNull
    @Override
    public HistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemHistoryBinding binding = ItemHistoryBinding.inflate(LayoutInflater.from(context), parent,
                false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.ViewHolder holder, int position) {
        try {
            DataDiagnosa data = dataDiagnosaList.get(position);
            holder.binding.tvNameBaby.setText(data.getNameBaby());
            holder.binding.tvDate.setText(data.getDate());
            holder.binding.tvDiganosa.setText(data.getPenyakit());
            holder.itemView.setOnClickListener(v->{
                Intent intent = new Intent(context, HasilActvity.class);
                intent.putExtra(HasilActvity.DIAGNOSA,data.getUuid());
                intent.putExtra(HasilActvity.LABEL,"history");
                context.startActivity(intent);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return dataDiagnosaList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemHistoryBinding binding;

        public ViewHolder(@NonNull ItemHistoryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
