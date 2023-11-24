package com.thenextus.noteapp.OldActivities.OldClasses

import com.thenextus.noteapp.OldActivities.OldClasses.Observer

open class Subject {
    private val observers: MutableList<Observer> = mutableListOf()

    fun addObserver(observer: Observer) {
        observers.add(observer)
    }

    fun removeObserver(observer: Observer) {
        observers.remove(observer)
    }

    fun notifyObservers(data: String, position: Int, titleData: String, forDelete: Boolean) {
        for (observer in observers) {
            observer.update(data, position, titleData, forDelete)
        }
    }
}