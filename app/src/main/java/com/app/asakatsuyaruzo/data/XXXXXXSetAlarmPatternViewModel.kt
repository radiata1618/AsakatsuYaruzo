//package com.app.asakatsuyaroze.data
//
//import android.app.Application
//import android.content.Context
//import android.util.Log
//import androidx.lifecycle.AndroidViewModel
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.viewModelScope
//import androidx.room.Room
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.GlobalScope
//import kotlinx.coroutines.launch
//
//class SetAlarmPatternViewModel(application: Application) : AndroidViewModel(application) {
//
//    var alarmPattern = MutableLiveData<AlarmPattern>()
//    var alarmListType1 = MutableLiveData<List<Alarm>>()
//    var alarmListType2 = MutableLiveData<List<Alarm>>()
//    var alarmListType3 = MutableLiveData<List<Alarm>>()
//
//    init {
//    }
//
//    fun refleshData(applicationContext: Context, patternId: Int) {
//        val database =
//            Room.databaseBuilder(applicationContext, AppDatabase::class.java, "mainDatabase")
//                .build()
//        val alarmDao = database.alarmDao()
//        val alarmPatternDao = database.alarmPatternDao()
//
//        GlobalScope.launch(Dispatchers.IO) {
//            alarmListType1.postValue(alarmDao.getAlarmByPatternIdType1(patternId))
//            alarmListType2.postValue(alarmDao.getAlarmByPatternIdType2(patternId))
//            alarmListType3.postValue(alarmDao.getAlarmByPatternIdType3(patternId))
//            alarmPattern.postValue(alarmPatternDao.getAlarmPattern(patternId))
//        }
//    }
//}
//
