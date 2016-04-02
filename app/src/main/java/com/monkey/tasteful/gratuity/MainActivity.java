package com.monkey.tasteful.gratuity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.math.BigDecimal;
import java.text.NumberFormat;

public class MainActivity extends Activity {
    SeekBar seekBar;
    TextView percentTip, tip, total;
    Button calculate;
    EditText subtotal;
    BigDecimal totalAmount, tipAmount;
    double tipPercent = 0.15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        percentTip = (TextView) findViewById(R.id.tipPercent);
        tip = (TextView) findViewById(R.id.tip);
        total = (TextView) findViewById(R.id.total);
        calculate = (Button) findViewById(R.id.button);
        subtotal = (EditText) findViewById(R.id.subtotal);

        seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                percentTip.setText("%" + String.valueOf(i));
                tipPercent = i*.01;

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        percentTip.setText("%" + seekBar.getProgress());

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (subtotal.getText() != null) {
                    BigDecimal sbtl = new BigDecimal(Double.valueOf(subtotal.getText().toString()));
                    tipAmount = sbtl.multiply(new BigDecimal(tipPercent));
                    totalAmount = sbtl.add(tipAmount);
                    String tipAmountText = NumberFormat.getCurrencyInstance().format(tipAmount);
                    tip.setText(tipAmountText);
                    String totalAmountText =  NumberFormat.getCurrencyInstance().format(totalAmount);
                    total.setText(totalAmountText);
                }
            }
        });
    }

}

