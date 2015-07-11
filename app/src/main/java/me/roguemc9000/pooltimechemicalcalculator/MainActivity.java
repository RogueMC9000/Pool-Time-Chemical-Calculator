package me.roguemc9000.pooltimechemicalcalculator;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.provider.Settings;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;


public class MainActivity extends ActionBarActivity {

    private double PH = 7.5;
    private int ALKALINITY = 100;
    public static AdRequest adRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new PagerFragmentAdapter(getSupportFragmentManager(), this));

        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);

        adRequest = new AdRequest.Builder().addTestDevice("F8997865B0017036").addKeyword("pool").build();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void calcPh(View view) {
        double pH = Double.parseDouble(((TextView) findViewById(R.id.valuePh)).getText().toString());
        int gallons = Integer.parseInt(((TextView) findViewById(R.id.gallonsPh)).getText().toString());
        String pref = ((Spinner)findViewById(R.id.spinnerPh)).getSelectedItem().toString();
        boolean greater = pH - PH > 0;
        double difference = Math.abs(pH - PH);
        int change = (int) (difference / .1);
        double amount;
        if (greater) {
            if (pref.equals("Muriatic Acid")) amount = .5 * change * (gallons / 1000);
            else amount = .4 * change * (gallons / 1000);
        } else amount = .4 * change * (gallons / 1000);
        long a = Math.round(amount);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setNeutralButton("Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setTitle("pH Calculator").setMessage("Your pool needs " + a + " oz. (volume) of " + (greater ? (pref.equals("Muriatic Acid") ? "Muriatic Acid" : "Dry Acid") : "Soda Ash") + ".");
        builder.create();
        builder.show();
    }

    public void calcAlk(View view) {
        int alk = Integer.parseInt(((TextView) findViewById(R.id.valueAlk)).getText().toString());
        int gallons = Integer.parseInt(((TextView)findViewById(R.id.gallonsAlk)).getText().toString());
        String pref = ((Spinner)findViewById(R.id.spinnerAlk)).getSelectedItem().toString();
        boolean greater = alk - ALKALINITY > 0;
        int difference = Math.abs(alk - ALKALINITY);
        double change = difference / 10;
        double amount;
        if (greater) {
            if (pref.equals("Muriatic Acid")) amount = 2.56 * change * (gallons / 1000);
            else amount = 3.44 * change * (gallons / 1000);
        } else amount = 2.24 * change * (gallons / 1000);
        long a = Math.round(amount);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setNeutralButton("Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setTitle("Alkalinity Calculator").setMessage("Your pool needs " + a + " oz. (volume) of " + (greater ? (pref.equals("Muriatic Acid") ? "Muriatic Acid" : "Dry Acid") : "Baking Soda"));
        builder.create();
        builder.show();
    }

    public void calcGal(View view) {
        int ft = Integer.parseInt(((TextView)findViewById(R.id.valueGal)).getText().toString());
        int gallons = (int)Math.round(ft * 7.48);
        AlertDialog.Builder builder = new AlertDialog.Builder(this); builder.setNeutralButton("Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setTitle("Gallons Calculator").setMessage("Your pool has about " + gallons + " gallons.");
        builder.create();
        builder.show();
    }

}
