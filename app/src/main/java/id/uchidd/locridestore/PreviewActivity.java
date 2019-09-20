package id.uchidd.locridestore;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PreviewActivity extends AppCompatActivity {

    @BindView(R.id.ivBackCP)
    ImageView ivBackCP;
    @BindView(R.id.ivPreview)
    ImageView ivPreview;
    @BindView(R.id.cvFront)
    CardView cvFront;
    @BindView(R.id.cvBack)
    CardView cvBack;
    @BindView(R.id.tvPreview1)
    TextView tvPreview1;
    @BindView(R.id.tvPreview2)
    TextView tvPreview2;
    @BindView(R.id.tvPreview3)
    TextView tvPreview3;
    @BindView(R.id.tvPreview4)
    TextView tvPreview4;
    @BindView(R.id.tvPreview5)
    TextView tvPreview5;
    @BindView(R.id.cvBuyNow)
    CardView cvBuyNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);
        ButterKnife.bind(this);

        ivBackCP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Bundle getData = getIntent().getExtras();
        String getBrand = getData.getString("BRAND");
        String getName = getData.getString("NAME");
        String getType = getData.getString("TYPE");
        String getColor = getData.getString("COLOR");
        String getPrice = getData.getString("PRICE");
        final Integer getImage1 = getData.getInt("IMAGE1");
        final Integer getImage2 = getData.getInt("IMAGE2");
        final String getLink = getData.getString("LINK");

        Picasso.get().load(getImage1).into(ivPreview);
        tvPreview1.setText(getBrand);
        tvPreview2.setText(getName);
        tvPreview3.setText(getType);
        tvPreview4.setText(getColor);
        tvPreview5.setText(getPrice);

        cvFront.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Picasso.get().load(getImage1).into(ivPreview);
            }
        });

        cvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Picasso.get().load(getImage2).into(ivPreview);
            }
        });

        cvBuyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(PreviewActivity.this);
                builder.setTitle("Buy Product")
                        .setMessage("If you want to buy this product, you can buy it on their website.\nDo you want to visit their website?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String url = getLink;
                                Intent i = new Intent(Intent.ACTION_VIEW);
                                i.setData(Uri.parse(url));
                                startActivity(i);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        hideNavigationBar();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //startActivity(new Intent(PreviewActivity.this, ChooseProductActivity.class));
        //finishAffinity();
        finish();
    }

    public void hideNavigationBar() {

        this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

    }

}
