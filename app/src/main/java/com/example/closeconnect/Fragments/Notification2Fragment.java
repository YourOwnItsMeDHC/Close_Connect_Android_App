package com.example.closeconnect.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.closeconnect.Adapter.NotificationAdapter;
import com.example.closeconnect.Model.Notification;
import com.example.closeconnect.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class Notification2Fragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<Notification> list;
    FirebaseDatabase database;

    public Notification2Fragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        database = FirebaseDatabase.getInstance();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notification2, container, false);

        recyclerView = view.findViewById(R.id.notification2RV);

        list = new ArrayList<>();

//        list.add(new Notification(R.drawable.dekesugi,
//                "<b>Eminem</b> replied you", "2 minutes ago"));
//        list.add(new Notification(R.drawable.shizuka,
//                "<b>J.Balvin</b> commented on your post", "3 minutes ago"));
//        list.add(new Notification(R.drawable.nobita,
//                "<b>Mukesh Chhabra</b> sends you a friend request", "just now"));
//        list.add(new Notification(R.drawable.doraemon,
//                "<b>Drake</b> mention you in a comment", "15 minutes ago"));
//        list.add(new Notification(R.drawable.nobita_friends,
//                "<b>Ajay</b> liked your post", "just now"));
//        list.add(new Notification(R.drawable.dekesugi_painting,
//                "<b>Tory Lanez</b> mention you in a comment", "1 hour ago"));
//
//        list.add(new Notification(R.drawable.dekesugi,
//                "<b>Eminem</b> replied you", "2 minutes ago"));
//        list.add(new Notification(R.drawable.shizuka,
//                "<b>J.Balvin</b> commented on your post", "3 minutes ago"));
//        list.add(new Notification(R.drawable.nobita,
//                "<b>Mukesh Chhabra</b> sends you a friend request", "just now"));
//        list.add(new Notification(R.drawable.doraemon,
//                "<b>Drake</b> mention you in a comment", "15 minutes ago"));
//        list.add(new Notification(R.drawable.nobita_friends,
//                "<b>Ajay</b> liked your post", "just now"));
//        list.add(new Notification(R.drawable.dekesugi_painting,
//                "<b>Tory Lanez</b> mention you in a comment", "2 hour ago"));


        NotificationAdapter adapter = new NotificationAdapter(list, getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        database.getReference()
                .child("notification")
                .child(FirebaseAuth.getInstance().getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            Notification notification = dataSnapshot.getValue(Notification.class);
                            notification.setNotificationId(dataSnapshot.getKey());
                            list.add(notification);
                        }
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

        return view;
    }
}