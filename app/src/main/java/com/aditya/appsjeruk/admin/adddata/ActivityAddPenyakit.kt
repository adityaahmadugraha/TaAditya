package com.aditya.appsjeruk.admin.adddata

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Rect
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.AutoCompleteTextView
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.lifecycle.lifecycleScope
import com.aditya.appsjeruk.BuildConfig
import com.aditya.appsjeruk.R
import com.aditya.appsjeruk.admin.ActivityAdmin
import com.aditya.appsjeruk.admin.AdminViewModel
import com.aditya.appsjeruk.data.Resource
import com.aditya.appsjeruk.databinding.ActivityAddPenyakitBinding
import com.aditya.appsjeruk.utils.Constant
import com.aditya.appsjeruk.utils.Constant.reduceFileImage
import com.aditya.appsjeruk.utils.Constant.setInputError
import com.aditya.appsjeruk.utils.Constant.uriToFile
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


@AndroidEntryPoint
class ActivityAddPenyakit : AppCompatActivity() {

    private lateinit var binding: ActivityAddPenyakitBinding
    private val viewModel: AdminViewModel by viewModels()
    private var fotoKerusakan: File? = null
    private var fotoKerusakanPath: String? = null

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

        binding = ActivityAddPenyakitBinding.inflate(layoutInflater)
        setContentView(binding.root)
//


        binding.btnKirim.setOnClickListener {
//            insertData(requestBody)

            getUserInput()
        }

                if (!allPermissionsGranted()) {
            ActivityCompat.requestPermissions(
                this,
                REQUIRED_PERMISSIONS,
                REQUEST_CODE_PERMISSIONS
            )
        }

        val bottomSheetDialog = BottomSheetDialog(this@ActivityAddPenyakit)
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
                fotoKerusakan = uriToFile(uri, this@ActivityAddPenyakit)
                binding.imgPenyakit.setImageURI(uri)
            }
        }
    }

    private fun startCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.resolveActivity(this@ActivityAddPenyakit.packageManager)

        Constant.createCustomTempFile(this@ActivityAddPenyakit).also {
            val photoURI: Uri = FileProvider.getUriForFile(
                this@ActivityAddPenyakit,
                BuildConfig.APPLICATION_ID,
                it
            )
            fotoKerusakanPath = it.absolutePath
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
            launcherIntentCamera.launch(intent)
        }
    }

    private val launcherIntentCamera = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == RESULT_OK) {
            val photoShooted = File(fotoKerusakanPath.toString())
            val rotatedBitmap = Constant.getRotatedBitmap(photoShooted)
            lifecycleScope.launch(Dispatchers.IO) {
                val uri = rotatedBitmap?.let { it1 ->
                    Constant.bitmapToFile(
                        it1,
                        this@ActivityAddPenyakit
                    )
                }
                fotoKerusakan = File(uri?.path.toString())

            }
            Glide.with(this@ActivityAddPenyakit)
                .load(rotatedBitmap)
                .into(binding.imgPenyakit)
        }
    }

    private fun getUserInput() {
        binding.apply {
            val nama = etName.text.toString()
            val deskripsi = etDeskripsi.text.toString()
            val pencegahan = etPencegahan.text.toString()
            val type = etType.text.toString()
            val kode = etKode.text.toString()

            if (validateInput(nama, deskripsi, pencegahan, type, kode)) {
                if (fotoKerusakan != null) {
                    val fileProfilePicture: File = reduceFileImage(fotoKerusakan as File)

                    val requestBody: RequestBody = MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        .addFormDataPart("nama", nama)
                        .addFormDataPart("deskripsi", deskripsi)
                        .addFormDataPart("pencegahan", pencegahan)
                        .addFormDataPart("type", type)
                        .addFormDataPart("kode", kode)
                        .addFormDataPart(
                            "foto",
                            fileProfilePicture.name,
                            RequestBody.create("image/*".toMediaTypeOrNull(), fileProfilePicture)
                        ).build()

                    insertData(requestBody)
                } else {
                    // Handle jika fotoKerusakan null
                    Log.e("MyApp", "Error: fotoKerusakan is null")
                    Toast.makeText(
                        this@ActivityAddPenyakit,
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
        pencegahan: String,
        type: String,
        kode: String
    ): Boolean {
        binding.apply {
            if (nama.isEmpty()) {
                return ilName.setInputError(getString(R.string.must_not_empty))
            }
            if (deskripsi.isEmpty()) {
                return ilDeskripsi.setInputError(getString(R.string.must_not_empty))
            }
            if (pencegahan.isEmpty()) {
                return ilPencegahan.setInputError(getString(R.string.must_not_empty))
            }
            if (type.isEmpty()) {
                return ilType.setInputError(getString(R.string.must_not_empty))
            }
            if (kode.isEmpty()) {
                return ilKode.setInputError(getString(R.string.must_not_empty))
            }
            if (fotoKerusakan == null) {
                Toast.makeText(
                    this@ActivityAddPenyakit,
                    getString(R.string.pick_photo_first),
                    Toast.LENGTH_SHORT
                ).show()
                return false
            }
        }
        return true
    }

    private fun insertData(requestBody: RequestBody) {
        viewModel.insertData(requestBody).observe(this@ActivityAddPenyakit) { result ->
            binding.apply {
                when (result) {
                    is Resource.Loading -> {
                        Log.d("MyApp", "Inserting data - Loading...")
                    }

                    is Resource.Success -> {
                        Log.d("MyApp", "Inserting data - Success")

                        // Log the response from the server
                        Log.d("MyApp", "Response: ${result.data}")

                        Intent(this@ActivityAddPenyakit, ActivityAdmin::class.java).apply {
                            startActivity(this)
                        }
                    }

                    is Resource.Error -> {
                        Log.e("MyApp", "Inserting data - Error: ${result.error}")

                        // Log the response from the server in case of an error
//                        result.error?.printStackTrace()

                        Toast.makeText(
                            this@ActivityAddPenyakit, result.error,
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