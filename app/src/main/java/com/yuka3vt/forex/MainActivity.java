package com.yuka3vt.forex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ProgressBar loadingProgressBar;
    private SwipeRefreshLayout swipeRefreshLayout;
    private TextView audTextView,bnbTextView,btcTextView,eurTextView,gbpTextView,hkdTextView,inrTextView,jpyTextView,myrTextView,usdTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}