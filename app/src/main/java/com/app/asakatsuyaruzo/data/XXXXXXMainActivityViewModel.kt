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
//import com.app.asakatsuyaroze.adapter.MainActivityAlarmPatternData
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.GlobalScope
//import kotlinx.coroutines.launch
//
//class MainActivityViewModel(application: Application) : AndroidViewModel(application) {
//
//    var alarmPatternDataList = MutableLiveData<List<MainActivityAlarmPatternData>>()
//
//    init {
//        alarmPatternDataList.value = mutableListOf(
//        )
//    }
//
//    fun refleshData(applicationContext: Context) {
//        val database =
//            Room.databaseBuilder(applicationContext, AppDatabase::class.java, "mainDatabase")
//                .build()
//        val alarmPatternDao = database.alarmPatternDao()
//
//        GlobalScope.launch(Dispatchers.IO) {
//            val alarmPatternList: List<AlarmPattern> = alarmPatternDao.getAll()
//            val alarmPatternDataListTmp: MutableList<MainActivityAlarmPatternData> = mutableListOf()
//            for (i in alarmPatternList.indices) {
//                alarmPatternDataListTmp.add(
//                    MainActivityAlarmPatternData(
//                        alarmPatternList.get(i).id.toString(),
//                        alarmPatternList.get(i).patternName
//                    )
//                )
//            }
//            alarmPatternDataList.postValue(alarmPatternDataListTmp)
//
//        }
//    }
//
//}
//
