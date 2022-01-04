package com.ramoncinp.mydollars.data.database.accounts

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

const val ACCOUNT_ID = 0L

@Dao
interface AccountsDao {

    @Insert
    suspend fun insert(account: Account)

    @Update
    suspend fun update(account: Account)

    @Query("SELECT * from ACCOUNTS_TABLE WHERE id = $ACCOUNT_ID")
    suspend fun getAccount(): Account?
}
