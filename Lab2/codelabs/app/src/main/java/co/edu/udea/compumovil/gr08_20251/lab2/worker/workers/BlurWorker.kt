package co.edu.udea.compumovil.gr08_20251.lab2.worker.workers

import android.Manifest
import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import androidx.annotation.RequiresPermission
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import co.edu.udea.compumovil.gr08_20251.lab2.R
import co.edu.udea.compumovil.gr08_20251.lab2.worker.DELAY_TIME_MILLIS
import co.edu.udea.compumovil.gr08_20251.lab2.worker.KEY_BLUR_LEVEL
import co.edu.udea.compumovil.gr08_20251.lab2.worker.KEY_IMAGE_URI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

private const val TAG = "BlurWorker"

class BlurWorker(ctx: Context, params: WorkerParameters) : CoroutineWorker(ctx, params) {

    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    override suspend fun doWork(): Result {
        val resourceUri = inputData.getString(KEY_IMAGE_URI)
        val blurLevel = inputData.getInt(KEY_BLUR_LEVEL, 1)

        makeStatusNotification(
            applicationContext.resources.getString(R.string.blurring_image),
            applicationContext
        )

        return withContext(Dispatchers.IO) {

            // This is an utility function added to emulate slower work.
            delay(DELAY_TIME_MILLIS)

            return@withContext try {
                require(!resourceUri.isNullOrBlank()) {
                    val errorMessage =
                        applicationContext.resources.getString(R.string.invalid_input_uri)
                    Log.e(TAG, errorMessage)
                    errorMessage
                }
                val resolver = applicationContext.contentResolver

                val picture = BitmapFactory.decodeStream(
                    resolver.openInputStream(Uri.parse(resourceUri))
                )

                val output = blurBitmap(picture, blurLevel)

                // Write bitmap to a temp file
                val outputUri = writeBitmapToFile(applicationContext, output)

                val outputData = workDataOf(KEY_IMAGE_URI to outputUri.toString())

                Result.success(outputData)
            } catch (throwable: Throwable) {
                Log.e(
                    TAG,
                    applicationContext.resources.getString(R.string.error_applying_blur),
                    throwable
                )
                Result.failure()
            }
        }
    }
}
