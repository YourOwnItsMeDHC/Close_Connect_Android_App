package com.example.closeconnect;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.closeconnect.Fragments.AddFragment;
import com.example.closeconnect.Fragments.AddPostFragment;
import com.example.closeconnect.Fragments.MainFragment;
import com.example.closeconnect.Fragments.NotificationFragment;
import com.example.closeconnect.Fragments.ProfileFragment;
import com.example.closeconnect.Fragments.SearchFragment;
import com.example.closeconnect.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //For Toolbar in "My Profile" page :
        setSupportActionBar(binding.toolbar);
        MainActivity.this.setTitle("My Profile");

        //Adding below home fragment code , so it will be visible on screen by default:
        FragmentTransaction mainTrans = getSupportFragmentManager().beginTransaction();
        binding.toolbar.setVisibility(View.GONE);
        mainTrans.replace(R.id.content, new MainFragment());
        mainTrans.commit();

        binding.readableBottomBar.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                //Optimized code of below code :
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                if(item.getItemId() == R.id.main) {
                    binding.toolbar.setVisibility(View.GONE);
                    transaction.replace(R.id.content, new MainFragment());
                }
                else if(item.getItemId() == R.id.search) {
                    binding.toolbar.setVisibility(View.GONE);
                    transaction.replace(R.id.content, new SearchFragment());
                }
                else if(item.getItemId() == R.id.add) {
                    binding.toolbar.setVisibility(View.GONE);
                    transaction.replace(R.id.content, new AddPostFragment());
                }
                else if(item.getItemId() == R.id.notification) {
                    binding.toolbar.setVisibility(View.GONE);
                    transaction.replace(R.id.content, new NotificationFragment());
                }
                else{
                    binding.toolbar.setVisibility(View.VISIBLE);
                    transaction.replace(R.id.content, new ProfileFragment());
                }
                transaction.commit();


//                //Optimized code of below code is given above ⬆
//                if(item.getItemId() == R.id.main) {
//                    FragmentTransaction mainTrans = getSupportFragmentManager().beginTransaction();
//                    mainTrans.replace(R.id.content, new MainFragment());
//                    mainTrans.commit();
//                    //Toast.makeText(MainActivity.this, "Home Selected", Toast.LENGTH_SHORT).show();
//                }
//                else if(item.getItemId() == R.id.search) {
//                    FragmentTransaction searchTrans = getSupportFragmentManager().beginTransaction();
//                    searchTrans.replace(R.id.content, new SearchFragment());
//                    searchTrans.commit();
//                    //Toast.makeText(MainActivity.this, "Search Selected", Toast.LENGTH_SHORT).show();
//                }
//                else if(item.getItemId() == R.id.add) {
//                    FragmentTransaction addTrans = getSupportFragmentManager().beginTransaction();
//                    addTrans.replace(R.id.content, new AddFragment());
//                    addTrans.commit();
//                    //Toast.makeText(MainActivity.this, "Add Selected", Toast.LENGTH_SHORT).show();
//                }
//                else if(item.getItemId() == R.id.notification) {
//                    FragmentTransaction notificationTrans = getSupportFragmentManager().beginTransaction();
//                    notificationTrans.replace(R.id.content, new NotificationFragment());
//                    notificationTrans.commit();
//                    //Toast.makeText(MainActivity.this, "Notification Selected", Toast.LENGTH_SHORT).show();
//                }
//                else{
//                    FragmentTransaction profileTrans = getSupportFragmentManager().beginTransaction();
//                    profileTrans.replace(R.id.content, new ProfileFragment());
//                    profileTrans.commit();
//                    //Toast.makeText(MainActivity.this, "Profile Selected", Toast.LENGTH_SHORT).show();
//                }




//                //Commented switch case code (below one), because it was giving "non-final" keyword error
//                switch (getTaskId()) {
//                    case R.id.main:
//                        FragmentTransaction mainTrans = getSupportFragmentManager().beginTransaction();
//                        mainTrans.replace(R.id.content, new MainFragment());
//                        mainTrans.commit();
//                      //Toast.makeText(MainActivity.this, "Home Selected", Toast.LENGTH_SHORT).show();
//                        break;
//
//                    case R.id.search:
//                        FragmentTransaction searchTrans = getSupportFragmentManager().beginTransaction();
//                        searchTrans.replace(R.id.content, new SearchFragment());
//                        searchTrans.commit();
//                       //Toast.makeText(MainActivity.this, "Search Selected", Toast.LENGTH_SHORT).show();
//                        break;
//
//                    case R.id.add:
//                        FragmentTransaction addTrans = getSupportFragmentManager().beginTransaction();
//                        addTrans.replace(R.id.content, new AddFragment());
//                        addTrans.commit();
//                        //Toast.makeText(MainActivity.this, "Add Selected", Toast.LENGTH_SHORT).show();
//                        break;
//
//                    case R.id.notification:
//                        FragmentTransaction notificationTrans = getSupportFragmentManager().beginTransaction();
//                        notificationTrans.replace(R.id.content, new NotificationFragment());
//                        notificationTrans.commit();
//                        //Toast.makeText(MainActivity.this, "Notification Selected", Toast.LENGTH_SHORT).show();
//                        break;
//
//                    case R.id.profile:
//                        FragmentTransaction profileTrans = getSupportFragmentManager().beginTransaction();
//                        profileTrans.replace(R.id.content, new ProfileFragment());
//                        profileTrans.commit();
//                        //Toast.makeText(MainActivity.this, "Profile Selected", Toast.LENGTH_SHORT).show();
//                        break;
//                }
                return true;
            }
        });
   }

   //For setting button in "My Profile" page
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {


            case R.id.setting:
                auth.signOut();
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}