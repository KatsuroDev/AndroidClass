package ca.qc.cstj.s05localdatasource.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import ca.qc.cstj.s05localdatasource.core.Constants
import ca.qc.cstj.s05localdatasource.data.repositories.ContactRepository
import ca.qc.cstj.s05localdatasource.domain.models.Contact
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

@Database(entities = [Contact::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    //TODO: Add Rooms Repository (DAO) here
    //abstract fun zzzRepository(): zzzRepository
    abstract fun contactRepository(): ContactRepository

    companion object {
        @Volatile
        private var Instance: AppDatabase? = null

        fun getDatabase(context: Context) :
                AppDatabase = Instance ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "contact_database" //TODO: RENAME database_name
            ).addCallback(object: RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    val ioScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
                    ioScope.launch {
                        //Instance?.
                        Instance?.contactRepository()?.create(Constants.PREPOPULATE_CONTACTS)
                    }
                }
            }).build()

            Instance = instance
            return instance
        }

    }
}