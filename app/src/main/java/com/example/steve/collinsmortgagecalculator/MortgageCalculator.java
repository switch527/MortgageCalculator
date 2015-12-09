package com.example.steve.collinsmortgagecalculator;


import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import java.text.DecimalFormat;

import static java.lang.Math.pow;




public class MortgageCalculator extends ActionBarActivity {

    private static final String house_Price = "House_Price";
    private static final String down_Payment = "Down_Payment";
    private static final String interest_Rate = "Interest_Rate";
    private static final String loan_Length = "Loan Length";

    private double housePrice2;
    private double downPayment2;
    private double interestRate2;
    private double loanLength2;
    private double loanAmount2;
    private double loanLengthMonths2;
    private double monthlyInterestRate2;
    private double monthlyPayment2;
    private double totalPayment2;

    String monthlyPaymentString;
    String totalPaymentString;

    EditText housePrice;
    EditText downPayment;
    EditText interestRate;
    EditText loanLength;
    EditText monthlyPayment;
    EditText totalPayment;

    DecimalFormat monetary = new DecimalFormat("#.##");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mortgage_calculator);

        housePrice = (EditText) findViewById(R.id.editTextHousePrice);
        downPayment = (EditText) findViewById(R.id.editTextDownPayment);
        interestRate = (EditText) findViewById(R.id.editTextInterest);
        loanLength = (EditText)findViewById(R.id.editTextLength);
        monthlyPayment = (EditText)findViewById(R.id.editTextMonthly);
        totalPayment = (EditText)findViewById(R.id.editTextTotal);
        Button buttonCompute = (Button)findViewById(R.id.buttonCompute);
        Button buttonCancel = (Button)findViewById(R.id.buttonCancel);




        if (savedInstanceState == null) {
            housePrice2 = 0.0;
            downPayment2 = 0.0;
            interestRate2 = 0.0;
            loanLength2 = 0.0;
        }
        else {
            housePrice2 = savedInstanceState.getDouble(house_Price);
            downPayment2 = savedInstanceState.getDouble(down_Payment);
            interestRate2 = savedInstanceState.getDouble(interest_Rate);
            loanLength2 = savedInstanceState.getDouble(loan_Length);
        }



        buttonCompute.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v){
                        try {
                            housePrice2 = Double.parseDouble(housePrice.getText().toString());
                            downPayment2 = Double.parseDouble(downPayment.getText().toString());
                            interestRate2 = Double.parseDouble(interestRate.getText().toString());
                            loanLength2 = Double.parseDouble(loanLength.getText().toString());
                        }
                        catch (NumberFormatException e) {

                        }
                        if (housePrice2 != 0.0 && loanLength2 != 0.0) {
                            if (interestRate2 == 0.0) {
                                loanAmount2 = (housePrice2 - downPayment2);
                                loanLengthMonths2 = (loanLength2 * 12);
                                monthlyPayment2 = (loanAmount2 / loanLengthMonths2);
                                totalPayment2 = loanAmount2;
                            }
                            else {
                                loanAmount2 = (housePrice2 - downPayment2);
                                monthlyInterestRate2 = (interestRate2 / 1200);
                                loanLengthMonths2 = (loanLength2 * 12);
                                monthlyPayment2 = ((loanAmount2 * monthlyInterestRate2) / (1 - (pow((1 + monthlyInterestRate2), (-loanLengthMonths2)))));
                                totalPayment2 = (monthlyPayment2 * loanLengthMonths2);
                            }

                            monthlyPaymentString = monetary.format(monthlyPayment2);
                            totalPaymentString = monetary.format(totalPayment2);
                            monthlyPayment.setText("$"+monthlyPaymentString);
                            totalPayment.setText("$"+totalPaymentString);
                        }
                        else {
                            //Alert Alert Alert

                        }
                    }
                }
        );

        buttonCancel.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        housePrice2 = 0.0;
                        downPayment2 = 0.0;
                        interestRate2 = 0.0;
                        loanLength2 = 0.0;

                        housePrice.setText(null);
                        downPayment.setText(null);
                        interestRate.setText(null);
                        loanLength.setText(null);
                        monthlyPayment.setText(null);
                        totalPayment.setText(null);
                    }
                }
        );


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_mortgage_calculator, menu);
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
}
