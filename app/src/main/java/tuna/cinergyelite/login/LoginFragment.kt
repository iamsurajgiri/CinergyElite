package tuna.cinergyelite.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import tuna.cinergyelite.BuildConfig
import tuna.cinergyelite.R
import tuna.cinergyelite.databinding.FragmentLoginBinding
import tuna.cinergyelite.utils.DeviceIdManager
import tuna.cinergyelite.utils.PreferenceHelper
import tuna.cinergyelite.utils.navigateTo
import tuna.cinergyelite.viewModels.LoginViewModel
import tuna.core.constansts.Constants
import tuna.core.data.model.request.GuestTokenRequest
import tuna.core.data.model.request.LoginRequest
import tuna.core.data.network.ApiResult

class LoginFragment : Fragment() {

    private val adapter = DescriptionAdapter()

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val loginViewModel: LoginViewModel by viewModel()

    override fun onDestroyView() {
        super.onDestroyView()
        //set binding to null when fragment is destroyed
        _binding = null
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.descriptionRv.adapter = adapter
        adapter.descriptionDiffer.submitList(getDescriptions())
        if (PreferenceHelper.getString(Constants.GUEST_TOKEN).isEmpty()){
            loginViewModel.getGuestToken(GuestTokenRequest(
                DeviceIdManager.getDeviceId(),
                BuildConfig.SECRET_KEY
            ))
        }
        binding.guestLogin.setOnClickListener {
            loginViewModel.guestLogin(
                LoginRequest(
                    DeviceIdManager.getDeviceId()
                )
            )
        }
        initObservers()

    }

    private fun initObservers() {
        loginViewModel.token.observe(viewLifecycleOwner){
            if (it==null) return@observe
            when(it){
                is ApiResult.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is ApiResult.Success -> {
                    PreferenceHelper.saveString(Constants.GUEST_TOKEN,it.data.token)
                    loginViewModel.token.postValue(null)
                    binding.progressBar.visibility = View.GONE
                }
                is ApiResult.Error -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    binding.progressBar.visibility = View.GONE
                }
            }
        }

        loginViewModel.login.observe(viewLifecycleOwner){
            if (it==null) return@observe
            when(it){
                is ApiResult.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is ApiResult.Success -> {
                    PreferenceHelper.saveInt(Constants.USER_ID,it.data.user?.id?:0)
                    it.data.user?.memberId?.let { it1 ->
                        PreferenceHelper.saveInt(Constants.MEMBER_ID,
                            it1
                        )
                    }
                    Toast.makeText(requireContext(), it.data.response, Toast.LENGTH_SHORT).show()
                    loginViewModel.login.postValue(null)
                    findNavController().navigateTo(R.id.homeFragment)
                    binding.progressBar.visibility = View.GONE
                }
                is ApiResult.Error -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    binding.progressBar.visibility = View.GONE
                }
            }
        }
    }


    private fun getDescriptions():List<String>{
        return listOf(
            "Enjoy a free popcorn on sign up.",
            "Enjoy a $5.00 Elite reward after 300 points to spend however you want.",
            "Enjoy a special birthday movie ticket offer.",
            "Enjoy exclusive content, sneak peaks, and special offers!."
        )
    }

}