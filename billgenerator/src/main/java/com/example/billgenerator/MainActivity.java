package com.example.billgenerator;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    EditText editTextDate, editTextTo, editTextParticulars, editTextRate, editTextAmount;
    Button submitButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity1_main);

        editTextDate = findViewById(R.id.editTextDate);
        editTextTo = findViewById(R.id.editTextTo);
        editTextParticulars = findViewById(R.id.editTextParticulars);
        editTextRate = findViewById(R.id.editTextRate);
        editTextAmount = findViewById(R.id.editTextAmount);
        submitButton = findViewById(R.id.submitButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(editTextDate.getText()) || TextUtils.isEmpty(editTextTo.getText()) || TextUtils.isEmpty(editTextParticulars.getText()) || TextUtils.isEmpty(editTextRate.getText()) || TextUtils.isEmpty(editTextAmount.getText())) {
                    Toast.makeText(getApplicationContext(), "Please fill everything", Toast.LENGTH_SHORT).show();
                    return;
                }

                String date = editTextDate.getText().toString();
                String to = editTextTo.getText().toString();
                String particulars = editTextParticulars.getText().toString();
                String rate = editTextRate.getText().toString();
                String amount = editTextAmount.getText().toString();

                try {
                    createPdf(date, to, particulars, rate, amount);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }

            }
        });

    }

    private void createPdf(String date, String to, String particulars, String rate, String amount) throws FileNotFoundException {
        String pdfPath = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.FROYO) {
            pdfPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
        }
        File billFolder = new File(pdfPath, "Bill");
        billFolder.mkdirs(); // Create the "Bill" folder if it doesn't exist

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String fileName = "bill_" + timeStamp + ".pdf";
        File file = new File(billFolder, fileName);

        OutputStream outputStream = new FileOutputStream(file);

        // Rest of your code for creating the PDF and writing data to it





    PdfWriter writer = new PdfWriter(new FileOutputStream(file));
        PdfDocument pdfDocument = new PdfDocument(writer);
        Document document = new Document(pdfDocument);

        pdfDocument.setDefaultPageSize(PageSize.A6);
        document.setMargins(0, 0, 0, 0);


        Drawable d = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            d = getDrawable(R.drawable.img);
        }

        Bitmap bitmap = ((BitmapDrawable) d).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] bitmapData = stream.toByteArray();

        ImageData imageData = ImageDataFactory.create(bitmapData);
        Image image = new Image(imageData);

        float targetWidth = 120;  // Adjust this value as needed
        float targetHeight = 120; // Adjust this value as needed

        image.scaleToFit(targetWidth, targetHeight);

        image.setHorizontalAlignment(HorizontalAlignment.CENTER);



        Paragraph address = new Paragraph("Gala No. 12, Opposite Abhyudaya Co-operative Bank, Near Lokmanya TMT Depot, Mhada M.S.B, Savarkar Nagar, Thane (W), 400606").setFontSize(8).setTextAlignment(TextAlignment.CENTER);
        Paragraph phone = new Paragraph("9323968697/9819769705").setFontSize(8).setTextAlignment(TextAlignment.CENTER);
        Paragraph to1 = new Paragraph().setFontSize(8).setTextAlignment(TextAlignment.LEFT).add("To:\n")
                .add(to);;;

        Paragraph pahunchar = new Paragraph("For Panhunchar,").setFontSize(8).setTextAlignment(TextAlignment.RIGHT);

        float[] width = {125f, 125f};
        Table table = new Table(width);
        table.setHorizontalAlignment(HorizontalAlignment.CENTER);

        table.addCell(new Cell().add(new Paragraph("Date")));
        table.addCell(new Cell().add(new Paragraph(date)));


        table.addCell(new Cell().add(new Paragraph("Particulars")));
        table.addCell(new Cell().add(new Paragraph(particulars)));

        table.addCell(new Cell().add(new Paragraph("Rate")));
        table.addCell(new Cell().add(new Paragraph(rate)));

        table.addCell(new Cell().add(new Paragraph("Total Amount")));
        table.addCell(new Cell().add(new Paragraph(amount)));


        document.add(phone);
        document.add(image);
        document.add(address);
        document.add(to1);
        document.add(table);
        document.add(pahunchar);

        document.close();
        Toast.makeText(this, "PDF saved to Downloads", Toast.LENGTH_SHORT).show();


    }
}