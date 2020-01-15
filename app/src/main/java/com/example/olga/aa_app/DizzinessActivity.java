package com.example.olga.aa_app;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.HashMap;

public class DizzinessActivity extends AppCompatActivity {

    Button choose;
    HashMap<String, String> des;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dizziness);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setLogo(R.drawable.logo1);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }

        choose = findViewById(R.id.choose);
        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DizzinessActivity.this, TreatmentRedActivity.class));
            }
        });

        des = new HashMap();

        setInfoButtons(R.id.listing_layout);
    }

    private void setInfoButtons(int id){
        ViewGroup root = findViewById(id);
        for (int i = 0; i< root.getChildCount(); i++){
            final ViewGroup child = (ViewGroup) root.getChildAt(i);
            ImageButton button = (ImageButton) child.getChildAt(1);
            button.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    AlertDialog.Builder builder = new AlertDialog.Builder(DizzinessActivity.this, R.style.MyAlertDialogStyleInfo);
                    CheckBox checkbox = (CheckBox) child.getChildAt(0);
                    builder.setTitle(checkbox.getText());

                    LinearLayout linear_layout = (LinearLayout) getLayoutInflater().inflate(R.layout.custom_layout_red, null);
                    TextView text_view = (TextView) linear_layout.getChildAt(0);
                    text_view.setText(des.get(checkbox.getText()));
                    builder.setView(linear_layout);

                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {

                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_extra, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (getFragmentManager().getBackStackEntryCount() == 0) {
            super.onBackPressed();
        } else if (getFragmentManager().getBackStackEntryCount() == 1) {
            moveTaskToBack(false);
        } else {
            getFragmentManager().popBackStack();
        }
        return true;
    }
}