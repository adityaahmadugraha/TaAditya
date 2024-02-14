package com.aditya.appsjeruk.admin.addrule

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Rect
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.AutoCompleteTextView
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.lifecycle.lifecycleScope
import com.aditya.appsjeruk.BuildConfig
import com.aditya.appsjeruk.R
import com.aditya.appsjeruk.admin.ActivityAdmin
import com.aditya.appsjeruk.admin.AdminViewModel
import com.aditya.appsjeruk.data.Resource
import com.aditya.appsjeruk.databinding.ActivityAddGejalaBinding
import com.aditya.appsjeruk.databinding.ActivityAddPenyakitBinding
import com.aditya.appsjeruk.utils.Constant
import com.aditya.appsjeruk.utils.Constant.setInputError
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import kotlin.math.log


@AndroidEntryPoint
class AddGejalaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddGejalaBinding
    private val viewModel: AdminViewModel by viewModels()
    private var fotoFile: File? = null
    private var fotoPath: String? = null

    companion object {
        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
        private const val REQUEST_CODE_PERMISSIONS = 10
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (!allPermissionsGranted()) {
                Toast.makeText(
                    this,
                    "Tidak mendapatkan permission.",
                    Toast.LENGTH_SHORT
                ).show()
                finish()
            }
        }
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(baseContext, it) == PackageManager.PERMISSION_GRANTED
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddGejalaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnKirim.setOnClickListener {
            getUserInput()
        }

        binding.icBack.setOnClickListener {
            intent = Intent(this@AddGejalaActivity, ActivityAdmin::class.java)
            startActivity(intent)
        }

        if (!allPermissionsGranted()) {
            ActivityCompat.requestPermissions(
                this,
                REQUIRED_PERMISSIONS,
                REQUEST_CODE_PERMISSIONS
            )
        }

        val bottomSheetDialog = BottomSheetDialog(this@AddGejalaActivity)
        binding.imgAdd.setOnClickListener {
            val view = layoutInflater.inflate(R.layout.bottom_sheet, null)
            bottomSheetDialog.setContentView(view)
            view.findViewById<ImageView>(R.id.img_camera)?.setOnClickListener {
                startCamera()
                bottomSheetDialog.dismiss()
            }
            view.findViewById<ImageView>(R.id.img_galery)?.setOnClickListener {
                startGallery()
                bottomSheetDialog.dismiss()
            }
            bottomSheetDialog.show()
        }
    }

    private fun startGallery() {
        val intent = Intent()
        intent.action = Intent.ACTION_GET_CONTENT
        intent.type = "image/*"
        val chooser = Intent.createChooser(intent, "Choose a Picture")
        launcherIntentGallery.launch(chooser)
    }

    private val launcherIntentGallery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val selectedImg = result.data?.data as Uri
            selectedImg.let { uri ->
                fotoFile = Constant.uriToFile(uri, this@AddGejalaActivity)
                binding.imgGejala.setImageURI(uri)
            }
        }
    }

    private fun startCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.resolveActivity(this@AddGejalaActivity.packageManager)

        Constant.createCustomTempFile(this@AddGejalaActivity).also {
            val photoURI: Uri = FileProvider.getUriForFile(
                this@AddGejalaActivity,
                BuildConfig.APPLICATION_ID,
                it
            )
            fotoPath = it.absolutePath
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
            launcherIntentCamera.launch(intent)
        }
    }

    private val launcherIntentCamera = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == RESULT_OK) {
            val photoShooted = File(fotoPath.toString())
            val rotatedBitmap = Constant.getRotatedBitmap(photoShooted)
            lifecycleScope.launch(Dispatchers.IO) {
                val uri = rotatedBitmap?.let { it1 ->
                    Constant.bitmapToFile(
                        it1,
                        this@AddGejalaActivity
                    )
                }
                fotoFile = File(uri?.path.toString())

            }
            Glide.with(this@AddGejalaActivity)
                .load(rotatedBitmap)
                .into(binding.imgGejala)
        }
    }

    private fun getUserInput() {
        binding.apply {
            val nama_gejala = etNameGejala.text.toString()
            val deskripsi_gejala = etDeskripsiGejala.text.toString()
//            val pencegahan = etPencegahan.text.toString()
            val kode_gejala = etKodeGejala.text.toString()

            if (validateInput(nama_gejala, deskripsi_gejala, kode_gejala)) {
                if (fotoFile != null) {
                    val fileProfilePicture: File = Constant.reduceFileImage(fotoFile as File)

                    val requestBody: RequestBody = MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        .addFormDataPart("nama_gejala", nama_gejala)
                        .addFormDataPart("deskripsi_gejala", deskripsi_gejala)
                        .addFormDataPart("kode_gejala", kode_gejala)
                        .addFormDataPart(
                            "foto_gejala",
                            fileProfilePicture.name,
                            RequestBody.create("image/*".toMediaTypeOrNull(), fileProfilePicture)
                        ).build()

                    insertData(requestBody)
                } else {
                    Toast.makeText(
                        this@AddGejalaActivity,
                        getString(R.string.pick_photo_first),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }


    private fun validateInput(
        nama: String,
        deskripsi: String,
        kode: String
    ): Boolean {
        binding.apply {
            if (nama.isEmpty()) {
                return ilNameGejala.setInputError(getString(R.string.must_not_empty))
            }
            if (deskripsi.isEmpty()) {
                return ilDeskripsiGejala.setInputError(getString(R.string.must_not_empty))
            }
            if (kode.isEmpty()) {
                return ilKodeGejala.setInputError(getString(R.string.must_not_empty))
            }
            if (fotoFile == null) {
                Toast.makeText(
                    this@AddGejalaActivity,
                    getString(R.string.pick_photo_first),
                    Toast.LENGTH_SHORT
                ).show()
                return false
            }
        }
        return true
    }

    private fun insertData(requestBody: RequestBody) {
        viewModel.insertGejala(requestBody).observe(this@AddGejalaActivity) { result ->
            binding.apply {
                when (result) {
                    is Resource.Loading -> {
                    }

                    is Resource.Success -> {

                        Intent(this@AddGejalaActivity, ActivityAdmin::class.java).apply {
                            startActivity(this)
                        }
                    }

                    is Resource.Error -> {
                        Toast.makeText(
                            this@AddGejalaActivity, result.error,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }


    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (ev?.action == MotionEvent.ACTION_DOWN) {
            val v = currentFocus
            if (v is TextInputEditText || v is AutoCompleteTextView) {
                val outRect = Rect()
                v.getGlobalVisibleRect(outRect)
                if (!outRect.contains(ev.rawX.toInt(), ev.rawY.toInt())) {
                    v.clearFocus()
                    val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(v.windowToken, 0)
                }
            }
        }
        return super.dispatchTouchEvent(ev)
    }

}