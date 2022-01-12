package com.example.ricindigus.enpove2021.fragments.vivienda;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.ricindigus.enpove2021.R;
import com.example.ricindigus.enpove2021.activities.MarcoActivity;
import com.example.ricindigus.enpove2021.modelo.DAOUtils;
import com.example.ricindigus.enpove2021.modelo.Data;
import com.example.ricindigus.enpove2021.modelo.SQLConstantes;
import com.example.ricindigus.enpove2021.modelo.pojos.Caratula;
import com.example.ricindigus.enpove2021.modelo.pojos.Carga;
import com.example.ricindigus.enpove2021.modelo.pojos.Marco;
import com.example.ricindigus.enpove2021.modelo.pojos.Ubicacion;
import com.example.ricindigus.enpove2021.modelo.pojos.ViviendaItem;
import com.example.ricindigus.enpove2021.util.FragmentPagina;
import com.example.ricindigus.enpove2021.util.UtilsMethods;
import com.example.ricindigus.enpove2021.util.UtilsMethodsInputs;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;

import java.lang.reflect.Method;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentCaratula extends FragmentPagina implements GoogleApiClient.OnConnectionFailedListener,
        GoogleApiClient.ConnectionCallbacks,
        LocationListener {

    private static final String LOGTAG = "android-localizacion";
    private static final int PETICION_PERMISO_LOCALIZACION = 101;
    private static final int PETICION_CONFIG_UBICACION = 201;
    private GoogleApiClient apiClient;
    private LocationRequest locRequest;

    private View rootView;
    private CardView cvLongitud, cvAltitud, cvLatitud;
    private ToggleButton btnGPS;
    private TextView txtLatitud, txtLongitud, txtAltitud;
    private Context contexto;
    private TextView txtGPSMensaje;
    private final int TIEMPO = 10000;
    Handler handler = new Handler();
     Runnable myRunnable;

    String idVivienda;
    String idMes;
    String idAnio;
    String idZona;
    String idPeriodo;
    String idUsuario;
    String nroSegmento;
    String latitudMarco;
    String longitudMarco;
    Context context;

    TextView nro_conglomerado_TextView,nro_selec_vivienda_TextView,tipo_selec_vivienda_TextView,vivienda_reemplazo_TextView,nom_dep_TextView, nom_prov_TextView, nom_dist_TextView, nom_ccpp_TextView;
    TextView zona_TextView, manzana_id_TextView, vivienda_TextView;
    Spinner tipvia_Spinner,nro_viv_reemplazo_Spinner;
    EditText tipvia_o_EditText, nomvia_EditText, nropta_EditText, block_EditText, interior_EditText,nroPta2_EditText, piso_EditText, mza_EditText,
            lote_EditText, km_EditText, telefono_EditText;

    private String nro_conglomerado   = "";
    private String nro_selec_vivienda = "";
    private String tipo_selec_vivienda= "";
    private String vivienda_reemplazo = "";
    private String nro_vivienda_reemplazo = "";
    private int tipvia = -1;
    private String tipvia_o="";
    private String ccdd;
    private String nom_dep;
    private String ccpp;
    private String nom_prov;
    private String ccdi;
    private String nom_dist;
    private String codccpp;
    private String nom_ccpp;
    private String zona;
    private String manzana_id;
    private String vivienda;
    private String latitud;
    private String longitud;
    private String altitud;
    private String nomvia = "";
    private String nropta = "";
    private String nropta2 = "";
    private String block = "";
    private String interior = "";
    private String piso = "";
    private String mza = "";
    private String lote = "";
    private String km = "";
    private String telefono = "";


    @SuppressLint("ValidFragment")
    public FragmentCaratula(String idVivienda, String idMes, String idAnio, String idZona, String idPeriodo, String idUsuario,String nroSegmento, Context context) {
        this.idVivienda = idVivienda;
        this.idMes = idMes;
        this.idAnio = idAnio;
        this.idZona = idZona;
        this.idPeriodo = idPeriodo;
        this.idUsuario = idUsuario;
        this.nroSegmento = nroSegmento;
        this.context = context;
        Data data = new Data(context);
        data.open();
        data.actualizar_municipio_error();
        data.close();
    }

    public FragmentCaratula() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_caratula, container, false);
        nro_conglomerado_TextView    = (TextView) rootView.findViewById(R.id.caratula_txt_conglomerado);
        nro_selec_vivienda_TextView  = (TextView) rootView.findViewById(R.id.caratula_txt_nroseleccion);
        tipo_selec_vivienda_TextView = (TextView) rootView.findViewById(R.id.caratula_txt_tipseleccion);
        vivienda_reemplazo_TextView  = (TextView) rootView.findViewById(R.id.caratula_txt_vivreemplazo);
        nro_viv_reemplazo_Spinner = (Spinner) rootView.findViewById(R.id.caratula_sp_vivreemplazo);

        nom_dep_TextView = (TextView) rootView.findViewById(R.id.caratula_textview_NOM_DEP);
        nom_prov_TextView = (TextView) rootView.findViewById(R.id.caratula_textview_NOM_PROV);
        nom_dist_TextView = (TextView) rootView.findViewById(R.id.caratula_textview_NOM_DIST);
        nom_ccpp_TextView = (TextView) rootView.findViewById(R.id.caratula_textview_NOM_CCPP);
        zona_TextView = (TextView) rootView.findViewById(R.id.caratula_textview_ZONA);
        manzana_id_TextView = (TextView) rootView.findViewById(R.id.caratula_textview_MANZANA_ID);
        vivienda_TextView = (TextView) rootView.findViewById(R.id.caratula_textview_VIVIENDA);

        tipvia_Spinner = (Spinner) rootView.findViewById(R.id.caratula_spinner_TIPVIA);
        tipvia_o_EditText = (EditText) rootView.findViewById(R.id.caratula_edittext_TIPVIA_O);
        nomvia_EditText = (EditText) rootView.findViewById(R.id.caratula_textview_NOMVIA);
        nropta_EditText = (EditText) rootView.findViewById(R.id.caratula_textview_NROPTA);
        block_EditText = (EditText) rootView.findViewById(R.id.caratula_textview_BLOCK);
        interior_EditText = (EditText) rootView.findViewById(R.id.caratula_textview_INTERIO);
        nroPta2_EditText = (EditText) rootView.findViewById(R.id.caratula_textview_pta2);
        piso_EditText = (EditText) rootView.findViewById(R.id.caratula_textview_PISO);
        mza_EditText = (EditText) rootView.findViewById(R.id.caratula_textview_MZA);
        lote_EditText = (EditText) rootView.findViewById(R.id.caratula_textview_LOTE);
        km_EditText = (EditText) rootView.findViewById(R.id.caratula_textview_KM);
        telefono_EditText = (EditText) rootView.findViewById(R.id.caratula_textview_TELEFONO);

        cvAltitud = (CardView) rootView.findViewById(R.id.gps_layout_altitud);
        cvLatitud = (CardView) rootView.findViewById(R.id.gps_layout_latitud);
        cvLongitud = (CardView) rootView.findViewById(R.id.gps_layout_longitud);
        txtAltitud = (TextView) rootView.findViewById(R.id.gps_txt_altitud);
        txtLatitud = (TextView) rootView.findViewById(R.id.gps_txt_latitud);
        txtLongitud = (TextView) rootView.findViewById(R.id.gps_txt_longitud);
        btnGPS = (ToggleButton) rootView.findViewById(R.id.gps_btn_captura);
        txtGPSMensaje = (TextView) rootView.findViewById(R.id.gps_txt_mensaje);


        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tipvia_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                UtilsMethodsInputs.setupSpinnerEspecifique(pos,7,tipvia_o_EditText);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });
        UtilsMethodsInputs.setupEditText(tipvia_o_EditText,getContext(),1,50);
        UtilsMethodsInputs.setupEditText(nomvia_EditText,getContext(),1,50);
        UtilsMethodsInputs.setupEditText(nropta_EditText,getContext(),1,4);
        UtilsMethodsInputs.setupEditText(block_EditText,getContext(),1,4);
        UtilsMethodsInputs.setupEditText(interior_EditText,getContext(),1,4);
        UtilsMethodsInputs.setupEditText(nroPta2_EditText,getContext(),1,2);
        UtilsMethodsInputs.setupEditText(piso_EditText,getContext(),1,2);
        UtilsMethodsInputs.setupEditText(mza_EditText,getContext(),1,4);
        UtilsMethodsInputs.setupEditText(lote_EditText,getContext(),1,4);
        UtilsMethodsInputs.setupEditText(km_EditText,getContext(),2,4);
        //UtilsMethodsInputs.setupEditText(telefono_EditText,getContext(),3,15);
        cargarDatos();
        btnGPS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleLocationUpdates(btnGPS.isChecked());
                if(!btnGPS.isChecked()){
                    txtAltitud.setText("99.999999");
                    txtLatitud.setText("99.999999");
                    txtLongitud.setText("99.999999");
                    showMessageDistanciaGPS(99.999999,99.999999);
                    altitud = "0000";
                }
            }
        });

        //Construcción cliente API Google
        apiClient = new GoogleApiClient.Builder(getActivity().getApplicationContext())
                    .enableAutoManage(getActivity(), this)
                    .addConnectionCallbacks(this)
                    .addApi(LocationServices.API)
                    .build();

        //-12.2247243,-76.9353625/-12.2249369,-76.9357177
