package com.example.octopoints.ui.common

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.octopoints.model.dataaccess.match.MatchDataAccess
import com.example.octopoints.model.dataaccess.team.TeamDataAccess
import com.example.octopoints.model.dataaccess.user.UserDataAccess

open class CommonViewModel<F>(context: Context): ViewModel() {

    val matchDataAccess: MatchDataAccess = MatchDataAccess(context)
    val userDataAccess: UserDataAccess = UserDataAccess(context)
    val teamDataAccess: TeamDataAccess = TeamDataAccess(context)

    val data: MutableLiveData<F> by lazy {
        MutableLiveData<F>()
    }

}

class CommonViewModelFactory<F>(private vararg val args: Any): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (args.size == 2){
            return modelClass.getConstructor(Context::class.java, Long::class.java).newInstance(args[0], args[1])
        }
        return modelClass.getConstructor(Context::class.java).newInstance(args[0])

    }
}