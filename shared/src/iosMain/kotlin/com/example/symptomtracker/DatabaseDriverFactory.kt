package com.example.symptomtracker

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.example.symptomtracker.db.AppDatabase

actual class DatabaseDriverFactory {
  actual fun createDriver(): SqlDriver {
    return NativeSqliteDriver(AppDatabase.Schema, "notes.db")
  }
}
