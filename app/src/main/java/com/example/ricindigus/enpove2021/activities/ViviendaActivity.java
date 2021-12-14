package com.example.ricindigus.enpove2021.activities;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.InputFilter;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ricindigus.enpove2021.R;
import com.example.ricindigus.enpove2021.activities.agregacion.AgregarResidenteActivity;
import com.example.ricindigus.enpove2021.fragments.vivienda.FragmentCaratula;
import com.example.ricindigus.enpove2021.fragments.vivienda.FragmentHogares;
import com.example.ricindigus.enpove2021.modelo.Data;
import com.example.ricindigus.enpove2021.modelo.SQLConstantes;
import com.example.ricindigus.enpove2021.modelo.pojos.Hogar;
import com.example.ricindigus.enpove2021.util.FragmentPagina;
import com.example.ricindigus.enpove2021.util.InputFilterSoloLetras;
import com.example.ricindigus.enpove2021.util.TipoFragmentVivienda;
import com.example.ricindigus.enpove2021.util.UtilsMethods;

import java.util.Calendar;

public class ViviendaActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private String idVivienda;
    public static String vivienda_anio;
    public static String vivienda_mes;
    public static String vivienda_periodo;
    public static String vivienda_zona;
    public static String nroVivienda;
    public static String nroSegmento;
    private String nickUsuario;
    private String idUsuario;

    private TextView btnAtras;
    private TextView btnSiguiente;
    int tFragment = 1;
    int tFragmentAnterior = 1;
    FragmentPagina fragmentActual;
    NavigationView navigationView;

    int diaInicio;
    int mesInicio;
    int anioInicio;

    int horaInicio;
    int minutoInicio;
    int horaFin;
    int minutoFin;

    int diaProx;
    int mesProx;
    int anioProx;

    int horaProx;
    int minutoProx;

    private Data dataTablas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vivienda);
        btnAtras = (TextView) findViewById(R.id.boton_anterior);
        btnSiguiente = (TextView) findViewById(R.id.boton_siguiente);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        idVivienda = getIntent().getExtras().getString("idVivienda");
        nickUsuario = getIntent().getExtras().getString("nickUsuario");
        idUsuario = getIntent().getExtras().getString("idUsuario");
        vivienda_anio = getIntent().getExtras().getString("vivienda_anio");
        vivienda_mes = getIntent().getExtras().getString("vivienda_mes");
        vivienda_periodo = getIntent().getExtras().getString("vivienda_periodo");
        vivienda_zona = getIntent().getExtras().getString("vivienda_zona");
        nroVivienda = getIntent().getExtras().getString("nroVivienda");
        nroSegmento = getIntent().getExtras().getString("nroSegmento");


        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("VIVIENDA N° " + nroVivienda);
        //getSupportActionBar().setSubtitle("ZONA N° " + vivienda_zona);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        TextView txtHeaderVivienda = (TextView) headerView.findViewById(R.id.txtTituloVivienda);
        TextView txtHeaderUsuario = (TextView) headerView.findViewById(R.id.txtTituloUsuario);
        txtHeaderVivienda.setText("VIVIENDA N° " + nroVivienda);
        txtHeaderUsuario.setText(nickUsuario);
        navigationView.setNavigationItemSelectedListener(this);

        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ocultarTeclado(btnSiguiente);
                if(fragmentActual.validarDatos()){
                    fragmentActual.guardarDatos();
                    tFragment++;
                    habilitarFragment(tFragment);
                    while(!setFragment(tFragment,1)){
                        tFragment++;
                        habilitarFragment(tFragment);
                    }
                }
            }
        });

        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ocultarTeclado(btnAtras);
                tFragment--;
                setFragment(tFragment,-1);

            }
        });
        setFragment(TipoFragmentVivienda.CARATULA,1);
    }

    public String getNickUsuario() {
        return nickUsuario;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            salirActivityVivienda();
        }
    }

    public void habilitarFragment(int tipoFragment){
        Data data =  new Data(this);
        data.open();
        switch (tipoFragment){
            case TipoFragmentVivienda.CARATULA:
                if (data.getValor(SQLConstantes.tablafragmentsvivienda,SQLConstantes.fragments_vivienda_caratula,idVivienda).equals("0"))
                    data.actualizarValor(SQLConstantes.tablafragmentsvivienda,SQLConstantes.fragments_vivienda_caratula,"1",idVivienda);
                break;
            case TipoFragmentVivienda.HOGARES:
                if (data.getValor(SQLConstantes.tablafragmentsvivienda,SQLConstantes.fragments_vivienda_hogares,idVivienda).equals("0"))
                    data.actualizarValor(SQLConstantes.tablafragmentsvivienda,SQLConstantes.fragments_vivienda_hogares,"1",idVivienda);
                break;
        }
    }

    public void ocultarTeclado(View view){
        InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public boolean seteoValido(int tipoFragment){
        boolean valido = true;
        Data data =  new Data(this);
        data.open();
        switch (tipoFragment){
            case TipoFragmentVivienda.CARATULA:
                if (data.getValor(SQLConstantes.tablafragmentsvivienda,SQLConstantes.fragments_vivienda_caratula,idVivienda).equals("0")) valido = false;
                break;
            case TipoFragmentVivienda.HOGARES:
                if (data.getValor(SQLConstantes.tablafragmentsvivienda,SQLConstantes.fragments_vivienda_hogares,idVivienda).equals("0")) valido = false;
                break;
        }
        return valido;
    }

    public boolean setFragment(int tipoFragment, int direccion){
        if (seteoValido(tipoFragment)){
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            if(direccion != 0){
                if(direccion > 0){
                    fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
                }else{
                    fragmentTransaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right);
                }
            }
            btnAtras.setVisibility(View.VISIBLE);
            btnSiguiente.setVisibility(View.VISIBLE);
            switch (tipoFragment){
                case TipoFragmentVivienda.CARATULA:
                    btnAtras.setVisibility(View.GONE);
                    FragmentCaratula fragmentCaratula = new FragmentCaratula(idVivienda,vivienda_mes,vivienda_anio, vivienda_zona,vivienda_periodo,idUsuario,nroSegmento,ViviendaActivity.this);
                    fragmentTransaction.replace(R.id.fragment_layout, fragmentCaratula);
                    fragmentActual = fragmentCaratula;
                    tFragment = TipoFragmentVivienda.CARATULA;
                    navigationView.setCheckedItem(R.id.nav_caratula);
                    break;
                case TipoFragmentVivienda.HOGARES:
                    btnSiguiente.setVisibility(View.GONE);
                    FragmentHogares fragmentHogares = new FragmentHogares(idVivienda,ViviendaActivity.this);
                    fragmentTransaction.replace(R.id.fragment_layout, fragmentHogares);
                    fragmentActual = fragmentHogares;
                    tFragment = TipoFragmentVivienda.HOGARES;
                    navigationView.setCheckedItem(R.id.nav_hogares);
                    break;
            }
            fragmentTransaction.commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.vivienda, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        if (id == R.id.action_volver_marco) {
//            salirActivityVivienda();
//            return true;
//        }
//        return super.onOptionsItemSelected(item);

        final int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_volver_marco) {
            salirActivityVivienda();
            return true;
        }else if (id == R.id.action_registrar_observacion) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            final View dialogView = this.getLayoutInflater().inflate(R.layout.dialog_observaciones, null);
            LinearLayout lytObservaciones = dialogView.findViewById(R.id.dialog_lytObservaciones);
            final EditText edtObservaciones = dialogView.findViewById(R.id.dialog_edtObservaciones);
            edtObservaciones.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
            dialog.setView(dialogView);
            dialog.setTitle("OBSERVACIONES CARÁTULA");
            dialog.setPositiveButton("Guardar", null);
            dialog.setNegativeButton("Cancelar", null);
            final AlertDialog alertDialog = dialog.create();
            alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
                @Override
                public void onShow(DialogInterface dialogInterface) {
                    Data data = new Data(ViviendaActivity.this);
                    data.open();
                    edtObservaciones.setText(data.getValor(SQLConstantes.tablacaratula,SQLConstantes.caratula_observaciones,idVivienda));
                    data.close();
                    Button b = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                    b.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Data data = new Data(ViviendaActivity.this);
                            data.open();
                            data.actualizarValor(SQLConstantes.tablacaratula,SQLConstantes.caratula_observaciones,edtObservaciones.getText().toString(),idVivienda);
                            data.close();
                            alertDialog.dismiss();
                        }
                    });
                }
            });
            alertDialog.show();
            return true;
        }else if (id == R.id.action_finalizar_vivienda){
            Data data = new Data(ViviendaActivity.this);
            data.open();
            if (coberturaViviendaConMensaje()){
                data.actualizarValor(SQLConstantes.tablamarco,SQLConstantes.marco_estado,"1",idVivienda);
                finish();
            }
            else data.actualizarValor(SQLConstantes.tablamarco,SQLConstantes.marco_estado,"0'",idVivienda);
            data.close();
            return true;
        }else if (id == R.id.action_resultado_vivienda){
             finalizarVisita();
            return true;
        }else if (id == R.id.action_ubicacion_vivienda){
            Intent intent = new Intent(getApplicationContext(), UbicacionViviendaActivity.class);
            intent.putExtra("idVivienda",idVivienda);
            intent.putExtra("vivienda", nroVivienda);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    public boolean coberturaViviendaConMensaje(){
        Data data = new Data(ViviendaActivity.this);
        data.open();
        String cobCaratula = data.getValor(SQLConstantes.tablacaratula,SQLConstantes.caratula_cobertura,idVivienda);
        if (!cobCaratula.equals("1")){
            mostrarMensaje("FALTA COBERTURAR LA CARÁTULA, NO SE PUEDE FINALIZAR VIVIENDA");return false;
        }
        if (data.getAllHogaresVivienda(idVivienda).size() > 0){
//            for (Hogar hogar : data.getAllHogaresVivienda(idVivienda)){
//                if (!hogar.getCobertura().equals("1")) {
//                    mostrarMensaje("FALTA COBERTURAR EL HOHAR " + hogar.getNumero());return false;
//                }
//            }
        }else {
            mostrarMensaje("NO REGISTRO HOGARES, NO SE PUEDE COBERTURAR ESTE MODULO");return false;
        }
        data.close();
        return true;
    }

    public boolean coberturaVivienda(){
        Data data = new Data(ViviendaActivity.this);
        data.open();
        String cobCaratula = data.getValor(SQLConstantes.tablacaratula,SQLConstantes.caratula_cobertura,idVivienda);
        if (!cobCaratula.equals("1")) return false;
        if (data.getAllHogaresVivienda(idVivienda).size() > 0){
            for (Hogar hogar : data.getAllHogaresVivienda(idVivienda)){
                if (!hogar.getCobertura().equals("1")) return false;
            }
        }else return false;
        data.close();
        return true;
    }

    public void mostrarMensaje(String m){
        final AlertDialog.Builder builder = new AlertDialog.Builder(ViviendaActivity.this);
        builder.setMessage(m);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        boolean correcto = true;
        switch (id){
            case R.id.nav_caratula:
                if (!setFragment(TipoFragmentVivienda.CARATULA,0))correcto=false;
                break;
            case R.id.nav_hogares:
                if (!setFragment(TipoFragmentVivienda.HOGARES,0))correcto=false;
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return correcto;
    }

    public void salirActivityVivienda(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("¿Está seguro que desea volver al marco y salir de la encuesta?")
                .setTitle("Aviso")
                .setCancelable(false)
                .setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        })
                .setPositiveButton("Sí",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Data data = new Data(ViviendaActivity.this);
                                data.open();
                                if (coberturaVivienda()) data.actualizarValor(SQLConstantes.tablamarco,SQLConstantes.marco_estado,"1",idVivienda);
                                else data.actualizarValor(SQLConstantes.tablamarco,SQLConstantes.marco_estado,"0",idVivienda);
                                data.close();
                                finish();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void finalizarVisita(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        final View dialogView = this.getLayoutInflater().inflate(R.layout.dialog_finalizar_visita_vivienda, null);
        final LinearLayout lytDialog = (LinearLayout) dialogView.findViewById(R.id.dialog_finalizar_visita_lyt_viv);
        final Spinner spResultado = (Spinner) dialogView.findViewById(R.id.dialog_finalizar_visita_spResultado_viv);
        final EditText edtEspecifique = (EditText) dialogView.findViewById(R.id.dialog_finalizar_visita_edtEspecifique_viv);
        final CardView cardViewEspecifique = (CardView) dialogView.findViewById(R.id.dialog_cardview_finalizar_especifique_viv);
        String especifique = "";



        edtEspecifique.setFilters(new InputFilter[]{new InputFilter.AllCaps(),new InputFilter.LengthFilter(100), new InputFilterSoloLetras()});

        spResultado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                if(pos == 12){
                    edtEspecifique.setEnabled(true);
                    cardViewEspecifique.setVisibility(View.VISIBLE);
                }else{
                    if(edtEspecifique.isEnabled()){
                        edtEspecifique.setText("");
                        cardViewEspecifique.setVisibility(View.GONE);
                        edtEspecifique.setEnabled(false);
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });



        alert.setTitle("RESULTADO FINAL DE VIVIENDA");
        alert.setView(dialogView);
        alert.setPositiveButton("Finalizar",null);
        alert.setNegativeButton("Cancelar",null);
        final AlertDialog alertDialog = alert.create();

        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                ocultarTeclado(lytDialog);

                Button btnFinalizarVisita = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                btnFinalizarVisita.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean valido = false;
                        boolean estado = true;
                        String mensaje = "";

                        /*Validaciones cuando es diferente de completa*/
                        if(spResultado.getSelectedItemPosition() != 1){
                            if(spResultado.getSelectedItemPosition() == 0){
                                estado = false;
                                if(mensaje.equals("")) mensaje = "DEBE INDICAR EL RESULTADO DE LA VISITAS";
                            }
                        }

                        valido = estado;

                        if(valido){
                            boolean finalizacion = true;

                            if (finalizacion){
//                            //CONTENVALUES DE VISITA RESULTADO FINAL
                                final Calendar cal = Calendar.getInstance();

                                ContentValues contentValuesFinal = new ContentValues();
                                contentValuesFinal.put(SQLConstantes.caratula_fecha_final_aa, UtilsMethods.getFechaActual().getAnioInicio());
                                contentValuesFinal.put(SQLConstantes.caratula_fecha_final_mm,UtilsMethods.getFechaActual().getMesInicio());
                                contentValuesFinal.put(SQLConstantes.caratula_fecha_final_dd,UtilsMethods.getFechaActual().getDiaInicio());
                                contentValuesFinal.put(SQLConstantes.caratula_resultado_final,spResultado.getSelectedItemPosition()+"");
                                contentValuesFinal.put(SQLConstantes.caratula_resultado_final_o,edtEspecifique.getText().toString()+"");
                                //FALTA GUARDAR RESULTADO ESPECIFIQUE DE OTRO

                                dataTablas = new Data(getApplicationContext());
                                dataTablas.open();

                                if(!dataTablas.existeElemento(SQLConstantes.tablacaratula, idVivienda)){
                                    dataTablas.actualizarElemento(SQLConstantes.tablacaratula,contentValuesFinal, idVivienda);
                                }else{

                                }
                                dataTablas.close();
                                alertDialog.dismiss();
                            }
                        }else Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        alertDialog.show();
    }
    public static String checkDigito (int number) {
        return number <= 9 ? "0" + number : String.valueOf(number);
    }

    private void controlarEspecifiqueRadio(RadioGroup group, int checkedId, int opcionEsp, EditText editTextEspecifique) {
        int seleccionado = group.indexOfChild(group.findViewById(checkedId));
        if(seleccionado == opcionEsp){
            editTextEspecifique.setBackgroundResource(R.drawable.input_text_enabled);
            editTextEspecifique.setEnabled(true);
        }else{
            editTextEspecifique.setText("");
            editTextEspecifique.setBackgroundResource(R.drawable.input_text_disabled);
            editTextEspecifique.setEnabled(false);
        }
    }
}