//        validateDistanciaGPS(-12.2247243,-76.9353625,-12.2249369,-76.9357177);//45m
//        validateDistanciaGPS(-12.2247243,-76.9353625,-12.2254175,-76.936576);//150m
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-6.47868608,-76.36486217,-6.4787347,-76.3649621));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-6.4845376,-76.3840039,-6.4844849,-76.3841069));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-6.48069104,-76.38924653,-6.4806776,-76.3891184));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-6.48045143,-76.3836542,-6.480566,-76.3837131));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-6.48200541,-76.36907258,-6.4819085,-76.369206));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-6.48200541,-76.36907258,-6.4818921,-76.3689281));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-6.48112186,-76.38867703,-6.4808912,-76.3889212));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-6.4762202,-76.38914744,-6.4758806,-76.3891626));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-6.48069104,-76.38924653,-6.4808521,-76.388738));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-11.8652533333333,-77.1342083333333,-11.8653314,-77.1342743));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-11.8654983333333,-77.1347483333333,-11.8653929,-77.1347705));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-11.87906,-77.1415066666666,-11.8789508,-77.1415364));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-11.8651883333333,-77.1343516666666,-11.8652607,-77.1342572));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-11.88147845,-77.13310674,-11.8814914,-77.1329829));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-11.8651883333333,-77.1343516666666,-11.8652783,-77.1342531));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-11.88209969,-77.13185245,-11.8821865,-77.131604));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-11.87963625,-77.13131201,-11.8794792,-77.1306808));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-11.8658783333333,-77.1344266666666,-11.877337,-77.1090346));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-16.36591511,-71.56380053,-16.3659872,-71.5638546));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-16.3764068,-71.57005184,-16.3763007,-71.5700504));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-16.39651312,-71.56977131,-16.396543,-71.5696599));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-16.38041673,-71.55426373,-16.3804397,-71.5543798));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-16.37657587,-71.57039189,-16.376543,-71.5705136));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-16.37670693,-71.57173591,-16.3768557,-71.5716418));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-12.039706,-77.049691,-12.0397441,-77.049605));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-12.039681,-77.049033,-12.0395914,-77.0489914));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-12.039382,-77.049782,-12.0394972,-77.0497063));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-12.039382,-77.049782,-12.0395032,-77.0496831));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-12.039309,-77.050751,-12.0394664,-77.0507811));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-12.040183,-77.049239,-12.0403542,-77.0491956));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-12.040183,-77.049239,-12.0403882,-77.0492337));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-12.039219,-77.050423,-12.0391367,-77.0500849));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-12.039425,-77.049776,-12.0396933,-77.0495011));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-12.040183,-77.049239,-12.0405841,-77.0492132));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-12.040183,-77.049239,-12.0406349,-77.0491825));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-12.040183,-77.049239,-12.0406362,-77.0491841));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-12.040593,-77.051218,-12.0405606,-77.0517008));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-12.039681,-77.049033,-12.0402575,-77.0489792));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-12.040761,-77.051207,-12.0413419,-77.0511188));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-12.040593,-77.051218,-12.0410365,-77.0508226));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-12.040593,-77.051218,-12.041174,-77.0504069));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-12.040761,-77.051207,-12.0416967,-77.0503325));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-12.040174,-77.05137,-12.0384405,-77.0512706));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-8.04748058,-79.06787149,-8.0473941,-79.0678437));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-8.0565173,-79.05117235,-8.0564725,-79.0512545));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-8.04937933,-79.05759803,-8.0493569,-79.057505));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-8.04362516,-79.04958862,-8.0435951,-79.0496822));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-8.06169472,-79.05379951,-8.0617618,-79.0537277));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-8.05047512,-79.05082651,-8.0505199,-79.050728));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-8.04486637,-79.06555879,-8.0449767,-79.0656042));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-8.04307791,-79.06336499,-8.0432025,-79.0633983));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-8.06123858,-79.05360734,-8.0611393,-79.0535222));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-8.08892393,-79.01279295,-8.0889813,-79.0127219));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-8.08650416,-79.01244365,-8.0865488,-79.0123633));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-8.09132642,-79.00991676,-8.0913496,-79.0100112));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-8.08586579,-79.01398672,-8.0857662,-79.0139825));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-8.09066147,-79.00797856,-8.0905739,-79.0080356));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-8.08897364,-79.0094256,-8.08887,-79.0094266));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-8.09262691,-79.01653008,-8.0927504,-79.0165145));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-11.96229125,-77.07789288,-11.9623911,-77.0778822));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-11.96229125,-77.07789288,-11.9622096,-77.0778268));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-11.95636724,-77.08392557,-11.9564584,-77.0839818));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-11.9623945,-77.07814728,-11.9624006,-77.0782712));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-11.95931603,-77.08356521,-11.9593715,-77.0836917));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-11.96063214,-77.0831803,-11.960563,-77.0833151));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-11.96492658,-77.07377417,-11.9647243,-77.0737227));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-11.95970431,-77.08276309,-11.9596163,-77.0825446));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-11.95948651,-77.08178078,-11.9593303,-77.081591));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-11.96224554,-77.07789765,-11.9619942,-77.0777084));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-11.96492658,-77.07377417,-11.9651106,-77.0741043));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-11.95681485,-77.07347589,-11.9569532,-77.0728547));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-11.97102746,-77.07147891,-11.9619947,-77.071369));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-11.97044905,-77.07135781,-12.0433629,-77.0332665));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-11.97766534,-77.01933922,-11.9776638,-77.0194299));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-11.98548267,-77.00488704,-11.9853962,-77.0048558));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-11.99058062,-76.99134006,-11.9905734,-76.9912447));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-11.98313673,-77.01523815,-11.983106,-77.0151445));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-11.98149732,-77.01730399,-11.9815953,-77.017342));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-11.99058062,-76.99134006,-11.9905365,-76.9912383));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-11.97766534,-77.01933922,-11.9775991,-77.0192495));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-11.98149732,-77.01730399,-11.9816,-77.0173506));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-11.98661916,-77.01816938,-11.9865117,-77.0181272));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-11.98149732,-77.01730399,-11.9815942,-77.0173843));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-11.98629664,-77.01681695,-11.9861801,-77.0168669));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-11.98313673,-77.01523815,-11.9830065,-77.0152593));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-11.98646367,-77.00651568,-11.9863731,-77.0066161));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-11.98551309,-77.01852181,-11.9853785,-77.0185312));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-11.99054909,-76.992797,-11.9903995,-76.9926151));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-6.49398446,-76.34299746,-6.4939048,-76.3430629));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-6.49017638,-76.34588798,-6.4900665,-76.3459157));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-6.48435818,-76.3448301,-6.484409,-76.3449466));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-6.49144028,-76.35498955,-6.4913607,-76.3550969));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-6.48906155,-76.3462657,-6.4891826,-76.3457241));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-8.12106437,-79.00521848,-8.1211504,-79.005277));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-8.12030074,-79.01270929,-8.1204208,-79.0127362));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-8.12009183,-79.01258584,-8.12009132,-79.012710));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-8.12020218,-79.01269354,-8.1202867,-79.0125448));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-8.11929964,-79.00608097,-8.1193419,-79.0059114));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-8.11579977,-79.01281165,-8.1154985,-79.0125221));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-8.11572259,-79.0120974,-8.1145449,-79.0116527));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-8.11766854,-79.00553951,-8.1162447,-79.0045366));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-8.11767854,-79.00555264,-8.1162874,-79.0044064));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-12.18330777,-76.99193048,-12.1833929,-76.9918743));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-12.17408308,-76.98965727,-12.1740188,-76.9895501));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-12.1875318,-77.00315092,-12.187382,-77.0034602));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-12.18126286,-77.00359619,-12.1809426,-77.0038147));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-12.18701357,-77.0037725,-12.1869602,-77.0033475));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-12.18156895,-77.00497784,-12.18172,-77.0043061));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-12.18126192,-77.00359864,-12.181685,-77.0030314));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-12.18146428,-77.00732165,-12.1807843,-77.0077163));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-12.16612361,-76.9700375,-12.18257,-76.9917915));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-12.16612582,-76.97004144,-12.1832495,-76.991625));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-12.1877665,-77.00305778,-12.0588345,-77.0446537));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-12.18780966,-77.00310788,-12.0588341,-77.044654));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-16.39085944,-71.51232885,-16.3907668,-71.5123298));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-16.39076154,-71.51253216,-16.3907121,-71.5124443));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-16.39013911,-71.5122664,-16.3902249,-71.5123305));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-16.44072117,-71.55549171,-16.4406426,-71.5555746));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-16.39050712,-71.51186066,-16.3904012,-71.5119003));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-16.39188701,-71.51094804,-16.3919771,-71.5110239));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-16.39104745,-71.51183565,-16.3909329,-71.5118004));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-16.39073255,-71.51197836,-16.3906128,-71.5120097));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-16.39089905,-71.51174237,-16.3910176,-71.5116882));
//        Log.e("Distancia_gps:",""+getDistanciaGPS(-16.43686101,-71.59039186,-16.4399643,-71.5929541));


