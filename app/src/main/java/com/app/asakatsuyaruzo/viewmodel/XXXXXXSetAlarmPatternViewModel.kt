//package com.app.asakatsuyaruzo.viewmodel
//
//import android.app.Application
//import android.content.Context
//import androidx.lifecycle.AndroidViewModel
//import androidx.lifecycle.MutableLiveData
//import androidx.room.Room
//import com.app.asakatsuyaruzo.data.AlarmPattern
//import com.app.asakatsuyaroze.data.AppDatabase
//import com.app.asakatsuyaruzo.data.defaultAlarmPattern
//import com.app.asakatsuyaruzo.MainActivity.Companion.alarmPatternDao
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.GlobalScope
//import kotlinx.coroutines.launch
//
//
//class SetAlarmPatternViewModel(application: Application) : AndroidViewModel(application) {
//
//    var alarmPatternDataList = MutableLiveData<List<AlarmPattern>>()
//
//    init {
//        alarmPatternDataList.value = mutableListOf(defaultAlarmPattern())
//    }
//
//    fun refleshData(applicationContext: Context,patternId:Int) {
//        GlobalScope.launch(Dispatchers.IO) {
//            val alarmPattern: AlarmPattern = alarmPatternDao.getAlarmPattern(patternId)
//            alarmPatternDataList.postValue(mutableListOf(alarmPattern))
//
//        }
//    }
//
//}
//
