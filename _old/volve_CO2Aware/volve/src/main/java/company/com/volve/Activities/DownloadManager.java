package company.com.volve.Activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.StrictMode;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.lang.reflect.Method;

import static android.app.DownloadManager.*;
import static com.facebook.FacebookSdk.getApplicationContext;
public class DownloadManager {
    private static final String pdfName = "WeeklyPDF.pdf"; //filename on server NB: NEEDS TO REMAIN CONSTANT
    private static String pdfUri = "https://firebasestorage.googleapis.com/v0/b/volve-ucd.appspot.com/o/" + pdfName + "?alt=media&token=e0c21fde-3c09-4fb7-9963-cf65785ac7e8";
    private static String action = null;
    private static FirebaseStorage storage = FirebaseStorage.getInstance();
    private static StorageReference gsReference = storage.getReferenceFromUrl("gs://volve-ucd.appspot.com/");

    public static void pdfRequestHandle() {
        BroadcastReceiver broadcast;

        gsReference.child(pdfName).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                //Requirements to connect to firebase and download pdf.
                Request request = new Request(Uri.parse(pdfUri));
                request.setTitle("WeeklyPDf.pdf");
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "/" + "WeeklyPDf.pdf");
                request.setDescription("Download in progress!");
                request.setMimeType("pdf");
                android.app.DownloadManager downloadManager = (android.app.DownloadManager) getApplicationContext().getSystemService(Context.DOWNLOAD_SERVICE);
                new File("");

                assert downloadManager != null;
                downloadManager.enqueue(request);

            }
        });

        broadcast = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (Build.VERSION.SDK_INT >= 24) {
                    try {
                        @SuppressWarnings("JavaReflectionMemberAccess") 
                        Method m = StrictMode.class.getMethod("disableDeathOnFileUriExposure");
                        m.invoke(null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                openPDF();
            }
        };

        Context context = getApplicationContext();
        context.registerReceiver(broadcast, new IntentFilter(ACTION_DOWNLOAD_COMPLETE));


    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private static void openPDF() {

        //get Download folder directory
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/WeeklyPDf.pdf");

        Intent openPDFIntent = new Intent("com.adobe.reader");
        openPDFIntent.setType("application/pdf");
        openPDFIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        openPDFIntent.setAction(Intent.ACTION_VIEW);
        Uri uri = Uri.fromFile(file);
        openPDFIntent.setDataAndType(uri, "application/pdf");

        if (!ACTION_DOWNLOAD_COMPLETE.equals(action))
            getApplicationContext().startActivity(openPDFIntent);
    }
}