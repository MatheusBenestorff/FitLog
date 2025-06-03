package com.example.fitlog.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.fitlog.R;
import com.example.fitlog.models.Exercise;

import java.util.ArrayList;
import java.util.List;

public class SelectedExerciseDisplayAdapter extends RecyclerView.Adapter<SelectedExerciseDisplayAdapter.ViewHolder> {

    private List<Exercise> selectedExercises;

    public SelectedExerciseDisplayAdapter(List<Exercise> selectedExercises) {
        this.selectedExercises = selectedExercises;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_selected_exercise_display, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Exercise exercise = selectedExercises.get(position);
        holder.name.setText(exercise.getName());

        if (exercise.getMuscleGroup() != null && !exercise.getMuscleGroup().isEmpty()) {
            holder.group.setText(exercise.getMuscleGroup());
            holder.group.setVisibility(View.VISIBLE);
        } else {
            holder.group.setText("");
            holder.group.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return selectedExercises != null ? selectedExercises.size() : 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, group;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.exercise_name);
            group = itemView.findViewById(R.id.exercise_group);
        }
    }


    public void updateExercises(List<Exercise> newExercises) {

        if (newExercises != null) {
            this.selectedExercises = new ArrayList<>(newExercises);
        } else {
            this.selectedExercises.clear();
        }
        notifyDataSetChanged();
    }
}
