package ru.isaev.swstest.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import ru.isaev.swstest.fragment.viewmodel.RegistrationViewModel
import ru.isaev.swstest.helper.utils.AppTextWatcher
import androidx.fragment.app.viewModels
import ru.isaev.swstest.databinding.RegisterFragmentBinding
import ru.isaev.swstest.helper.showToast


@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private val viewModel: RegistrationViewModel by viewModels()
    private lateinit var mBinding: RegisterFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = RegisterFragmentBinding.inflate(layoutInflater)
        return mBinding.root
    }

    override fun onStart() {
        super.onStart()
        userRegister(viewModel)
        mBinding.registerButton.setOnClickListener {
            if (mBinding.passwordEditText.text.toString()
                    .toInt() == mBinding.repeatPasswordEditText.text.toString().toInt()
            ) {
                viewModel.sendRegisterRequest(viewModel.user)
            } else {
                showToast("Поля паролей не совпадают")
            }
        }
    }

    private fun userRegister(viewModel: RegistrationViewModel) {
        mBinding.emailEditText.addTextChangedListener(AppTextWatcher {
            if (mBinding.passwordEditText.text.toString().isNotEmpty()) {
                viewModel.user.email = mBinding.emailEditText.text.toString()
            }
        })
        mBinding.passwordEditText.addTextChangedListener(AppTextWatcher {
            if (mBinding.passwordEditText.text.toString().isNotEmpty()) {
                viewModel.user.password = mBinding.passwordEditText.text.toString().toInt()
            }
        }
        )
    }
}