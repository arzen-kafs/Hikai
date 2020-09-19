package com.example.hikai.hikaiplayer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hikai.R;

import java.util.List;

public class LectureAdapter extends RecyclerView.Adapter<LectureAdapter.LectureViewHolder> {

    private List<Lecture> lectureList;
    RecyclerViewClickInterface recyclerViewClickInterface;
    LectureAdapter(List<Lecture> lectureList,RecyclerViewClickInterface recyclerViewClickInterface) {
        this.lectureList = lectureList;
        this.recyclerViewClickInterface=recyclerViewClickInterface;
    }

    @NonNull
    @Override
    public LectureViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_lecture, viewGroup, false);
        return new LectureViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LectureViewHolder subItemViewHolder, int i) {
        final Lecture lecture = lectureList.get(i);
        subItemViewHolder.tvSubItemTitle.setText(lecture.getLectureTitle());
        subItemViewHolder.linearLayoutVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), lecture.getLectureUrl(), Toast.LENGTH_SHORT).show();
                recyclerViewClickInterface.onItemClick(lecture.getLectureUrl());

            }
        });
        subItemViewHolder.btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //recyclerViewClickInterface.onDownloadClick(lecture.getLectureUrl());
                Toast.makeText(view.getContext(), lecture.getLectureUrl(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return lectureList.size();
    }



    class LectureViewHolder extends RecyclerView.ViewHolder {
        TextView tvSubItemTitle;
        LinearLayout linearLayoutVideo;
        ImageView btnDownload;

        LectureViewHolder(View itemView) {
            super(itemView);
            tvSubItemTitle = itemView.findViewById(R.id.tv_lecture_title);
            linearLayoutVideo = itemView.findViewById(R.id.linear_layout_video);
            btnDownload = itemView.findViewById(R.id.btn_download_video);
        }
    }
}
