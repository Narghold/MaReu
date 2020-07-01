package com.dubois.yann.mareu.controller.addMeeting;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dubois.yann.mareu.R;
import com.dubois.yann.mareu.event.AddMeetingEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ParticipantFragment extends Fragment{

    @BindView(R.id.add_new_participant_input)
    EditText mParticipantInput;
    @BindView(R.id.add_new_participant_btn)
    Button mAddParticipantButton;
    @BindView(R.id.list_participant)
    RecyclerView mRecyclerParticipants;

    private ParticipantsRecyclerViewAdapter mParticipantAdapter;

    private List<String> mParticipantList = new ArrayList<>();
    private static List<String> mParticipantListStatic;

    public static Fragment newInstance() {
        ParticipantFragment fragment = new ParticipantFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_participant, container, false);
        ButterKnife.bind(this, view);
        Context context = view.getContext();
        mRecyclerParticipants.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        mRecyclerParticipants.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        mParticipantAdapter  = new ParticipantsRecyclerViewAdapter(mParticipantList);
        mRecyclerParticipants.setAdapter(mParticipantAdapter);

        //OnClickListener to add participant to participant resume
        mAddParticipantButton.setOnClickListener(v -> {
            mParticipantList.add(mParticipantInput.getText().toString());
            mParticipantInput.getText().clear();
            mParticipantListStatic = mParticipantList;
            mParticipantAdapter.setData(mParticipantList);
            EventBus.getDefault().post(new AddMeetingEvent(mParticipantList));
        });
        return view;
    }
}
