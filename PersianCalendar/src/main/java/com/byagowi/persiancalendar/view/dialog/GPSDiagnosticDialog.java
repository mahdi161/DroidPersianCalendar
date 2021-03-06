//package com.byagowi.persiancalendar.view.dialog;
//
//import android.app.Dialog;
//import android.content.Context;
//import android.content.Intent;
//import android.location.LocationManager;
//import android.net.ConnectivityManager;
//import android.net.NetworkInfo;
//import android.os.Bundle;
//import android.provider.Settings;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.widget.Toast;
//
//import com.byagowi.persiancalendar.R;
//import com.byagowi.persiancalendar.databinding.DialogAccessBinding;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AlertDialog;
//import androidx.databinding.DataBindingUtil;
//import androidx.fragment.app.DialogFragment;
//
//public class GPSDiagnosticDialog extends DialogFragment implements View.OnClickListener {
//
//    public static boolean needsDiagnostic(Context context) {
//        try {
//            LocationManager gps = (LocationManager)
//                    context.getSystemService(Context.LOCATION_SERVICE);
//            ConnectivityManager connectivityManager = (ConnectivityManager)
//                    context.getSystemService(Context.CONNECTIVITY_SERVICE);
//
//            NetworkInfo info = null;
//            if (connectivityManager != null) info = connectivityManager.getActiveNetworkInfo();
//
//            boolean gpsEnabled = false;
//
//            if (gps != null) {
//                try {
//                    gpsEnabled = gps.isProviderEnabled(LocationManager.GPS_PROVIDER);
//                } catch (Exception ignored) {
//                }
//            }
//
//            return !gpsEnabled || info == null;
//        } catch (Exception e) {
//            // Do whatever we were doing till now
//            return false;
//        }
//    }
//
//    // This is a workaround for the strange behavior of onCreateView (which doesn't show dialog's layout)
//    @NonNull
//    @Override
//    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        Context context = getContext();
//        if (context == null) return null;
//
//        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
//        DialogAccessBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context),
//                R.layout.dialog_access, null, false);
//        dialogBuilder.setView(binding.getRoot());
//
//        boolean gpsEnabled = false;
//        NetworkInfo info = null;
//        try {
//            // check whether gps provider and network providers are enabled or not
//            LocationManager gps = (LocationManager)
//                    context.getSystemService(Context.LOCATION_SERVICE);
//            ConnectivityManager connectivityManager = (ConnectivityManager)
//                    context.getSystemService(Context.CONNECTIVITY_SERVICE);
//
//            if (connectivityManager != null) {
//                info = connectivityManager.getActiveNetworkInfo();
//            }
//            if (gps != null) {
//                try {
//                    gpsEnabled = gps.isProviderEnabled(LocationManager.GPS_PROVIDER);
//                } catch (Exception ignored) {
//                }
//            }
//        } catch (Exception ignore) {
//        }
//
//        if (!gpsEnabled && info == null) {
//            Toast.makeText(context, R.string.internet_location_enable, Toast.LENGTH_SHORT).show();
//            binding.dialogButtonGPS.setVisibility(View.VISIBLE);
//            binding.dialogButtonWiFi.setVisibility(View.VISIBLE);
//            binding.dialogButtonGPRS.setVisibility(View.VISIBLE);
//        } else if (!gpsEnabled) {
//            Toast.makeText(context, R.string.location_enable, Toast.LENGTH_SHORT).show();
//            binding.dialogButtonGPRS.setVisibility(View.GONE);
//            binding.dialogButtonWiFi.setVisibility(View.GONE);
//        } else if (info == null) {
//            Toast.makeText(context, R.string.internet_enable, Toast.LENGTH_SHORT).show();
//            binding.dialogButtonGPS.setVisibility(View.GONE);
//        }
//
//        binding.dialogButtonGPS.setOnClickListener(this);
//        binding.dialogButtonWiFi.setOnClickListener(this);
//        binding.dialogButtonGPRS.setOnClickListener(this);
//        binding.dialogButtonExit.setOnClickListener(this);
//
//        return dialogBuilder.create();
//    }
//
//    @Override
//    public void onClick(View view) {
//        Intent intent = null;
//        switch (view.getId()) {
//            case R.id.dialogButtonGPS:
//                intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//                break;
//
//            case R.id.dialogButtonWiFi:
//                intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//                break;
//
//            case R.id.dialogButtonGPRS:
//                intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
//                break;
//        }
//
//        Context context = getContext();
//        if (intent != null && context != null) {
//            try {
//                context.startActivity(intent);
//            } catch (Exception e) {
//                Log.e("GPSDiagnosticDialog", "startActivity fail", e);
//            }
//        }
//        dismiss();
//    }
//}