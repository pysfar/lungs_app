package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.myapplication.Common.Common;
import com.example.myapplication.Interface.ItemClickListener;
import com.example.myapplication.Interface.RankingCallBack;
import com.example.myapplication.ViewHolder.RankingViewHolder;
import com.example.myapplication.model.QuestionScore;
import com.example.myapplication.model.Ranking;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RankingFragment extends Fragment {
    View myFragment;

    RecyclerView rankingList;
    LinearLayoutManager layoutManager;
    FirebaseRecyclerAdapter<Ranking, RankingViewHolder>adapter;

    FirebaseDatabase database;
    DatabaseReference questionScore,rankingTbl;
    int sum = 0;
    public static RankingFragment newInstance(){
        RankingFragment rankingFragment = new RankingFragment();
        return rankingFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        database = FirebaseDatabase.getInstance();
        questionScore = database.getReference("Question_Score");
        rankingTbl = database.getReference("Ranking");

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myFragment = inflater.inflate(R.layout.fragment_ranking,container,false);

        rankingList = myFragment.findViewById(R.id.rankingList);
        layoutManager = new LinearLayoutManager(getActivity());
        rankingList.setHasFixedSize(true);

        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        rankingList.setLayoutManager(layoutManager);
        
        updateScore(Common.currentUser.getUsername(), new RankingCallBack<Ranking>() {
                    @Override
                    public void callBack(Ranking ranking) {
                        rankingTbl.child(ranking.getUserName()).setValue(ranking);

                    }
                });

                adapter = new FirebaseRecyclerAdapter<Ranking, RankingViewHolder>(
                        Ranking.class,
                        R.layout.layout_ranking,
                        RankingViewHolder.class,
                        rankingTbl.orderByChild("score")
                ) {
                    @Override
                    protected void populateViewHolder(RankingViewHolder viewHolder, Ranking model, int position) {
                        viewHolder.txt_name.setText(model.getUserName());
                        viewHolder.txt_score.setText(String.valueOf(model.getScore()));

                        viewHolder.setItemClickListener(new ItemClickListener() {
                            @Override
                            public void onclick(View view, int position, boolean isLongClick) {
                                Intent scoreDetail = new Intent(getActivity(),ScoreDetail.class);
                                scoreDetail.putExtra("viewUser",model.getUserName());
                                startActivity(scoreDetail);
                            }
                        });
                    }
                };
                adapter.notifyDataSetChanged();
                rankingList.setAdapter(adapter);




        return myFragment;
    }



    private void updateScore(String username, RankingCallBack<Ranking>callback) {
        questionScore.orderByChild("user").equalTo(username)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot data:dataSnapshot.getChildren())
                        {
                            QuestionScore ques = data.getValue(QuestionScore.class);
                            sum+=Integer.parseInt(ques.getScore());
                            
                        }
                        Ranking ranking = new Ranking(username,sum);
                        callback.callBack(ranking);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }
}

