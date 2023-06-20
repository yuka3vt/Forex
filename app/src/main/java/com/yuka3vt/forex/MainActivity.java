package com.yuka3vt.forex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.text.DecimalFormat;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    private ProgressBar loadingProgressBar;
    private SwipeRefreshLayout swipeRefreshLayout1;
    private TextView audTextView,bnbTextView,btcTextView,eurTextView,gbpTextView,hkdTextView,inrTextView,jpyTextView,myrTextView,usdTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swipeRefreshLayout1 = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout1);
        audTextView = (TextView) findViewById(R.id.audTextView);
        bnbTextView = (TextView) findViewById(R.id.bnbTextView);
        btcTextView = (TextView) findViewById(R.id.btcTextView);
        eurTextView = (TextView) findViewById(R.id.eurTextView);
        gbpTextView = (TextView) findViewById(R.id.gbpTextView);
        hkdTextView = (TextView) findViewById(R.id.hkdTextView);
        inrTextView = (TextView) findViewById(R.id.inrTextView);
        jpyTextView = (TextView) findViewById(R.id.jpyTextView);
        myrTextView = (TextView) findViewById(R.id.myrTextView);
        usdTextView = (TextView) findViewById(R.id.usdTextView);
        loadingProgressBar = (ProgressBar) findViewById(R.id.loadingProgessBar);

        initSwipeRefreshLayout();
        initForex();
    }
    private void initSwipeRefreshLayout(){
        swipeRefreshLayout1.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initForex();

                swipeRefreshLayout1.setRefreshing(false);
            }
        });
    }

    public String formatnumber(double number, String format){
        DecimalFormat decimalFormat = new DecimalFormat(format);
        return decimalFormat.format(number);
    }

    private void initForex(){
        loadingProgressBar.setVisibility(TextView.VISIBLE);

        String url = "https://openexchangerates.org/api/latest.json?app_id=852516a43f8745f590f92a63c8ba86db";

        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Gson gson = new Gson();
                RootModel rootModel = gson.fromJson(new String(responseBody),RootModel.class);
                RatesModel ratesModel = rootModel.getRatesModel();

                double aud = ratesModel.getIDR() / ratesModel.getAUD();
                double bnb = ratesModel.getIDR() / ratesModel.getBNB();
                double btc = ratesModel.getIDR() / ratesModel.getBTC();
                double eur = ratesModel.getIDR() / ratesModel.getEUR();
                double gbp = ratesModel.getIDR() / ratesModel.getGBP();
                double hkd = ratesModel.getIDR() / ratesModel.getHKD();
                double inr = ratesModel.getIDR() / ratesModel.getINR();
                double jpy = ratesModel.getIDR() / ratesModel.getJPY();
                double myr = ratesModel.getIDR() / ratesModel.getMYR();
                double idr = ratesModel.getIDR();

                audTextView.setText(formatnumber(aud,"###,##0.00"));
                bnbTextView.setText(formatnumber(bnb,"###,##0.00"));
                btcTextView.setText(formatnumber(btc,"###,##0.00"));
                eurTextView.setText(formatnumber(eur,"###,##0.00"));
                gbpTextView.setText(formatnumber(gbp,"###,##0.00"));
                hkdTextView.setText(formatnumber(hkd,"###,##0.00"));
                inrTextView.setText(formatnumber(inr,"###,##0.00"));
                jpyTextView.setText(formatnumber(jpy,"###,##0.00"));
                myrTextView.setText(formatnumber(myr,"###,##0.00"));
                usdTextView.setText(formatnumber(idr,"###,##0.00"));

                loadingProgressBar.setVisibility(TextView.INVISIBLE);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();

                loadingProgressBar.setVisibility(TextView.INVISIBLE);
            }
        });
    }
}