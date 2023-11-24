package uz.gita.jaxongir.adminformapp.presentation.adduser

interface AddContract {
    interface ViewModel{
        fun onEventDispatcher(intent: Intent)
    }

    interface Intent{
        data class AddUser(val userName : String, val password :String)
    }

    interface Direction{

    }


}