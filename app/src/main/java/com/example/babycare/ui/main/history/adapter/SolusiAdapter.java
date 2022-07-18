package com.example.babycare.ui.main.history.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.babycare.databinding.ItemGejalaSolusiBinding;

import java.util.List;

public class SolusiAdapter extends RecyclerView.Adapter<SolusiAdapter.ViewHolder> {

    List<String> list;
    Context context;

    public SolusiAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public SolusiAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemGejalaSolusiBinding binding = ItemGejalaSolusiBinding.inflate(LayoutInflater.from(context), parent,
                false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SolusiAdapter.ViewHolder holder, int position) {
        String data = list.get(position);
        holder.binding.tvData.setText(data);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemGejalaSolusiBinding binding;
        public ViewHolder(@NonNull ItemGejalaSolusiBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
