package com.example.user.ihsprac;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class SingleihspracActivity extends AppCompatActivity {

    private String post_key = null;
    private DatabaseReference mDatabase;
    private TextView singlePostTitle, singlePostDesc, singlePostUsername;
    private ImageView singlePostImage;
    private Button deleteButton;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singleihsprac);

        post_key = getIntent().getExtras().getString("PostId");
        Toast.makeText(this, post_key, Toast.LENGTH_SHORT).show();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Ihsprac");
        singlePostTitle = (TextView) findViewById(R.id.singleTitle);
        singlePostDesc = (TextView) findViewById(R.id.singleDesc);
        singlePostUsername = (TextView) findViewById(R.id.singleUser);
        singlePostImage = (ImageView) findViewById(R.id.singleImageView);
        mAuth = FirebaseAuth.getInstance();
        deleteButton = (Button) findViewById(R.id.singleDeleteButton);
        deleteButton.setVisibility(View.INVISIBLE);

        mDatabase.child(post_key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String post_title = (String) dataSnapshot.child("title").getValue();
                String post_desc = (String) dataSnapshot.child("desc").getValue();
                String post_image = (String) dataSnapshot.child("image").getValue();
                String post_uid = (String) dataSnapshot.child("uid").getValue();
                String post_username = (String) dataSnapshot.child("username").getValue();


                singlePostTitle.setText(post_title);
                singlePostDesc.setText(post_desc);
                singlePostUsername.setText(post_username);
                Picasso.get().load(post_image).into(singlePostImage);

                if(mAuth.getCurrentUser().getUid().equals(post_uid)){
                    deleteButton.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public  void deleteButtonClicked(View view){
        mDatabase.child(post_key).removeValue();
        Intent mainIntent= new Intent(SingleihspracActivity.this,MainActivity.class);
        startActivity(mainIntent);
    }
}
