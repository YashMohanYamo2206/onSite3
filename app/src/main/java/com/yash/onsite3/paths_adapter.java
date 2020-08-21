package com.yash.onsite3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class paths_adapter extends RecyclerView.Adapter<paths_adapter.paths_viewHolder> {

    private ArrayList<pathDetails> pathDetails;

    private OnItemClickListener listener;

    public paths_adapter(ArrayList<com.yash.onsite3.pathDetails> pathDetails) {
        this.pathDetails = pathDetails;
    }

    public interface OnItemClickListener {
        void onItemClick(int pos);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }


    public class paths_viewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout itemLayout;
        ImageView open_closed;
        TextView name;
        public paths_viewHolder(@NonNull View itemView) {
            super(itemView);
            itemLayout = itemView.findViewById(R.id.file_item);
            open_closed = itemView.findViewById(R.id.open_closed);
            name = itemView.findViewById(R.id.root_name);
            itemLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null) {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }


    @NonNull
    @Override
    public paths_viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_file_item, parent, false);
        return new paths_viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull paths_viewHolder holder, int position) {
        pathDetails pathDetail = pathDetails.get(position);
        holder.name.setText(pathDetail.getName());
        holder.open_closed.setImageResource(pathDetail.getImg());
    }

    @Override
    public int getItemCount() {
        return pathDetails.size();
    }
}
