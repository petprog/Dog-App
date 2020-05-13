package com.android.petprog.dogs.view

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.android.petprog.dogs.R
import kotlinx.android.synthetic.main.activity_main.*

private const val REQUEST_CODE_SEND_SMS = 1

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = Navigation.findNavController(this, R.id.fragment)
        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, null)
    }

    fun checkSmsPermission() {
        val hasSendSmsPermission =
            ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
        if (hasSendSmsPermission != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.SEND_SMS
                )
            ) {
                AlertDialog.Builder(this)
                    .setTitle("Send SMS permission")
                    .setMessage("This app requires access to send an sms")
                    .setPositiveButton("Ask me") { _, _ ->
                        requestSmsPermission()
                    }
                    .setNegativeButton("No") { _, _ ->
                        notifyDetailFragment(false)

                    }.show()

            } else {
                requestSmsPermission()
            }
        } else {
            notifyDetailFragment(true)
        }
    }

    private fun requestSmsPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.SEND_SMS),
            REQUEST_CODE_SEND_SMS
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            REQUEST_CODE_SEND_SMS -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    notifyDetailFragment(true)
                } else {
                    notifyDetailFragment(false)
                }
            }
        }
    }

    private fun notifyDetailFragment(permissionGranted: Boolean) {
        val activeFragment = fragment.childFragmentManager.primaryNavigationFragment
        if (activeFragment is DetailFragment) {
            activeFragment.onPermissionResult(permissionGranted)
        }
    }
}
