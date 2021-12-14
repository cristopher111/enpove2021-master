package com.example.ricindigus.enpove2021.activities;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.ricindigus.enpove2021.R;
import com.example.ricindigus.enpove2021.util.PermissionUtils;

import static com.example.ricindigus.enpove2021.util.PermissionUtils.checkInternet;

public class CroquisActivity extends AppCompatActivity {
    DownloadManager downloadManager;
    Toolbar toolbar;
    WebView webView;
    String linkPDF = "https://drive.google.com/file/d/1G5EYfD5D_HcUlDi0oQo8VbFZqFEwxAQv/view?usp=sharing";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_croquis);

        webView = (WebView) findViewById(R.id.wvBrowser);

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Cargando...");
        progressDialog.show();

        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://drive.google.com/drive/folders/18hdFMzsIR2bciKIlzpouCWzBBzL3vdnM");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished (WebView view, String url){
                super.onPageFinished(view, url);
                progressDialog.dismiss();
            }
        });

        toolbar  = (Toolbar) findViewById(R.id.toolbar_croquis);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_croquis, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_closes:
                onBackPressed();
                return true;
            case R.id.action_descargar:
                downloadPdf(linkPDF,"PDF");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void downloadPdf(String link,String titulo){
        if(PermissionUtils.checkInternet(CroquisActivity.this)!=null && checkInternet(CroquisActivity.this).isConnected()){
            if (PermissionUtils.checkVersion()){
                if (PermissionUtils.checkPermits(this)){
                    convertPdf(link,titulo);
                }
                else{
                    PermissionUtils.askPermits(this);
                }
            }
            else{
                convertPdf(link,titulo);
            }
        }
        else{
            Toast.makeText(this, "Verifique su conexión a INTERNET", Toast.LENGTH_LONG).show();
        }
    }

    public void convertPdf(String link,String titulo){
        downloadManager=(DownloadManager)getSystemService(Context.DOWNLOAD_SERVICE);
        Uri url=Uri.parse(link);
        DownloadManager.Request request = new DownloadManager.Request(url);
        request.setTitle(titulo);
        request.setDescription("Descargando ...");
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,titulo+".pdf");
        downloadManager.enqueue(request);
        Toast.makeText(getApplicationContext(),"PDF Descargado Correctamente",Toast.LENGTH_SHORT).show();
    }



}
