package com.example.shareble;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity3_main);

        Button btnMenu = findViewById(R.id.btnMenu);
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendPDF();
            }
        });


        Button btnVisitingCard = findViewById(R.id.btnVisitingCard);
        btnVisitingCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendVisitingCard();
            }
        });

        Button btnPaymentQR = findViewById(R.id.btnPaymentQR);
        btnPaymentQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendPaymentQR();
            }
        });

        Button btnBankDetails = findViewById(R.id.btnBankDetails);
        btnBankDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendBankDetails();
            }
        });

        Button btnAddress = findViewById(R.id.btnAddress);
        btnAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendAddress();
            }
        });

        Button btnGoogleMapLocation = findViewById(R.id.btnGoogleMapLocation);
        btnGoogleMapLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendGoogleMapLocation();
            }
        });





    }

    private void sendPDF() {
        // Get the PDF file's resource ID
        int resourceId = R.raw.menu;

        // Create a File object from the PDF file in the raw folder
        File pdfFile = new File(getCacheDir(), "menu.pdf");
        pdfFile.getParentFile().mkdirs();

        // Copy the PDF file from the raw folder to the File object
        FileUtils.copyRawResourceToFile(this, resourceId, pdfFile);

        // Create the content URI using FileProvider
        Uri contentUri = FileProvider.getUriForFile(this, "com.example.shareble.fileprovider", pdfFile);

        // Create the intent
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("application/pdf");
        intent.putExtra(Intent.EXTRA_STREAM, contentUri);

        // Add the FLAG_GRANT_READ_URI_PERMISSION flag
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        // Check if there are any apps that can handle the intent
        PackageManager packageManager = getPackageManager();
        List<ResolveInfo> activities = packageManager.queryIntentActivities(intent, 0);
        boolean isIntentSafe = activities.size() > 0;

        // Launch the intent if there are apps available, otherwise display a message
        if (isIntentSafe) {
            startActivity(Intent.createChooser(intent, "Send PDF using:"));
        } else {
            Toast.makeText(this, "No apps can handle this request.", Toast.LENGTH_SHORT).show();
        }
    }

    private void sendVisitingCard() {
        // Get the JPEG image file's resource ID
        int resourceId = R.drawable.visiting_card;

        // Create a File object from the JPEG image file in the drawable folder
        File imageFile = new File(getCacheDir(), "visiting_card.jpg");
        imageFile.getParentFile().mkdirs();

        // Copy the JPEG image file from the drawable folder to the File object
        FileUtils.copyDrawableToFile(this, resourceId, imageFile);

        // Create the content URI using FileProvider
        Uri contentUri = FileProvider.getUriForFile(this, "com.example.shareble.fileprovider", imageFile);

        // Create the intent
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("image/jpeg");
        intent.putExtra(Intent.EXTRA_STREAM, contentUri);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        // Check if there are any apps that can handle the intent
        PackageManager packageManager = getPackageManager();
        List<ResolveInfo> activities = packageManager.queryIntentActivities(intent, 0);
        boolean isIntentSafe = activities.size() > 0;

        // Launch the intent if there are apps available, otherwise display a message
        if (isIntentSafe) {
            startActivity(Intent.createChooser(intent, "Send Visiting Card using:"));
        } else {
            Toast.makeText(this, "No apps can handle this request.", Toast.LENGTH_SHORT).show();
        }
    }

    private void sendPaymentQR() {
        // Get the JPEG image file's resource ID
        int resourceId = R.drawable.payment_qr;

        // Create a File object from the JPEG image file in the drawable folder
        File imageFile = new File(getCacheDir(), "payment_qr.jpg");
        imageFile.getParentFile().mkdirs();

        // Copy the JPEG image file from the drawable folder to the File object
        FileUtils.copyDrawableToFile(this, resourceId, imageFile);

        // Create the content URI using FileProvider
        Uri contentUri = FileProvider.getUriForFile(this, "com.example.shareble.fileprovider", imageFile);

        // Create the intent
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("image/jpeg");
        intent.putExtra(Intent.EXTRA_STREAM, contentUri);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        // Check if there are any apps that can handle the intent
        PackageManager packageManager = getPackageManager();
        List<ResolveInfo> activities = packageManager.queryIntentActivities(intent, 0);
        boolean isIntentSafe = activities.size() > 0;

        // Launch the intent if there are apps available, otherwise display a message
        if (isIntentSafe) {
            startActivity(Intent.createChooser(intent, "Send Payment QR using:"));
        } else {
            Toast.makeText(this, "No apps can handle this request.", Toast.LENGTH_SHORT).show();
        }
    }

    private void sendBankDetails() {
        // Bank details text
        String bankDetailsText = "Nayana Redkar\nCanara Bank - Vartak Nagar Branch\nSaving A/c No: 1086101056439\nIFSC Code: CNRB0001086";

        // Create the intent
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, bankDetailsText);

        // Check if there are any apps that can handle the intent
        PackageManager packageManager = getPackageManager();
        List<ResolveInfo> activities = packageManager.queryIntentActivities(intent, 0);
        boolean isIntentSafe = activities.size() > 0;

        // Launch the intent if there are apps available, otherwise display a message
        if (isIntentSafe) {
            startActivity(Intent.createChooser(intent, "Send Bank Details using:"));
        } else {
            Toast.makeText(this, "No apps can handle this request.", Toast.LENGTH_SHORT).show();
        }
    }

    private void sendAddress() {
        // Address text
        String addressText = "Gala No. 12, Opposite Abhyudaya Co-operative Bank, Near Lokmanya TMT Depot, Mhada M.S.B, Savarkar Nagar, Thane (W), 400606";

        // Create the intent
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, addressText);

        // Check if there are any apps that can handle the intent
        PackageManager packageManager = getPackageManager();
        List<ResolveInfo> activities = packageManager.queryIntentActivities(intent, 0);
        boolean isIntentSafe = activities.size() > 0;

        // Launch the intent if there are apps available, otherwise display a message
        if (isIntentSafe) {
            startActivity(Intent.createChooser(intent, "Send Address using:"));
        } else {
            Toast.makeText(this, "No apps can handle this request.", Toast.LENGTH_SHORT).show();
        }
    }

    private void sendGoogleMapLocation() {
        // Google Map location link
        String locationLink = "https://maps.app.goo.gl/Hb789cv4PYFxJjRT7";

        // Create the intent
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, locationLink);

        // Check if there are any apps that can handle the intent
        PackageManager packageManager = getPackageManager();
        List<ResolveInfo> activities = packageManager.queryIntentActivities(intent, 0);
        boolean isIntentSafe = activities.size() > 0;

        // Launch the intent if there are apps available, otherwise display a message
        if (isIntentSafe) {
            startActivity(Intent.createChooser(intent, "Send Google Map Location using:"));
        } else {
            Toast.makeText(this, "No apps can handle this request.", Toast.LENGTH_SHORT).show();
        }
    }
}
