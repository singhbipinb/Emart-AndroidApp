package com.emart.emartindia;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.bumptech.glide.Glide;

public class SubCategories extends BaseNavigation {


    ScrollView scsmart, sccomp, scgadget, schome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.transition.fadein, R.transition.fadeout);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.activity_sub_categories, null, false);
        frameLayout.addView(view);


        scsmart = findViewById(R.id.mobilesubcat);
        sccomp = findViewById(R.id.computersubcat);
        scgadget = findViewById(R.id.gadgetssubcat);
        schome = findViewById(R.id.appliancessubcat);

        Intent intent = getIntent();

        String category = intent.getStringExtra("category");
        setTitle(category);

        switch (category) {

            case "Smartphones":
                Glide.with(this).load("https://image.freepik.com/free-vector/smartphone-with-big-data-stream-isometric-banner_107791-258.jpg").into((ImageView) findViewById(R.id.imgsmartphone));

                Glide.with(this).load("https://storage.sg.content-cdn.io/in-resources/7f703506-689d-4b4e-b482-c32d60769d33/Images/userimages/charger-banner.jpg").into((ImageView) findViewById(R.id.imgtravel));

                Glide.with(this).load("https://storage.sg.content-cdn.io/in-resources/7f703506-689d-4b4e-b482-c32d60769d33/Images/userimages/Web%20Banner/Power-Banks-with-best-features-online-at-conekt.jpg").into((ImageView) findViewById(R.id.imgpower));

                Glide.with(this).load("https://images-eu.ssl-images-amazon.com/images/G/31/img20/Wireless/WLA/April/Cases/Cat_Page/PC/Banner_2").into((ImageView) findViewById(R.id.imgcovers));

                scsmart.setVisibility(View.VISIBLE);
                break;

            case "Computers":

                Glide.with(this).load("https://www.supremetechno.com/site1/wp-content/uploads/2019/03/MSI-RTX-Laptop-Banner_1200x600-2.jpg").into((ImageView) findViewById(R.id.imglaptop));

                Glide.with(this).load("https://cwsmgmt.corsair.com/media/hybris/tlc/mousepads/TLC_mousepads_banner.jpg").into((ImageView) findViewById(R.id.imgmouse));

                Glide.with(this).load("https://cdn.sabrent.com/uploads/EC-HDD2-Banner-4-scaled.jpg").into((ImageView) findViewById(R.id.imgstorage));

                Glide.with(this).load("https://www.fiercepc.co.uk/media/wysiwyg/PC-BUNDLE-BANNER.jpg").into((ImageView) findViewById(R.id.imgpcp));

                Glide.with(this).load("https://www.wisair.com/wp-content/uploads/2020/11/Bitedefender-main-banner-txt.jpg").into((ImageView) findViewById(R.id.imgsoftware));

                sccomp.setVisibility(View.VISIBLE);
                break;

            case "Gadgets":
                Glide.with(this).load("https://www.yellowslice.tech/projs/croma-new-buying-guide/assets/img/wearables/wearable-device-banner1.jpg").into((ImageView) findViewById(R.id.imgwearable));

                Glide.with(this).load("https://media.officedepot.com/image/upload/f_auto,q_auto/coremedia/resource/blob/224460/8096326d2b8c9c7ffc148d766f1579da/4118-948x326-smart-world-banner-data-cm-header--1--data.jpg").into((ImageView) findViewById(R.id.imgsmarthome));

                Glide.with(this).load("https://nikonrumors.com/wp-content/uploads/2016/01/2016-Nikon-DSLR-camera-line.jpg").into((ImageView) findViewById(R.id.imgcamera));

                Glide.with(this).load("https://www.reliancedigital.in/wp-content/uploads/2017/11/tablet_banner3.jpg").into((ImageView) findViewById(R.id.imgtablets));

                Glide.with(this).load("https://www.lg.com/in/images/plp-b2c/in-homeentertainment-hero-6-d.jpg").into((ImageView) findViewById(R.id.imgaudio));

                scgadget.setVisibility(View.VISIBLE);
                break;

            case "HomeAppliances":

                Glide.with(this).load("https://www.topthingz.in/wp-content/uploads/2020/03/ledtv_banner_yayvo.jpg").into((ImageView) findViewById(R.id.imgtv));
                Glide.with(this).load("http://www.enfinityelectronics.com/thumbnail/crop/1900/330/refrigerators/banner.jpg").into((ImageView) findViewById(R.id.imgrefri));
                Glide.with(this).load("https://www.lg.com/in/images/plp-b2c/in-washingmachines-hero-2-d.jpg").into((ImageView) findViewById(R.id.imgwashing));
                Glide.with(this).load("https://www.shopperszion.com/home.jpeg").into((ImageView) findViewById(R.id.imgkitchen));
                Glide.with(this).load("https://kginternational.com/wp-content/uploads/2019/03/Home-Appliances-Banner-option-6-Rev.jpg").into((ImageView) findViewById(R.id.imghomeapp));


                schome.setVisibility(View.VISIBLE);
                break;


        }


    }

    public void toSmartphones(View view) {

        Intent intent = new Intent(this, Browse.class);
        intent.putExtra("subcategory", "Smartphones");
        startActivity(intent);
    }

    public void toTravelAdapter(View view) {
        Intent intent = new Intent(this, Browse.class);
        intent.putExtra("subcategory", "Travel Adapter");
        startActivity(intent);
    }

    public void toPowerBanks(View view) {
        Intent intent = new Intent(this, Browse.class);
        intent.putExtra("subcategory", "Power Banks");
        startActivity(intent);
    }

    public void toMouse(View view) {
        Intent intent = new Intent(this, Browse.class);
        intent.putExtra("subcategory", "Mouse and Keyboard");
        startActivity(intent);
    }

    public void toLaptop(View view) {
        Intent intent = new Intent(this, Browse.class);
        intent.putExtra("subcategory", "Laptop");
        startActivity(intent);
    }

    public void toCovers(View view) {
        Intent intent = new Intent(this, Browse.class);
        intent.putExtra("subcategory", "Cover and Cases");
        startActivity(intent);
    }

    public void toStorage(View view) {
        Intent intent = new Intent(this, Browse.class);
        intent.putExtra("subcategory", "Storage Devices");
        startActivity(intent);
    }

    public void toPCP(View view) {
        Intent intent = new Intent(this, Browse.class);
        intent.putExtra("subcategory", "PC Peripherals");
        startActivity(intent);
    }

    public void toSoftware(View view) {
        Intent intent = new Intent(this, Browse.class);
        intent.putExtra("subcategory", "Software");
        startActivity(intent);
    }

    public void toWearbles(View view) {
        Intent intent = new Intent(this, Browse.class);
        intent.putExtra("subcategory", "Smart Wearables");
        startActivity(intent);
    }

    public void toSmartHome(View view) {
        Intent intent = new Intent(this, Browse.class);
        intent.putExtra("subcategory", "Smart Homes");
        startActivity(intent);
    }

    public void toCamera(View view) {
        Intent intent = new Intent(this, Browse.class);
        intent.putExtra("subcategory", "Camera and Accessories");
        startActivity(intent);
    }

    public void toTablets(View view) {
        Intent intent = new Intent(this, Browse.class);
        intent.putExtra("subcategory", "Tablets");
        startActivity(intent);
    }

    public void toAudio(View view) {
        Intent intent = new Intent(this, Browse.class);
        intent.putExtra("subcategory", "Audio Devices");
        startActivity(intent);
    }

    public void toTelevision(View view) {
        Intent intent = new Intent(this, Browse.class);
        intent.putExtra("subcategory", "Televisions");
        startActivity(intent);
    }

    public void toRefrigerator(View view) {
        Intent intent = new Intent(this, Browse.class);
        intent.putExtra("subcategory", "Refrigerators");
        startActivity(intent);
    }

    public void toWashing(View view) {
        Intent intent = new Intent(this, Browse.class);
        intent.putExtra("subcategory", "Washing Machines");
        startActivity(intent);
    }

    public void toKitchen(View view) {
        Intent intent = new Intent(this, Browse.class);
        intent.putExtra("subcategory", "Kitchen Appliances");
        startActivity(intent);
    }

    public void toHomeApp(View view) {
        Intent intent = new Intent(this, Browse.class);
        intent.putExtra("subcategory", "Home Appliances");
        startActivity(intent);
    }
}