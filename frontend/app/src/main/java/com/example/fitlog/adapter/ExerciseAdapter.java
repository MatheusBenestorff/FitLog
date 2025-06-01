package com.example.fitlog.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitlog.R;
import com.example.fitlog.models.Exercise;

import java.util.ArrayList;
import java.util.List;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder> {

    private List<Exercise> exerciseList;
    private OnExerciseSelectedListener listener;

    public interface OnExerciseSelectedListener {
        void onSelectionChanged();
    }

    public ExerciseAdapter(List<Exercise> list, OnExerciseSelectedListener listener) {
        this.exerciseList = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_exercise, parent, false);
        return new ExerciseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseViewHolder holder, int position) {
        Exercise exercise = exerciseList.get(position);
        holder.name.setText(exercise.getName());
        holder.group.setText(exercise.getMuscleGroup());

        holder.itemView.setBackgroundColor(exercise.isSelected() ? Color.LTGRAY : Color.TRANSPARENT);

        holder.itemView.setOnClickListener(v -> {
            exercise.setSelected(!exercise.isSelected());
            notifyItemChanged(position);
            listener.onSelectionChanged();
        });
    }

    @Override
    public int getItemCount() {
        return exerciseList.size();
    }

    public List<Exercise> getSelectedExercises() {
        List<Exercise> selected = new ArrayList<>();
        for (Exercise e : exerciseList) {
            if (e.isSelected()) selected.add(e);
        }
        return selected;
    }

    static class ExerciseViewHolder extends RecyclerView.ViewHolder {
        TextView name, group;
        public ExerciseViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.exercise_name);
            group = itemView.findViewById(R.id.exercise_group);
        }
    }
}