//        sendUbicacion();
    }

    @Override
    public void onPause() {
        super.onPause();
        //handler.removeCallbacks(myRunnable);
    }

    @Override
    public void guardarDatos() {
        Data data = new Data(context);
        data.open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLConstantes.caratula_longitud,longitud);
        contentValues.put(SQLConstantes.caratula_latitud,latitud);
        contentValues.put(SQLConstantes.caratula_altitud,altitud);
        contentValues.put(SQLConstantes.caratula_tipvia,tipvia+"");
        contentValues.put(SQLConstantes.caratula_tipvia_o,tipvia_o);
        contentValues.put(SQLConstantes.caratula_nomvia,nomvia);
        contentValues.put(SQLConstantes.caratula_nropta,nropta);
        contentValues.put(SQLConstantes.caratula_block,block);
        contentValues.put(SQLConstantes.caratula_interior,interior);
        contentValues.put(SQLConstantes.caratula_nroPta2,nropta2);
        contentValues.put(SQLConstantes.caratula_piso,piso);
        contentValues.put(SQLConstantes.caratula_mza,mza);
        contentValues.put(SQLConstantes.caratula_lote,lote);
        contentValues.put(SQLConstantes.caratula_km,km);
        contentValues.put(SQLConstantes.caratula_telefono,telefono);
        contentValues.put(SQLConstantes.caratula_cobertura,"1");
        contentValues.put(SQLConstantes.caratula_nro_vivienda_reemplazo,nro_vivienda_reemplazo);
        if(data.existeElemento(getNombreTabla(),idVivienda)){
            data.actualizarElemento(getNombreTabla(),contentValues,idVivienda);
        }else{
            contentValues.put(SQLConstantes.caratula_id,idVivienda);
            contentValues.put(SQLConstantes.caratula_mes,idMes);
            contentValues.put(SQLConstantes.caratula_anio,idAnio);
            contentValues.put(SQLConstantes.caratula_periodo,idPeriodo);
            contentValues.put(SQLConstantes.caratula_usuario,idUsuario);
            contentValues.put(SQLConstantes.caratula_zona,zona);
            contentValues.put(SQLConstantes.caratula_ccdd,ccdd);
            contentValues.put(SQLConstantes.caratula_nom_dep,nom_dep);
            contentValues.put(SQLConstantes.caratula_ccpp,ccpp);
            contentValues.put(SQLConstantes.caratula_nom_prov,nom_prov);
            contentValues.put(SQLConstantes.caratula_ccdi,ccdi);
            contentValues.put(SQLConstantes.caratula_nom_dist,nom_dist);
            contentValues.put(SQLConstantes.caratula_codccpp,codccpp);
            contentValues.put(SQLConstantes.caratula_nom_ccpp,nom_ccpp);
            contentValues.put(SQLConstantes.caratula_manzana_id,manzana_id);
            contentValues.put(SQLConstantes.caratula_vivienda,vivienda);
            contentValues.put(SQLConstantes.caratula_t_hogar,"0");
            contentValues.put(SQLConstantes.caratula_conglomerado,nro_conglomerado);
            contentValues.put(SQLConstantes.caratula_nro_selec_vivienda,nro_selec_vivienda);
            contentValues.put(SQLConstantes.caratula_tipo_selec_vivienda, UtilsMethods.setTipoSeleccionVivienda(tipo_selec_vivienda));
            contentValues.put(SQLConstantes.caratula_vivienda_reemplazo,UtilsMethods.setViviendaReemplazo(vivienda_reemplazo));
            contentValues.put(SQLConstantes.caratula_fecha_inicio_aa,UtilsMethods.getFechaActual().getAnioInicio());
            contentValues.put(SQLConstantes.caratula_fecha_inicio_mm,UtilsMethods.getFechaActual().getMesInicio());
            contentValues.put(SQLConstantes.caratula_fecha_inicio_dd,UtilsMethods.getFechaActual().getDiaInicio());
            contentValues.put(SQLConstantes.caratula_nroSegmento,nroSegmento);
            data.insertarElemento(getNombreTabla(),contentValues);
        }
        Caratula caratula = data.getCaratula(idVivienda);
        data.close();
    }

    @Override
    public void llenarVariables() {
        nro_conglomerado     = nro_conglomerado_TextView.getText().toString();
        nro_selec_vivienda   = nro_selec_vivienda_TextView.getText().toString();
        tipo_selec_vivienda  = tipo_selec_vivienda_TextView.getText().toString();
        vivienda_reemplazo   = vivienda_reemplazo_TextView.getText().toString();
        nro_vivienda_reemplazo   = nro_viv_reemplazo_Spinner.getSelectedItem().toString();
        nom_dep = nom_dep_TextView.getText().toString();
        nom_prov = nom_prov_TextView.getText().toString();
        nom_dist = nom_dist_TextView.getText().toString();
        nom_ccpp = nom_ccpp_TextView.getText().toString();
        zona = zona_TextView.getText().toString();
        manzana_id = manzana_id_TextView.getText().toString();
        vivienda = vivienda_TextView.getText().toString();
        tipvia = tipvia_Spinner.getSelectedItemPosition();
        tipvia_o = tipvia_o_EditText.getText().toString();
        nomvia = nomvia_EditText.getText().toString();
        nropta = nropta_EditText.getText().toString();
        block = block_EditText.getText().toString();
        interior = interior_EditText.getText().toString();
        nropta2 = nroPta2_EditText.getText().toString();
        piso = piso_EditText.getText().toString();
        mza = mza_EditText.getText().toString();
        lote = lote_EditText.getText().toString();
        km = km_EditText.getText().toString();
        telefono = telefono_EditText.getText().toString();
        latitud = txtLatitud.getText().toString();
        longitud = txtLongitud.getText().toString();
    }

    @Override
    public void cargarDatos() {
        Caratula caratula = null;
        Data data =  new Data(context);
        data.open();
        if(data.existeElemento(getNombreTabla(),idVivienda)){
            caratula = data.getCaratula(idVivienda);
            data.close();
            nro_conglomerado_TextView.setText(caratula.getConglomerado());
            nro_selec_vivienda_TextView.setText(caratula.getNro_selec_vivienda());
            tipo_selec_vivienda_TextView.setText(UtilsMethods.getTipoSeleccionVivienda(caratula.getTipo_selec_vivienda()));
            vivienda_reemplazo_TextView.setText(UtilsMethods.getViviendaReemplazo(caratula.getVivienda_reemplazo()));
            if(caratula.getVivienda_reemplazo().equals("1") && !caratula.getNro_vivienda_reemplazo().equals("")){
                setSpinnerNroSeleccionVivienda(caratula.getNroSegmento());
                //nro_viv_reemplazo_Spinner.setSelection(getPosicionLista(caratula.getNroSegmento(),caratula.getNro_vivienda_reemplazo()));
                nro_viv_reemplazo_Spinner.setSelection(getPosicionListaViviendasNoReemplazo(caratula.getNro_vivienda_reemplazo()));
            }else{
                setSpinnerInicio();
                nro_viv_reemplazo_Spinner.setEnabled(false);
            }
            nom_dep_TextView.setText(caratula.getNom_dep());
            nom_prov_TextView.setText(caratula.getNom_prov());
            nom_dist_TextView.setText(caratula.getNom_dist());
            nom_ccpp_TextView.setText(caratula.getNom_ccpp());
            zona_TextView.setText(caratula.getZona());
            manzana_id_TextView.setText(caratula.getManzana_id());
            vivienda_TextView.setText(caratula.getVivienda());
            txtLatitud.setText(caratula.getLatitud());
            txtLongitud.setText(caratula.getLongitud());
            if(!caratula.getTipvia().equals("") || !caratula.getTipvia().equals("0")){
                tipvia_Spinner.setSelection(Integer.parseInt(caratula.getTipvia()));
                tipvia_o_EditText.setText(caratula.getTipvia_o());
            }
            nomvia_EditText.setText(caratula.getNomvia());
            nropta_EditText.setText(caratula.getNropta());
            block_EditText.setText(caratula.getBlock());
            interior_EditText.setText(caratula.getInterior());
            nroPta2_EditText.setText(caratula.getNroPuerta2());
            piso_EditText.setText(caratula.getPiso());
            mza_EditText.setText(caratula.getMza());
            lote_EditText.setText(caratula.getLote());
            km_EditText.setText(caratula.getKm());
            telefono_EditText.setText(caratula.getTelefono());
            showMessageDistanciaGPS(Double.parseDouble(caratula.getLatitud()),Double.parseDouble(caratula.getLongitud()));

        }else {
            Marco marco = data.getMarco(idVivienda);
            data.close();
            nro_conglomerado_TextView.setText(marco.getConglomerado());
            nro_selec_vivienda_TextView.setText(marco.getNro_selec_vivienda());
            tipo_selec_vivienda_TextView.setText(UtilsMethods.getTipoSeleccionVivienda(marco.getTipo_selec_vivienda()));
            vivienda_reemplazo_TextView.setText(UtilsMethods.getViviendaReemplazo(marco.getVivienda_reemplazo()));
            if(marco.getVivienda_reemplazo().equals("1")){
                setSpinnerNroSeleccionVivienda(marco.getNroSegmento());
            }else{
                setSpinnerInicio();
                nro_viv_reemplazo_Spinner.setEnabled(false);
            }
            nom_dep_TextView.setText(marco.getDepartamento());
            nom_prov_TextView.setText(marco.getProvincia());
            nom_dist_TextView.setText(marco.getDistrito());
            nom_ccpp_TextView.setText(marco.getNomccpp());
            ccdd    = marco.getCcdd();
            ccpp    = marco.getCcpp();
            ccdi    = marco.getCcdi();
            codccpp = marco.getCodccpp();

            zona_TextView.setText(marco.getZona());
            manzana_id_TextView.setText(marco.getManzana_id());
            vivienda_TextView.setText(marco.getNroVivienda());

            nomvia_EditText.setText(marco.getNomvia());
            nropta_EditText.setText(marco.getNropta());
            block_EditText.setText(marco.getBlock());
            interior_EditText.setText(marco.getInterior());
            nroPta2_EditText.setText(marco.getNroPuerta2());
            piso_EditText.setText(marco.getPiso());
            mza_EditText.setText(marco.getMza());
            lote_EditText.setText(marco.getLote());
            km_EditText.setText(marco.getKm());
            telefono_EditText.setText(marco.getTelefono());

        }
    }

    @Override
    public void llenarVista() {

    }

    public static boolean validarPiso(String cadena){
        boolean result=false;
        int numero;
        String piso = cadena.trim();
        if(piso.length()==1 || (piso.equals("S1") || piso.equals("S2"))) {
            try {
                //result = cadena.matches("[+-]?\\d*(\\.\\d+)?");
                result = piso.matches("a|A|s|S|s1|S1|s2|S2|[1-9].*");
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
        if(piso.length()==2 && !piso.equals("S2")) {
            try {
                numero = Integer.parseInt(piso);
                result = (numero<=15); //
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
        return result;
    }

    @Override
    public boolean validarDatos() {
        llenarVariables();
        int sumaValida = 0;
        String mensaje = "";
        if (nro_vivienda_reemplazo.equals("--Seleccione Vivienda--")) { mostrarMensaje("DEBE SELECCIONAR VIVIENDA QUE REEMPLAZARA"); return false; }
        if (latitud.equals("")) { mostrarMensaje("FALTA CAPTURAR EL GPS"); return false; }
        if(tipvia == 0){ mostrarMensaje("Debe indicar el tipo de via"); return false; }
        if(tipvia == 7){
            Log.e("tipvia_o", "validarDatos: "+tipvia_o );
            if(tipvia_o.trim().equals("")){
                mostrarMensaje("TIPO DE VIA: DEBE ESPECIFICAR OTRO"); return false;
            }
            if(!tipvia_o.equals("") && tipvia_o.trim().length()<3){
                mostrarMensaje("TIPO DE VIA: EL ESPECIFIQUE NO DEBE SER MENOR A 3 CARACTERES"); return false;
            }
        }
        if(nomvia.equals("")){ mostrarMensaje("NOMBRE DE VIA NO DEBE ESTAR VACIO PONER NEP DE NO HABER INFORMACIÓN"); return false; }
        if(nropta.equals("")){ mostrarMensaje("Nº PUERTA NO DEBE ESTAR VACIO - COLOCAR SN"); return false; }
        //if(nropta.equals("SN") && (mza.equals("") || lote.equals(""))){ mostrarMensaje("Vía sin número, debe indicar Manzana y Lote");}
        if(es_cero(nropta)){ mostrarMensaje("NO PUEDE SER CERO, NÚMERO DE LA PUERTA"); return false; }
//        if(block.equals("")){ mostrarMensaje("Debe completar BLOCK"); return false; }
//        if(interior.equals("")){ mostrarMensaje("Debe completar INTERIOR"); return false; }
        if(piso.equals("")){ mostrarMensaje("EL PISO NO DEBE ESTAR VACIO, PONER 1 DE NO HABER INFORMACIÓN"); return false; }
        if(es_cero(piso)){ mostrarMensaje("NO PUEDE SER CERO, PISO"); return false; }

        if(!validarPiso(piso)){mostrarMensaje("DEBE INGRESAR UN NUMERO DE PISO CORRECTO"); return false; }
        //if(piso != "A" || piso != "S"){ mostrarMensaje("DEBE INGRESAR UN NUMERO DE PISO CORRECTO"); return false; }

        if(!telefono.equals("") && telefono.length()<6){ mostrarMensaje("TELEFONO: DEBE INGRESAR MAS DE 5 NUMEROS"); return false; }

        if(!telefono.equals("") && UtilsMethods.validarTelefono(telefono)>1){ mostrarMensaje("TELEFONO: DEBE INGRESAR UN NUMERO VALIDO"); return false; }
//        if(tipvia == 5) {
//            if(km.trim().equals("")){ mostrarMensaje("Tipo de vía es Carretera, debe indicar el Km."); return false;}
//            if(Integer.parseInt(km.trim())==0){ mostrarMensaje("Tipo de vía es Carretera, Kilometros debe ser diferente de cero."); return false;}
//        }
        if(!mza.equals("") && lote.equals("")){ mostrarMensaje("SI TIENE MANZANA DEBE INGRESA EL LOTE"); return false; }
        if(mza.equals("") && !lote.equals("")){ mostrarMensaje("SI TIENE LOTE DEBE INGRESA LA MANZANA"); return false; }



        if(telefono.equals("")){ mostrarMensaje("DEBE REGISTRAR NÚMERO DE TELÉFONO"); return true; }

//        if(t_hogar.equals("")){ mostrarMensaje("Debe indicar la CANTIDAD DE HOGARES"); return false; }
        return true;
    }

    public boolean es_cero(String numero){
        int cant_cero=0;
        for(int i=0;i<numero.length();i++){
            if(numero.charAt(i)=='0'){
                cant_cero++;
            }
        }
        if(cant_cero==numero.length()) return true; else return false;
    }

    @Override
    public String getNombreTabla() {
        return SQLConstantes.tablacaratula;
    }

    public void setSpinnerInicio(){
        String [] inicio = {""};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item,inicio);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        nro_viv_reemplazo_Spinner.setAdapter(adapter);
    }

    public void setSpinnerNroSeleccionVivienda(String nroSegmento){
        //ArrayList<String> viviendas = getListaViviendas(nroSegmento);
        ArrayList<String> viviendas = getListaViviendasNoReemplazo();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item,viviendas);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        nro_viv_reemplazo_Spinner.setAdapter(adapter);
    }

    public ArrayList<String> getListaViviendas(String nroSegmento){
        ArrayList<String> viviendas = new ArrayList<>();
        Data data =  new Data(context);
        data.open();
        viviendas = data.getListaSpinnerViviendas(nroSegmento);
        data.close();
        return viviendas;
    }

    public ArrayList<String> getListaViviendasNoReemplazo(){
        ArrayList<String> viviendas = new ArrayList<>();
        Data data =  new Data(context);
        data.open();
        viviendas.add("--Seleccione Vivienda--");
        for (ViviendaItem viviendaItem:getListaViviendasXUser(idUsuario)) {
            viviendas.add(viviendaItem.getNroSelecVivienda());
        }
        //viviendas = data.getListaSpinnerViviendas(nroSegmento);
        data.close();
        return viviendas;
    }

    public int getPosicionLista(String conglomerado,String valor){
        ArrayList<String> viviendas = getListaViviendas(conglomerado);
        int posicion = 0;
        for (int i = 0; i < viviendas.size(); i++) {
            if(viviendas.get(i).equals(valor)){
                posicion = i;
            }
        }
        return posicion;
    }

    public int getPosicionListaViviendasNoReemplazo(String valor){
        ArrayList<String> viviendas = getListaViviendasNoReemplazo();
        int posicion = 0;
        for (int i = 0; i < viviendas.size(); i++) {
            if(viviendas.get(i).equals(valor)){
                posicion = i;
            }
        }
        return posicion;
    }


    public ArrayList<Carga> getListaCarga(String idUsuario){
        ArrayList<Carga> lista = null;
        Data data = new Data(context);
        data.open();
        lista = data.getListCarga(idUsuario);
        data.close();
        return lista;
    }

    public ArrayList<ViviendaItem> getListaViviendasXUser(String idUsuario){
        ArrayList<ViviendaItem> lista = null;
        Data data = new Data(context);
        data.open();
        lista = data.getListViviendasXCargaXReemplazo(getListaCarga(idUsuario));
        data.close();
        return lista;
    }

    public void mostrarMensaje(String m){
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(m);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void toggleLocationUpdates(boolean enable) {
        if (enable) {
            enableLocationUpdates();
        } else {
            disableLocationUpdates();
        }
    }

    private void enableLocationUpdates() {
        locRequest = new LocationRequest();
        locRequest.setInterval(2000);
        locRequest.setFastestInterval(1000);
        locRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        LocationSettingsRequest locSettingsRequest = new LocationSettingsRequest.Builder().addLocationRequest(locRequest).build();
        PendingResult<LocationSettingsResult> result = LocationServices.SettingsApi.checkLocationSettings(apiClient, locSettingsRequest);

        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(LocationSettingsResult locationSettingsResult) {
                final Status status = locationSettingsResult.getStatus();
                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:
                        Log.i(LOGTAG, "Configuración correcta");
                        startLocationUpdates();
                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        try {
                            Log.i(LOGTAG, "Se requiere actuación del usuario");
                            status.startResolutionForResult(getActivity(), PETICION_CONFIG_UBICACION);
                            btnGPS.setChecked(false);
                        } catch (IntentSender.SendIntentException e) {
                            btnGPS.setChecked(false);
                            Log.i(LOGTAG, "Error al intentar solucionar configuración de ubicación");
                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        Log.i(LOGTAG, "No se puede cumplir la configuración de ubicación necesaria");
                        btnGPS.setChecked(false);
                        break;
                }
            }
        });
    }

    private void disableLocationUpdates() {
        try{
            LocationServices.FusedLocationApi.removeLocationUpdates(apiClient, this);
        }catch (Exception e){}

    }

    private void startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            //Ojo: estamos suponiendo que ya tenemos concedido el permiso.
            //Sería recomendable implementar la posible petición en caso de no tenerlo.
            Log.i(LOGTAG, "Inicio de recepción de ubicaciones");
            LocationServices.FusedLocationApi.requestLocationUpdates(apiClient, locRequest, this);
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {
        //Se ha producido un error que no se puede resolver automáticamente
        //y la conexión con los Google Play Services no se ha establecido.
        Log.e(LOGTAG, "Error grave al conectar con Google Play Services");
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        //Conectado correctamente a Google Play Services
        if (ActivityCompat.checkSelfPermission(getActivity().getApplicationContext(),Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PETICION_PERMISO_LOCALIZACION);
        } else {
            Location lastLocation = LocationServices.FusedLocationApi.getLastLocation(apiClient);
//            updateUI(lastLocation);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        //Se ha interrumpido la conexión con Google Play Services

        Log.e(LOGTAG, "Se ha interrumpido la conexión con Google Play Services");
    }

    private void updateUI(Location loc) {
        if (loc != null) {
            txtLatitud.setText(String.valueOf(loc.getLatitude()));
            txtLongitud.setText(String.valueOf(loc.getLongitude()));
            txtAltitud.setText(String.valueOf(loc.getAccuracy()));
            showMessageDistanciaGPS(loc.getLatitude(),loc.getLongitude());
            altitud = loc.getAccuracy()+"";
            Log.e("altura:",""+altitud);
        } else {
            txtLatitud.setText("99.999999");
            txtLongitud.setText("99.999999");
            txtAltitud.setText("99.999999");
            showMessageDistanciaGPS(99.999999,99.999999);
            altitud = "0000";
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == PETICION_PERMISO_LOCALIZACION) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                //Permiso concedido
                @SuppressWarnings("MissingPermission")
                Location lastLocation = LocationServices.FusedLocationApi.getLastLocation(apiClient);
                updateUI(lastLocation);

            } else {
                //Permiso denegado:
                //Deberíamos deshabilitar toda la funcionalidad relativa a la localización.

                Log.e(LOGTAG, "Permiso denegado");
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case PETICION_CONFIG_UBICACION:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        startLocationUpdates();
                        break;
                    case Activity.RESULT_CANCELED:
                        Log.i(LOGTAG, "El usuario no ha realizado los cambios de configuración necesarios");
                        btnGPS.setChecked(false);
                        break;
                }
                break;
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.i(LOGTAG, "Recibida nueva ubicación!");
        disableLocationUpdates();
        btnGPS.setChecked(false);
        //Mostramos la nueva ubicación recibida
        updateUI(location);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        apiClient.stopAutoManage(getActivity());
        apiClient.disconnect();
    }

    @Override
    public void onStop() {
        super.onStop();
    }


    public boolean validateDistanciaGPS(Double lat1,Double lng1,Double lat2,Double lng2){
        Location locationA = new Location("punto A");
        Location locationB = new Location("punto B");
        float distancia;
        boolean estado = false;
        locationA.setLatitude(lat1);
        locationA.setLongitude(lng1);
        locationB.setLatitude(lat2);
        locationB.setLongitude(lng2);
        distancia = locationA.distanceTo(locationB);
        if(distancia<=10){
            estado = true;
        }
        return estado;
    }

    public float getDistanciaGPS(Double lat1,Double lng1,Double lat2,Double lng2){
        Location locationA = new Location("punto A");
        Location locationB = new Location("punto B");
        float distancia;
        boolean estado = false;
        locationA.setLatitude(lat1);
        locationA.setLongitude(lng1);
        locationB.setLatitude(lat2);
        locationB.setLongitude(lng2);
        distancia = locationA.distanceTo(locationB);
        if(distancia<=10){
            estado = true;
        }
        return distancia;
    }

    public void showMessageDistanciaGPS(Double lat,Double lng){
        Marco marco = DAOUtils.getMarco(idVivienda,context);
        if(!marco.getLatitud().equals("") && !marco.getLongitud().equals("")){
            if(validateDistanciaGPS(Double.parseDouble(marco.getLatitud()),Double.parseDouble(marco.getLongitud()),lat,lng)){
                txtGPSMensaje.setBackgroundColor(getResources().getColor(R.color.common_google_signin_btn_text_dark_focused));
                txtGPSMensaje.setTextColor(getResources().getColor(R.color.greenAccent));
                txtGPSMensaje.setText("GPS CORRECTO");
            }else {
                txtGPSMensaje.setBackgroundColor(getResources().getColor(R.color.common_google_signin_btn_text_dark_focused));
                txtGPSMensaje.setTextColor(getResources().getColor(R.color.pinkPrimary));
                txtGPSMensaje.setText("GPS ALEJADO");
            }
        }
    }


    public void sendUbicacion() {
       // handler =  new Handler();
        //handler.postDelayed(myRunnable, 3000);

        myRunnable = new Runnable() {
            @Override
            public void run() {
                Ubicacion ubicacion = new Ubicacion();
                ubicacion.setId(""+null);
                ubicacion.setIdUsuario(idUsuario);
                ubicacion.setUsuario(DAOUtils.getUsuarioId(idUsuario,context).getNombre());
                ubicacion.setIdDispositivo(getIdDispositivo());
                ubicacion.setSerial(getSerialNumber());
                ubicacion.setLongitud(getLatitud(longitud));
                ubicacion.setLatitud(getLongitud(latitud));
                ubicacion.setFechaTablet(UtilsMethods.getFechaNow().getDiaInicio()+"/"+UtilsMethods.getFechaNow().getMesInicio()+"/"+UtilsMethods.getFechaNow().getAnioInicio()+"-"+UtilsMethods.getFechaNow().getHoraInicio()+":"+UtilsMethods.getFechaNow().getMinutoInicio());
                ubicacion.setVersionApk(UtilsMethods.getVersion(context));
                ubicacion.setBateria(""+getBateria());
                ubicacion.setConexion("Activo");


                Log.e("prueba","***************************");
                Log.e("prueba-Idx:",ubicacion.getId());
                Log.e("prueba-Id Usuariox:",ubicacion.getIdUsuario());
                Log.e("prueba-Usuariox:",ubicacion.getUsuario());
                Log.e("prueba-Dispositivo:",ubicacion.getIdDispositivo());
                Log.e("prueba-Serial:",ubicacion.getSerial());
                Log.e("prueba-Latitud:",ubicacion.getLatitud());
                Log.e("prueba-Longitud:",ubicacion.getLongitud());
                Log.e("prueba-Fecha:",ubicacion.getFechaTablet());
                Log.e("prueba-Version:",ubicacion.getVersionApk());
                Log.e("prueba-Bateria:",ubicacion.getBateria());
                Log.e("prueba-Conexion:",ubicacion.getConexion());
                Log.e("prueba","***************************");
                handler.postDelayed(myRunnable, TIEMPO);
            }
        };
        handler.postDelayed(myRunnable, TIEMPO);

//        handler.postDelayed(new Runnable() {
//            public void run() {
//                Ubicacion ubicacion = new Ubicacion();
//                ubicacion.setId(""+null);
//                ubicacion.setIdUsuario(idUsuario);
//                ubicacion.setUsuario(DAOUtils.getUsuarioId(idUsuario,context).getNombre());
//                ubicacion.setIdDispositivo(getIdDispositivo());
//                ubicacion.setSerial(getSerialNumber());
//                ubicacion.setLongitud(getLatitud(longitud));
//                ubicacion.setLatitud(getLongitud(latitud));
//                ubicacion.setFechaTablet(UtilsMethods.getFechaNow().getDiaInicio()+"/"+UtilsMethods.getFechaNow().getMesInicio()+"/"+UtilsMethods.getFechaNow().getAnioInicio()+"-"+UtilsMethods.getFechaNow().getHoraInicio()+":"+UtilsMethods.getFechaNow().getMinutoInicio());
//                ubicacion.setVersionApk(UtilsMethods.getVersion(context));
//                ubicacion.setBateria(""+getBateria());
//                ubicacion.setConexion("Activo");
//
//
//                Log.e("prueba","***************************");
//                Log.e("prueba-Id:",ubicacion.getId());
//                Log.e("prueba-Id Usuario:",ubicacion.getIdUsuario());
//                Log.e("prueba-Usuario:",ubicacion.getUsuario());
//                Log.e("prueba-Dispositivo:",ubicacion.getIdDispositivo());
//                Log.e("prueba-Serial:",ubicacion.getSerial());
//                Log.e("prueba-Latitud:",ubicacion.getLatitud());
//                Log.e("prueba-Longitud:",ubicacion.getLongitud());
//                Log.e("prueba-Fecha:",ubicacion.getFechaTablet());
//                Log.e("prueba-Version:",ubicacion.getVersionApk());
//                Log.e("prueba-Bateria:",ubicacion.getBateria());
//                Log.e("prueba-Conexion:",ubicacion.getConexion());
//                Log.e("prueba","***************************");
//                handler.postDelayed(this, TIEMPO);
//            }
//
//        }, TIEMPO);

    }

    public float getBateria(){
        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = context.registerReceiver(null, ifilter);
        int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

        float batteryPct = level * 100 / (float)scale;
        return batteryPct;
    }
    public static String getSerialNumber() {
        String serialNumber;

        try {
            Class<?> c = Class.forName("android.os.SystemProperties");
            Method get = c.getMethod("get", String.class);

            // (?) Lenovo Tab (https://stackoverflow.com/a/34819027/1276306)
            serialNumber = (String) get.invoke(c, "gsm.sn1");

            if (serialNumber.equals(""))
                // Samsung Galaxy S5 (SM-G900F) : 6.0.1
                // Samsung Galaxy S6 (SM-G920F) : 7.0
                // Samsung Galaxy Tab 4 (SM-T530) : 5.0.2
                // (?) Samsung Galaxy Tab 2 (https://gist.github.com/jgold6/f46b1c049a1ee94fdb52)
                serialNumber = (String) get.invoke(c, "ril.serialnumber");

            if (serialNumber.equals(""))
                // Archos 133 Oxygen : 6.0.1
                // Google Nexus 5 : 6.0.1
                // Hannspree HANNSPAD 13.3" TITAN 2 (HSG1351) : 5.1.1
                // Honor 5C (NEM-L51) : 7.0
                // Honor 5X (KIW-L21) : 6.0.1
                // Huawei M2 (M2-801w) : 5.1.1
                // (?) HTC Nexus One : 2.3.4 (https://gist.github.com/tetsu-koba/992373)
                serialNumber = (String) get.invoke(c, "ro.serialno");

            if (serialNumber.equals(""))
                // (?) Samsung Galaxy Tab 3 (https://stackoverflow.com/a/27274950/1276306)
                serialNumber = (String) get.invoke(c, "sys.serialnumber");

            if (serialNumber.equals(""))
                // Archos 133 Oxygen : 6.0.1
                // Hannspree HANNSPAD 13.3" TITAN 2 (HSG1351) : 5.1.1
                // Honor 9 Lite (LLD-L31) : 8.0
                // Xiaomi Mi 8 (M1803E1A) : 8.1.0
                serialNumber = Build.SERIAL;

            // If none of the methods above worked
            if (serialNumber.equals(Build.UNKNOWN))
                serialNumber = null;
        } catch (Exception e) {
            e.printStackTrace();
            serialNumber = null;
        }

        return serialNumber;
    }
    public String getIdDispositivo(){
        //import android.provider.Settings.Secure;
        String id = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        return id;
    }
    public String getLatitud(String latitud){
        String latituds = "99.999999";
        try {
            if(!latitud.equals("") || latitud!=null){
                latituds = latitud;
            }
        }catch (NullPointerException e){

        }

        return latituds;
    }

    public String getLongitud(String longitud){
        String longituds = "99.999999";
        try {
            if(!longitud.equals("") || longitud!=null){
                longituds = longitud;
            }
        }catch (NullPointerException e){

        }

        return longituds;
    }

//    public String getConexionInternet(){
//        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
//
//        if (networkInfo != null && networkInfo.isConnected()) {
//            Log.e("juxe_conexion:","True");
//            // Si hay conexión a Internet en este momento
//        } else {
//            Log.e("juxe_conexion:","False");
//            // No hay conexión a Internet en este momento
//        }
//        return ""+networkInfo.getState();
//    }

}
