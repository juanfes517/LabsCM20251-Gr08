package co.edu.udea.compumovil.gr08_20251.lab2.worker.data

import androidx.work.WorkInfo
import kotlinx.coroutines.flow.Flow

interface BluromaticRepository {
    val outputWorkInfo: Flow<WorkInfo>
    fun applyBlur(blurLevel: Int)
    fun cancelWork()
}
