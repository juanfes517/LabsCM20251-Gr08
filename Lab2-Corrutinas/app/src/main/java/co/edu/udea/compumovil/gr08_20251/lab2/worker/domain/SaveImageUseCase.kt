package co.edu.udea.compumovil.gr08_20251.lab2.worker.domain

import android.content.ContentResolver
import android.content.ContentValues
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private const val TAG = "SaveImage"

/**
 * Save image into MediaStore.
 */
class SaveImageUseCase {
    suspend operator fun invoke(
        resolver: ContentResolver,
        contentUri: Uri?,
        bitmap: Bitmap,
    ) = withContext(Dispatchers.IO) {
        try {
            contentUri?.let { contentUri ->
                resolver.openOutputStream(contentUri, "w")?.use { outputStream ->
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 85, outputStream)
                }
                val values = ContentValues().apply {
                    put(MediaStore.Images.Media.IS_PENDING, 0)
                }
                resolver.update(contentUri, values, null, null)
            }
        } catch (e: Throwable) {
            Log.e(TAG, "Error occurs $e")
            throw e
        }
    }
}
