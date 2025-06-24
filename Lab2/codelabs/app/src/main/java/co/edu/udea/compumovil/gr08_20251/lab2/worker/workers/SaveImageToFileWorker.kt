package co.edu.udea.compumovil.gr08_20251.lab2.worker.workers

import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import co.edu.udea.compumovil.gr08_20251.lab2.R
import co.edu.udea.compumovil.gr08_20251.lab2.worker.DELAY_TIME_MILLIS
import co.edu.udea.compumovil.gr08_20251.lab2.worker.KEY_IMAGE_URI
import co.edu.udea.compumovil.gr08_20251.lab2.worker.domain.CreateImageUriUseCase
import co.edu.udea.compumovil.gr08_20251.lab2.worker.domain.SaveImageUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Saves the image to a permanent file
 */
private const val TAG = "SaveImageToFileWorker"

class SaveImageToFileWorker(ctx: Context, params: WorkerParameters) : CoroutineWorker(ctx, params) {

    private val title = "Blurred Image"
    private val dateFormatter = SimpleDateFormat(
        "yyyy.MM.dd 'at' HH:mm:ss z",
        Locale.getDefault()
    )

    override suspend fun doWork(): Result {
        // Makes a notification when the work starts and slows down the work so that
        // it's easier to see each WorkRequest start, even on emulated devices
        makeStatusNotification(
            applicationContext.resources.getString(R.string.saving_image),
            applicationContext
        )

        return withContext(Dispatchers.IO) {
            delay(DELAY_TIME_MILLIS)

            val resolver = applicationContext.contentResolver
            return@withContext try {
                val resourceUri = inputData.getString(KEY_IMAGE_URI)
                val bitmap = BitmapFactory.decodeStream(
                    resolver.openInputStream(Uri.parse(resourceUri))
                )

                val imageUrl: String?
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    val createImageUriUseCase = CreateImageUriUseCase()
                    val saveImageUseCase = SaveImageUseCase()

                    val imageUri = createImageUriUseCase(
                        resolver,
                        "${title}_${dateFormatter.format(Date())}.jpg"
                    )

                    saveImageUseCase(resolver, imageUri, bitmap)
                    imageUrl = imageUri.toString()
                } else {
                    @Suppress("DEPRECATION")
                    imageUrl = MediaStore.Images.Media.insertImage(
                        resolver, bitmap, title, dateFormatter.format(Date())
                    )
                }
                if (!imageUrl.isNullOrEmpty()) {
                    val output = workDataOf(KEY_IMAGE_URI to imageUrl)

                    Result.success(output)
                } else {
                    Log.e(
                        TAG,
                        applicationContext.resources.getString(
                            R.string.writing_to_mediaStore_failed
                        )
                    )
                    Result.failure()
                }
            } catch (exception: Exception) {
                Log.e(
                    TAG,
                    applicationContext.resources.getString(R.string.error_saving_image),
                    exception
                )
                Result.failure()
            }
        }
    }
}
