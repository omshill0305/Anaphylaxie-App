package com.example.olga.aa_app;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TableRow;
import java.util.ArrayList;
import android.widget.ArrayAdapter;


public class TreatmentRedActivity extends AppCompatActivity {
    Button tagButton3;
    ArrayList<String> instructionListDBEXample;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treatment_red);

        instructionListDBEXample = new ArrayList<>();
        instructionListDBEXample.add("Es bestehen Anzeichen für eine schwere Reaktion");
        instructionListDBEXample.add("Bitte bewahren Sie Ruhe");
        instructionListDBEXample.add("Bitte Fastjekt verabreichen");


        ListView list = (ListView) findViewById(R.id.dynamicView);
        String[] instructionList = new String[instructionListDBEXample.size()];
        for (int i = 0; i < instructionList.length; i++) {

            instructionList[i] = (i+1) + ". " + instructionListDBEXample.get(i);

        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.list_adapter_view, R.id.textView18, instructionList);
        list.setAdapter(arrayAdapter);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setLogo(R.drawable.logo1);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }

        showAddItemDialog1(null);


        TableRow raw1 = (TableRow) findViewById(R.id.raw2);
        tagButton3 = new Button(this);
        tagButton3.setText("Kribbeln in Mund und Rachen");
        tagButton3.setLayoutParams(new TableRow.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        raw1.addView(tagButton3);

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

    private void showAddItemDialog2(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.MyAlertDialogStyle2);
        builder.setTitle("Schritt 2");



        final View customLayout = getLayoutInflater().inflate(R.layout.custom_layout2, null);
        builder.setView(customLayout);


        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showAddItemDialog1(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.MyAlertDialogStyle2);
        builder.setTitle("Schritt 1");




        final View customLayout = getLayoutInflater().inflate(R.layout.custom_layout_red, null);
        builder.setView(customLayout);


        builder.setPositiveButton("Weiter", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                showAddItemDialog2(null);
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }



}
