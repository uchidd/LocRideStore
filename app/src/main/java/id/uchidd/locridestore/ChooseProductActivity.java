package id.uchidd.locridestore;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChooseProductActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;

    public static final String preference = "preferenceBundle";

    public static final String safeBrand = "brandKey";

    String saveData, brand;

    @BindView(R.id.ivBackCB)
    ImageView ivBackCB;
    @BindView(R.id.rvList)
    RecyclerView rvList;

    private ArrayList<String> textName;
    private ArrayList<String> textType;
    private ArrayList<String> textColor;
    private ArrayList<String> textPrice;
    private ArrayList<String> textLink;
    private ArrayList<Integer> imageList1;
    private ArrayList<Integer> imageList2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_product);
        ButterKnife.bind(this);

        sharedPreferences = getSharedPreferences(preference, Context.MODE_PRIVATE);

        ivBackCB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChooseProductActivity.this, ChooseBrandActivity.class));
                finishAffinity();
                finish();
            }
        });

        Bundle getData = getIntent().getExtras();
        brand = getData.getString("BRAND");

        autoSaveBrandinOnCreate();

        if (sharedPreferences.contains(safeBrand)) {
            saveData = sharedPreferences.getString(safeBrand, "");
        }


        if (saveData.equals("AGLXY")) {
            initItemViewA();
        } else if (saveData.equals("BILLIONAIRIES")) {
            initItemViewB();
        } else if (saveData.equals("COSMONAUTS")) {
            initItemViewC();
        }


        hideNavigationBar();
    }

    public void autoSaveBrandinOnCreate() {
        String getBrand = brand;

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(safeBrand, getBrand);

        editor.commit();

        //Toast.makeText(this, "get " + getBrand, Toast.LENGTH_SHORT).show();
    }

    private void initItemViewA() {

        final RecyclerView rvList = (RecyclerView) findViewById(R.id.rvList);
        rvList.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rvList.setLayoutManager(layoutManager);

        textName = new ArrayList<>();
        textName.add("KEEP YOUR HOOD ON & KEEP ON MOVIN");
        textName.add("DON’T LET THE STRAITS SEPARATE");
        textName.add("PLANET MAIN SOURCE 001 T");
        textName.add("GOOD VIBES SINCE ∞ 008 LS");
        textName.add("WHATEVER IT TAKES 008");

        textType = new ArrayList<>();
        textType.add("Hoodie");
        textType.add("Coach Jacket");
        textType.add("T-Shirt");
        textType.add("T-Shirt Long Sleeves");
        textType.add("T-Shirt");

        textColor = new ArrayList<>();
        textColor.add("Black");
        textColor.add("Black");
        textColor.add("Pink");
        textColor.add("Black");
        textColor.add("Black");

        textPrice = new ArrayList<>();
        textPrice.add("IDR 550.000");
        textPrice.add("IDR 650.000");
        textPrice.add("IDR 300.000");
        textPrice.add("IDR 350.000");
        textPrice.add("IDR 300.000");

        textLink = new ArrayList<>();
        textLink.add("https://aglxy.com/shop/keep-your-hood-on-keep-on-movin-black");
        textLink.add("https://aglxy.com/shop/dont-let-the-straits-separate-coach-jacket-black");
        textLink.add("https://aglxy.com/shop/planet-main-source-001-pink");
        textLink.add("https://aglxy.com/shop/good-vibes-since-%E2%88%9E-008-ls-black");
        textLink.add("https://aglxy.com/shop/whatever-it-takes-008-black");

        imageList1 = new ArrayList<>();
        imageList1.add(R.drawable.a1a);
        imageList1.add(R.drawable.a2a);
        imageList1.add(R.drawable.a3a);
        imageList1.add(R.drawable.a4a);
        imageList1.add(R.drawable.a5a);

        imageList2 = new ArrayList<>();
        imageList2.add(R.drawable.a1b);
        imageList2.add(R.drawable.a2b);
        imageList2.add(R.drawable.a3b);
        imageList2.add(R.drawable.a4b);
        imageList2.add(R.drawable.a5b);

        RecyclerView.Adapter adapter = new AdapterList(textName, textType, textColor, textPrice, imageList1);
        rvList.setAdapter(adapter);

        rvList.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {

            GestureDetector gestureDetector = new GestureDetector(getApplicationContext(), new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }
            });

            @Override
            public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
                View child = rvList.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
                if (child != null && gestureDetector.onTouchEvent(motionEvent)) {
                    int position = rvList.getChildAdapterPosition(child);

                    String brand, text1, text2, text3, text4, link;
                    Integer image1, image2;

                    brand = "AGLXY";
                    text1 = textName.get(position);
                    text2 = textType.get(position);
                    text3 = textColor.get(position);
                    text4 = textPrice.get(position);
                    image1 = imageList1.get(position);
                    image2 = imageList2.get(position);
                    link = textLink.get(position);

                    pushData(brand, text1, text2, text3, text4, image1, image2, link);

                }

                return false;
            }


            @Override
            public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean b) {

            }
        });

    }

    private void initItemViewB() {

        final RecyclerView rvList = (RecyclerView) findViewById(R.id.rvList);
        rvList.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rvList.setLayoutManager(layoutManager);

        textName = new ArrayList<>();
        textName.add("RICHBOY");
        textName.add("MAKE MONEY NOT LOVE");
        textName.add("SS1 BASIC");
        textName.add("SS1 BILLPRO");
        textName.add("SS1 BASIC");

        textType = new ArrayList<>();
        textType.add("T-Shirt");
        textType.add("T-Shirt");
        textType.add("T-Shirt Long Sleeves");
        textType.add("Coach Jacket");
        textType.add("T-Shirt Long Sleeves");

        textColor = new ArrayList<>();
        textColor.add("White");
        textColor.add("Black");
        textColor.add("Black");
        textColor.add("Black");
        textColor.add("White");

        textPrice = new ArrayList<>();
        textPrice.add("IDR 220.000");
        textPrice.add("IDR 220.000");
        textPrice.add("IDR 240.000");
        textPrice.add("IDR 500.000");
        textPrice.add("IDR 240.000");

        textLink = new ArrayList<>();
        textLink.add("https://www.billionairesproject.com/products/billionaires-project-t-shirt---richboy");
        textLink.add("https://www.billionairesproject.com/products/billionaires-project-t-shirt---make-money-not-love--black-");
        textLink.add("https://www.billionairesproject.com/products/billionaires-project-t-shirt---basic-longsleeve-black");
        textLink.add("https://www.billionairesproject.com/products/billionaires-project-outer---ss1-billpro-coach-jacket");
        textLink.add("https://www.billionairesproject.com/products/billionaires-project-t-shirt---ss1-basic-longsleeve--white-");

        imageList1 = new ArrayList<>();
        imageList1.add(R.drawable.b1a);
        imageList1.add(R.drawable.b2a);
        imageList1.add(R.drawable.b3a);
        imageList1.add(R.drawable.b4a);
        imageList1.add(R.drawable.b5a);

        imageList2 = new ArrayList<>();
        imageList2.add(R.drawable.b1b);
        imageList2.add(R.drawable.b2b);
        imageList2.add(R.drawable.b3b);
        imageList2.add(R.drawable.b4b);
        imageList2.add(R.drawable.b5b);

        RecyclerView.Adapter adapter = new AdapterList(textName, textType, textColor, textPrice, imageList1);
        rvList.setAdapter(adapter);

        rvList.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {

            GestureDetector gestureDetector = new GestureDetector(getApplicationContext(), new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }
            });

            @Override
            public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
                View child = rvList.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
                if (child != null && gestureDetector.onTouchEvent(motionEvent)) {
                    int position = rvList.getChildAdapterPosition(child);

                    String brand, text1, text2, text3, text4, link;
                    Integer image1, image2;

                    brand = "Billionaire's Project";
                    text1 = textName.get(position);
                    text2 = textType.get(position);
                    text3 = textColor.get(position);
                    text4 = textPrice.get(position);
                    image1 = imageList1.get(position);
                    image2 = imageList2.get(position);
                    link = textLink.get(position);

                    pushData(brand, text1, text2, text3, text4, image1, image2, link);

                }

                return false;
            }


            @Override
            public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean b) {

            }
        });

    }

    private void initItemViewC(){

        final RecyclerView rvList = (RecyclerView) findViewById(R.id.rvList);
        rvList.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rvList.setLayoutManager(layoutManager);

        textName = new ArrayList<>();
        textName.add("GOLD BLESS YOU");
        textName.add("SPACE CLUB");
        textName.add("WAR OF THE WORLD");
        textName.add("MILLIONAIRE");
        textName.add("SPACE SUSHI");

        textType = new ArrayList<>();
        textType.add("T-Shirt");
        textType.add("T-Shirt");
        textType.add("Coach Jacket");
        textType.add("T-Shirt");
        textType.add("T-Shirt");


        textColor = new ArrayList<>();
        textColor.add("Black");
        textColor.add("Black");
        textColor.add("Black");
        textColor.add("White");
        textColor.add("Black");

        textPrice = new ArrayList<>();
        textPrice.add("IDR 840.000");
        textPrice.add("IDR 840.000");
        textPrice.add("IDR 1.190.000");
        textPrice.add("IDR 840.000");
        textPrice.add("IDR 840.000");

        textLink = new ArrayList<>();
        textLink.add("https://wearecosmonauts.com/collections/tees/products/gold-bless-you");
        textLink.add("https://wearecosmonauts.com/collections/tees/products/space-club");
        textLink.add("https://wearecosmonauts.com/collections/jacket/products/war-of-the-worlds");
        textLink.add("https://wearecosmonauts.com/collections/tees/products/millionaire");
        textLink.add("https://wearecosmonauts.com/collections/tees/products/space-sushi");

        imageList1 = new ArrayList<>();
        imageList1.add(R.drawable.c1a);
        imageList1.add(R.drawable.c2a);
        imageList1.add(R.drawable.c3a);
        imageList1.add(R.drawable.c4a);
        imageList1.add(R.drawable.c5a);

        imageList2 = new ArrayList<>();
        imageList2.add(R.drawable.c1b);
        imageList2.add(R.drawable.c2b);
        imageList2.add(R.drawable.c3b);
        imageList2.add(R.drawable.c4b);
        imageList2.add(R.drawable.c5b);

        RecyclerView.Adapter adapter = new AdapterList(textName, textType, textColor, textPrice, imageList1);
        rvList.setAdapter(adapter);

        rvList.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {

            GestureDetector gestureDetector = new GestureDetector(getApplicationContext(), new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }
            });

            @Override
            public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
                View child = rvList.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
                if (child != null && gestureDetector.onTouchEvent(motionEvent)) {
                    int position = rvList.getChildAdapterPosition(child);

                    String brand, text1, text2, text3, text4, link;
                    Integer image1, image2;

                    brand = "Cosmonauts";
                    text1 = textName.get(position);
                    text2 = textType.get(position);
                    text3 = textColor.get(position);
                    text4 = textPrice.get(position);
                    image1 = imageList1.get(position);
                    image2 = imageList2.get(position);
                    link = textLink.get(position);

                    pushData(brand, text1, text2, text3, text4, image1, image2, link);

                }

                return false;
            }


            @Override
            public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean b) {

            }
        });

    }

    private void pushData(String brand, String text1, String text2, String text3, String text4, Integer image1, Integer image2, String link) {

        Bundle bundle = new Bundle();
        bundle.putString("BRAND", brand);
        bundle.putString("NAME", text1);
        bundle.putString("TYPE", text2);
        bundle.putString("COLOR", text3);
        bundle.putString("PRICE", text4);
        bundle.putInt("IMAGE1", image1);
        bundle.putInt("IMAGE2", image2);
        bundle.putString("LINK", link);

        Intent goToDetailProduk = new Intent(this, PreviewActivity.class);
        goToDetailProduk.putExtras(bundle);
        startActivity(goToDetailProduk);
        return;

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(ChooseProductActivity.this, ChooseBrandActivity.class));
        finishAffinity();
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
