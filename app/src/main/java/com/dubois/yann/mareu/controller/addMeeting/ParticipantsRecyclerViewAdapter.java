package com.dubois.yann.mareu.controller.addMeeting;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.dubois.yann.mareu.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ParticipantsRecyclerViewAdapter extends RecyclerView.Adapter<ParticipantsRecyclerViewAdapter.ViewHolder>{

    private List<String> mParticipantList;

    public ParticipantsRecyclerViewAdapter(List<String> items) {
        mParticipantList = items;
    }

    @NonNull
    @Override
    public ParticipantsRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_participant, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ParticipantsRecyclerViewAdapter.ViewHolder holder, int position) {
        String participant = mParticipantList.get(position);
        holder.mParticipantMail.setText(mParticipantList.get(position));

        holder.mParticipantDeleteBtn.setOnClickListener(v -> {
            mParticipantList.remove(participant);
            setData(mParticipantList);
        });
    }

    @Override
    public int getItemCount() {
        if (mParticipantList != null){
            return mParticipantList.size();
        }else{
            return 0;
        }

    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_list_participant)
        ConstraintLayout mParticipant;
        @BindView(R.id.participant_mail)
        TextView mParticipantMail;
        @BindView(R.id.participant_delete_button)
        ImageView mParticipantDeleteBtn;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    /**
     * Update the participant list
     * @param list
     */
    public void setData(List<String> list){
        this.mParticipantList = list;
        notifyDataSetChanged();
    }
}